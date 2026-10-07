#!/usr/bin/env python
"""Verifie que `outils/rapport-tokens.py` ne remplace jamais une mesure ecrite par "Aucune
consommation mesuree" quand les transcriptions du sprint ont disparu du poste.

Motif (S51-OUTIL-JOURNAL-TOKENS, s54). Les transcriptions de s22 a s27 ne sont plus sur le poste
qui regenere : l'outil, lance sans `--sprint`, aurait vide 23 des 50 `RAPPORT-tokens.md` et autant
de sections du journal cumulatif. Un outil de mesure qui ne trouve pas sa matiere ne l'ecrase pas.

Les sept cas (les six de l'avis de faisabilite de s54, plus le cas partiel) :
  1. le journal conserve la section chiffree d'un sprint sans transcription ;
  2. l'outil nomme sur sa sortie les sections conservees ;
  3. un sprint dont les transcriptions sont presentes est recalcule ;
  4. une section sans chiffres reste "Aucune consommation mesuree." ;
  5. un rapport de sprint chiffre est conserve quand rien n'est mesure ;
  6. un lancement sans `--sprint` ne modifie aucun rapport chiffre ;
  7. une mesure partielle (un agent chiffre n'a plus aucune transcription) ne remplace pas la
     section plus complete : c'est le cas de s22, dont seul le fil principal a survecu.

Usage :
    python outils/rapport-tokens-journal-test.py
"""

import contextlib
import importlib.util
import io
import json
import sys
import tempfile
import unittest
from datetime import datetime, timezone
from pathlib import Path


def charger_module():
    chemin = Path(__file__).resolve().parent / "rapport-tokens.py"
    specification = importlib.util.spec_from_file_location("rapport_tokens", chemin)
    module = importlib.util.module_from_spec(specification)
    specification.loader.exec_module(module)
    return module


MODULE = charger_module()

LIGNE_TOTAL = "| **Total** | | **7** | **10** | **20** | **30** | **40** |"
LIGNE_MARC = "| `marc-dev` | m | 7 | 10 | 20 | 30 | 40 |"
LIGNE_LEA = "| `lea-dev` | m | 3 | 1 | 2 | 3 | 4 |"
SECTION_CHIFFREE = "\n".join(["| Agent | Modele |", "|---|---|", LIGNE_MARC, LIGNE_TOTAL])


def fenetre(identifiant, dossier):
    return {
        "id": identifiant,
        "dossier": dossier,
        "debut": datetime(2026, 1, 1, tzinfo=timezone.utc),
        "fin": datetime(2026, 1, 2, tzinfo=timezone.utc),
        "en_cours": False,
    }


def journal_existant(*sections):
    corps = "# Journal de consommation de tokens\n\n"
    for titre, contenu in sections:
        corps += f"## {titre}\n\n{contenu}\n\n"
    return corps


