#!/usr/bin/env python
"""Verifie que `outils/rapport-tokens.py` mesure le bon arbre depuis un worktree, et qu'il refuse
plutot que de rendre un rapport vide d'apparence normale.

Motif (retro de s24, section 2.4). `python outils/rapport-tokens.py --sprint s24` lance depuis le
worktree de review rendait "aucune invocation mesuree" pour les neuf agents : le script deduit le
dossier des transcriptions du chemin du depot, un worktree a un autre chemin, donc un autre
dossier, et ce dossier-la est vide. La commande reussissait, le rapport s'ecrivait, et il decrivait
un autre arbre que celui du sprint. Ce n'est pas un zero invente, c'est pire - un rapport
d'apparence normale.

Les deux proprietes testees ici sont celles qui ferment ce mode de defaillance :
  - la racine principale est retrouvee depuis un worktree, sans lancer git ;
  - un dossier de transcriptions absent ou vide fait refuser, jamais rendre un rapport.

Usage :
    python outils/rapport-tokens-worktree-test.py
"""

import importlib.util
import sys
import tempfile
import unittest
from pathlib import Path


def charger_module():
    chemin = Path(__file__).resolve().parent / "rapport-tokens.py"
    specification = importlib.util.spec_from_file_location("rapport_tokens", chemin)
    module = importlib.util.module_from_spec(specification)
    specification.loader.exec_module(module)
    return module


MODULE = charger_module()


class LaRacinePrincipaleEstRetrouveeDepuisUnWorktree(unittest.TestCase):
    def setUp(self):
        self.bac = tempfile.TemporaryDirectory()
        self.racine = Path(self.bac.name)
        self.addCleanup(self.bac.cleanup)

    def test_un_depot_ordinaire_est_rendu_inchange(self):
        depot = self.racine / "depot"
        (depot / ".git").mkdir(parents=True)
        self.assertEqual(MODULE.racine_principale_du_depot(depot), depot)

    def test_un_worktree_rend_la_racine_du_depot_principal(self):
        principal = self.racine / "recreo"
        (principal / ".git" / "worktrees" / "recreo-s25").mkdir(parents=True)
        worktree = self.racine / "recreo-s25"
        worktree.mkdir()
        (worktree / ".git").write_text(
            f"gitdir: {principal / '.git' / 'worktrees' / 'recreo-s25'}\n",
            encoding="utf-8",
        )
        self.assertEqual(MODULE.racine_principale_du_depot(worktree), principal)

    def test_le_dossier_deduit_change_avec_la_racine_et_c_est_la_le_defaut(self):
        """Le coeur du defaut : deux chemins, deux dossiers de transcriptions, un seul rempli."""
        principal = self.racine / "recreo"
        (principal / ".git" / "worktrees" / "recreo-s25").mkdir(parents=True)
        worktree = self.racine / "recreo-s25"
        worktree.mkdir()
        (worktree / ".git").write_text(
            f"gitdir: {principal / '.git' / 'worktrees' / 'recreo-s25'}\n",
            encoding="utf-8",
        )
        self.assertNotEqual(
            MODULE.encoder_chemin_projet(worktree),
            MODULE.encoder_chemin_projet(principal),
        )
        self.assertEqual(
            MODULE.encoder_chemin_projet(MODULE.racine_principale_du_depot(worktree)),
            MODULE.encoder_chemin_projet(principal),
        )

    def test_un_git_absent_ne_fait_rien_deviner(self):
        depot = self.racine / "exporte"
        depot.mkdir()
        self.assertEqual(MODULE.racine_principale_du_depot(depot), depot)

    def test_un_git_de_forme_inconnue_rend_le_depot_sans_planter(self):
        depot = self.racine / "bizarre"
        depot.mkdir()
        (depot / ".git").write_text("ceci n'est pas une ligne gitdir\n", encoding="utf-8")
        self.assertEqual(MODULE.racine_principale_du_depot(depot), depot)


class UnDossierSansTranscriptionFaitRefuser(unittest.TestCase):
    def setUp(self):
        self.bac = tempfile.TemporaryDirectory()
        self.racine = Path(self.bac.name)
        self.addCleanup(self.bac.cleanup)

    def test_un_dossier_absent_est_refuse(self):
        self.assertEqual(
            MODULE.verdict_de_collecte(self.racine / "jamais-cree"), "dossier absent"
        )

    def test_un_dossier_vide_est_refuse_c_est_le_cas_mesure_a_s24(self):
        vide = self.racine / "vide"
        vide.mkdir()
        self.assertEqual(MODULE.verdict_de_collecte(vide), "dossier vide")

    def test_un_dossier_sans_jsonl_mais_avec_des_fichiers_est_refuse(self):
        trompeur = self.racine / "trompeur"
        trompeur.mkdir()
        (trompeur / "note.txt").write_text("rien", encoding="utf-8")
        self.assertEqual(MODULE.verdict_de_collecte(trompeur), "dossier vide")

    def test_une_session_du_fil_principal_suffit_a_rendre_utilisable(self):
        plein = self.racine / "plein"
        plein.mkdir()
        (plein / "session.jsonl").write_text("{}\n", encoding="utf-8")
        self.assertEqual(MODULE.verdict_de_collecte(plein), "utilisable")

    def test_des_sous_agents_seuls_suffisent_aussi(self):
        plein = self.racine / "sous-agents-seuls"
        (plein / "session" / "subagents").mkdir(parents=True)
        self.assertEqual(MODULE.verdict_de_collecte(plein), "utilisable")


if __name__ == "__main__":
    resultat = unittest.main(exit=False, verbosity=2).result
    sys.exit(0 if resultat.wasSuccessful() else 1)
