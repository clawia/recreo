#!/usr/bin/env python
"""Verifie que `lire_horodatage` (outils/rapport-tokens.py) retient le DERNIER horodatage
d'une etiquette, pas le premier.

Motif (retro de s22) : depuis l'amendement de squad/README.md section 2, un `review.md` non
encore clos peut recevoir plusieurs ajouts dates, chacun capable de porter sa propre ligne
"Horodatage de fin". Si la fonction retenait le premier match, la fenetre de mesure des tokens
resterait figee sur la premiere tentative de cloture d'un sprint, alors qu'un travail reel et
substantiel se poursuit ensuite dans le meme sprint - exactement l'"ecart muet" que ce depot
sanctionne ailleurs (voir CLAUDE.md, section "Prerequis de build").

Usage :
    python outils/rapport-tokens-horodatage-test.py
"""

import importlib.util
import sys
import tempfile
import unittest
from pathlib import Path

MODULE_PATH = Path(__file__).resolve().parent / "rapport-tokens.py"


def charger_module():
    spec = importlib.util.spec_from_file_location("rapport_tokens", MODULE_PATH)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


rapport_tokens = charger_module()


class LireHorodatageRetientLeDernierMatch(unittest.TestCase):
    def ecrire(self, contenu):
        fichier = Path(self._dossier.name) / "review.md"
        fichier.write_text(contenu, encoding="utf-8")
        return fichier

    def setUp(self):
        self._dossier = tempfile.TemporaryDirectory()
        self.addCleanup(self._dossier.cleanup)

    def test_une_seule_ligne_fin_est_retenue(self):
        fichier = self.ecrire(
            "# Review\n\n- **Horodatage de fin** : 2026-08-29 20:32\n"
        )
        horodatage = rapport_tokens.lire_horodatage(fichier, "fin")
        self.assertIsNotNone(horodatage)

    def test_deux_lignes_fin_retient_la_derniere_pas_la_premiere(self):
        fichier = self.ecrire(
            "# Review\n\n"
            "- **Horodatage de fin** : 2026-08-29 20:32\n\n"
            "## Ajout date, cycle rouvert\n\n"
            "- **Horodatage de fin** : 2026-08-30 10:35\n"
        )
        premiere = rapport_tokens.lire_horodatage(fichier, "debut")
        derniere_fin = rapport_tokens.lire_horodatage(fichier, "fin")
        self.assertIsNone(premiere)
        self.assertEqual(derniere_fin.strftime("%Y-%m-%d"), "2026-08-30")

    def test_trois_lignes_fin_retient_la_troisieme(self):
        fichier = self.ecrire(
            "- **Horodatage de fin** : 2026-08-27 09:00\n"
            "- **Horodatage de fin** : 2026-08-28 09:00\n"
            "- **Horodatage de fin** : 2026-08-29 09:00\n"
        )
        derniere_fin = rapport_tokens.lire_horodatage(fichier, "fin")
        self.assertEqual(derniere_fin.strftime("%Y-%m-%d"), "2026-08-29")

    def test_fichier_absent_rend_none(self):
        introuvable = Path(self._dossier.name) / "absent.md"
        self.assertIsNone(rapport_tokens.lire_horodatage(introuvable, "fin"))

    def test_aucune_ligne_correspondante_rend_none(self):
        fichier = self.ecrire("# Review\n\nAucun horodatage ici.\n")
        self.assertIsNone(rapport_tokens.lire_horodatage(fichier, "fin"))


if __name__ == "__main__":
    resultat = unittest.main(exit=False)
    sys.exit(0 if resultat.result.wasSuccessful() else 1)