class LeJournalEtLesRapportsConserventUneMesureEcrite(unittest.TestCase):
    def setUp(self):
        self.bac = tempfile.TemporaryDirectory()
        self.addCleanup(self.bac.cleanup)
        self.repo = Path(self.bac.name)
        (self.repo / "squad" / "rapports").mkdir(parents=True)
        self.journal = self.repo / "squad" / "rapports" / "JOURNAL-tokens.md"

    def regenerer_journal(self, fenetres, resultats):
        sortie = io.StringIO()
        with contextlib.redirect_stdout(sortie):
            MODULE.ecrire_journal(self.repo, fenetres, resultats, False)
        return self.journal.read_text(encoding="utf-8"), sortie.getvalue()

    def test_1_le_journal_conserve_la_section_chiffree_sans_transcription(self):
        self.journal.write_text(
            journal_existant(("Sprint s22", SECTION_CHIFFREE)), encoding="utf-8"
        )
        texte, _ = self.regenerer_journal([fenetre("s22", self.repo)], {})
        self.assertIn(LIGNE_TOTAL, texte)
        self.assertNotIn("Aucune consommation mesuree", texte)

    def test_2_la_sortie_nomme_les_sections_conservees(self):
        self.journal.write_text(
            journal_existant(("Sprint s22", SECTION_CHIFFREE), ("Sprint s23", "Aucune consommation mesuree.")),
            encoding="utf-8",
        )
        _, sortie = self.regenerer_journal(
            [fenetre("s22", self.repo), fenetre("s23", self.repo)], {}
        )
        self.assertIn("s22", sortie)
        self.assertNotIn("s23", sortie)

    def test_3_un_sprint_mesure_est_recalcule(self):
        self.journal.write_text(
            journal_existant(("Sprint s22", SECTION_CHIFFREE)), encoding="utf-8"
        )
        compteurs = MODULE.compteurs_vides()
        MODULE.cumuler(compteurs, {"input_tokens": 5, "output_tokens": 6}, "m")
        texte, sortie = self.regenerer_journal(
            [fenetre("s22", self.repo)], {("s22", "marc-dev"): compteurs}
        )
        self.assertNotIn(LIGNE_TOTAL, texte)
        self.assertIn("| **Total** | | **1** | **5** | **6** |", texte)
        self.assertNotIn("conservees", sortie)

    def test_4_une_section_sans_chiffres_reste_aucune_consommation_mesuree(self):
        self.journal.write_text(
            journal_existant(("Sprint s22", "Aucune consommation mesuree.")), encoding="utf-8"
        )
        texte, _ = self.regenerer_journal([fenetre("s22", self.repo)], {})
        self.assertIn("## Sprint s22\n\nAucune consommation mesuree.", texte)
        self.assertNotIn("**Total**", texte)

    def test_5_un_rapport_de_sprint_chiffre_est_conserve(self):
        rapport = self.repo / "RAPPORT-tokens.md"
        original = f"# [Sprint s22] Consommation de tokens\n\n{SECTION_CHIFFREE}\n"
        rapport.write_text(original, encoding="utf-8")
        sortie = io.StringIO()
        with contextlib.redirect_stdout(sortie):
            MODULE.ecrire_rapport_sprint(fenetre("s22", self.repo), {}, False)
        self.assertEqual(original, rapport.read_text(encoding="utf-8"))
        self.assertIn("conserve", sortie.getvalue())

    def test_6_un_lancement_sans_sprint_ne_modifie_aucun_rapport_chiffre(self):
        dossier = self.repo / "squad" / "sprints" / "s22-exemple"
        dossier.mkdir(parents=True)
        (dossier / "planning.md").write_text("- **Horodatage de debut** : 2026-01-01 10:00\n", encoding="utf-8")
        (dossier / "review.md").write_text("- **Horodatage de fin** : 2026-01-01 18:00\n", encoding="utf-8")
        rapport = dossier / "RAPPORT-tokens.md"
        original = f"# [Sprint s22] Consommation de tokens\n\n{SECTION_CHIFFREE}\n"
        rapport.write_text(original, encoding="utf-8")
        self.journal.write_text(journal_existant(("Sprint s22", SECTION_CHIFFREE)), encoding="utf-8")

        # Un dossier de transcriptions non vide, dont aucun message ne tombe dans la fenetre de s22.
        transcriptions = self.repo / "transcriptions"
        transcriptions.mkdir()
        message = {
            "timestamp": "2030-01-01T00:00:00Z",
            "message": {"model": "m", "usage": {"input_tokens": 1, "output_tokens": 1}},
        }
        (transcriptions / "session.jsonl").write_text(json.dumps(message) + "\n", encoding="utf-8")

        argv = sys.argv
        sys.argv = ["rapport-tokens.py", "--repo", str(self.repo), "--projects-dir", str(transcriptions)]
        try:
            with contextlib.redirect_stdout(io.StringIO()):
                code = MODULE.main()
        finally:
            sys.argv = argv
        self.assertEqual(0, code)
        self.assertEqual(original, rapport.read_text(encoding="utf-8"))
        self.assertIn(LIGNE_TOTAL, self.journal.read_text(encoding="utf-8"))

    def test_7_une_mesure_partielle_ne_remplace_pas_une_section_plus_complete(self):
        deux_agents = "\n".join(
            ["| Agent | Modele |", "|---|---|", LIGNE_MARC, LIGNE_LEA, LIGNE_TOTAL]
        )
        self.journal.write_text(journal_existant(("Sprint s22", deux_agents)), encoding="utf-8")
        compteurs = MODULE.compteurs_vides()
        MODULE.cumuler(compteurs, {"input_tokens": 1}, "m")
        texte, sortie = self.regenerer_journal(
            [fenetre("s22", self.repo)], {("s22", "marc-dev"): compteurs}
        )
        self.assertIn(LIGNE_LEA, texte)
        self.assertIn("s22", sortie)


if __name__ == "__main__":
    unittest.main()
