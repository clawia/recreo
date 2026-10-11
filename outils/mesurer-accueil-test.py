#!/usr/bin/env python
"""Eprouve les juges purs d'outils/mesurer-accueil.py (HS-ACCUEIL-MESURE, ADR-0009) avec des entrees
fabriquees : aucun navigateur, aucun serveur, aucun chemin de poste.

Ce que ces tests prouvent, et ce qu'ils ne prouvent pas. Ils prouvent que les verdicts (carte visible,
completude, seuils, lecture du catalogue sur disque) rendent le bon resultat, y compris aux bords. Ils ne
prouvent pas que l'enveloppe pilote Chrome correctement : cette preuve est manuelle, par une mesure de
l'arbre de base et d'un arbre mute, citees dans le rapport qualite du chantier.

Usage :
    python outils/mesurer-accueil-test.py
"""

import importlib.util
import sys
import tempfile
import unittest
from pathlib import Path

CHEMIN = Path(__file__).resolve().parent / "mesurer-accueil.py"


def charger_module():
    spec = importlib.util.spec_from_file_location("mesurer_accueil", CHEMIN)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


ma = charger_module()

L, H = 744, 551


def carte(identifiant, top, left=0, bottom=None, right=None):
    return {
        "id": identifiant,
        "top": top,
        "bottom": top + 56 if bottom is None else bottom,
        "left": left,
        "right": left + 200 if right is None else right,
        "height": 56,
    }


def ecran(ids, bandeaux=None, tuiles=None):
    return {"cartes": [carte(i, 10 + 68 * n) for n, i in enumerate(ids)], "bandeaux": bandeaux or [], "tuiles": tuiles or []}


ATTENDU = {"ids": ["a", "b", "c", "d"], "matieres": ["M1", "M2"]}


def mesure_conforme():
    return {
        "v2": ecran(
            ["a", "b", "c", "d"],
            bandeaux=[{"texte": "M1", "cartes": 2}, {"texte": "M2", "cartes": 2}],
        ),
        "etage1": ecran([], tuiles=[{"href": "/m1"}, {"href": "/m2"}]),
        "etages2": {"/m1": ecran(["a", "b"]), "/m2": ecran(["c", "d"])},
    }


class JugerInvocation(unittest.TestCase):
    def test_une_invocation_valide_n_a_aucun_motif(self):
        self.assertEqual([], ma.juger_invocation(744, 551, "http://localhost:8080"))

    def test_largeur_nulle_negative_ou_non_entiere_est_refusee(self):
        for largeur in (0, -1, "744", None):
            self.assertTrue(ma.juger_invocation(largeur, 551, "http://localhost:8080"), largeur)

    def test_hauteur_nulle_est_refusee(self):
        self.assertTrue(ma.juger_invocation(744, 0, "http://localhost:8080"))

    def test_une_url_sans_schema_ou_avec_chemin_est_refusee(self):
        for url in ("localhost:8080", "http://localhost:8080/niveaux/CP", "", None):
            self.assertTrue(ma.juger_invocation(744, 551, url), url)


class CarteEntierementVisible(unittest.TestCase):
    def test_le_bas_pile_sur_le_pli_est_visible(self):
        self.assertTrue(ma.carte_entierement_visible(carte("x", 495, bottom=H), L, H))

    def test_un_demi_pixel_sous_le_pli_ne_l_est_pas(self):
        self.assertFalse(ma.carte_entierement_visible(carte("x", 495, bottom=H + 0.5), L, H))

    def test_une_carte_au_dessus_de_la_fenetre_ne_l_est_pas(self):
        self.assertFalse(ma.carte_entierement_visible(carte("x", -1, bottom=55), L, H))

    def test_une_carte_qui_deborde_a_droite_ne_l_est_pas(self):
        self.assertFalse(ma.carte_entierement_visible(carte("x", 10, left=600, right=L + 1), L, H))

    def test_la_carte_coupee_par_le_pli_ne_compte_pas(self):
        cartes = [carte("a", 10), carte("b", 78), carte("c", 520, bottom=576)]
        self.assertEqual(2, ma.compter_visibles(cartes, L, H))

    def test_les_colonnes_sont_les_valeurs_distinctes_de_left(self):
        cartes = [carte("a", 10, left=12), carte("b", 10, left=372), carte("c", 78, left=12.2)]
        self.assertEqual(2, ma.colonnes(cartes))


class ComparerIds(unittest.TestCase):
    def test_conforme(self):
        self.assertEqual(([], [], []), ma.comparer_ids(["a", "b"], ["b", "a"]))

    def test_un_manquant_un_en_trop_un_doublon(self):
        self.assertEqual((["b"], ["z"], ["a"]), ma.comparer_ids(["a", "b"], ["a", "a", "z"]))

    def test_le_nom_d_un_jeu_inatteignable_est_dans_le_message(self):
        mesure = mesure_conforme()
        mesure["v2"]["cartes"] = [c for c in mesure["v2"]["cartes"] if c["id"] != "d"]
        ecarts = ma.juger_completude(ATTENDU, mesure)
        self.assertTrue(any("inatteignable" in e and "d" in e.split(":")[-1] for e in ecarts), ecarts)

    def test_la_liste_nommee_est_plafonnee_mais_donne_le_reste(self):
        texte = ma.nommer(["id%d" % n for n in range(25)], plafond=20)
        self.assertIn("(+5 autres)", texte)
        self.assertIn("id19", texte)
        self.assertNotIn("id20", texte)


class JugerCompletude(unittest.TestCase):
    def test_un_accueil_complet_n_a_aucun_ecart(self):
        self.assertEqual([], ma.juger_completude(ATTENDU, mesure_conforme()))

    def test_une_carte_retiree_de_la_vue_par_defaut_est_nommee(self):
        m = mesure_conforme()
        m["v2"]["cartes"].pop()
        self.assertTrue(ma.juger_completude(ATTENDU, m))

    def test_une_carte_retiree_d_un_etage_2_est_nommee(self):
        m = mesure_conforme()
        m["etages2"]["/m2"]["cartes"].pop()
        ecarts = ma.juger_completude(ATTENDU, m)
        self.assertEqual(1, len(ecarts), ecarts)
        self.assertIn("V1", ecarts[0])

    def test_une_tuile_retiree_est_un_ecart(self):
        m = mesure_conforme()
        m["etage1"]["tuiles"].pop()
        m["etages2"].pop("/m2")
        ecarts = ma.juger_completude(ATTENDU, m)
        self.assertTrue(any("tuile" in e for e in ecarts), ecarts)

    def test_un_bandeau_sans_carte_est_un_ecart(self):
        m = mesure_conforme()
        m["v2"]["bandeaux"][1]["cartes"] = 0
        self.assertTrue(any("sans carte" in e for e in ma.juger_completude(ATTENDU, m)))

    def test_un_bandeau_de_moins_que_de_matieres_est_un_ecart(self):
        m = mesure_conforme()
        m["v2"]["bandeaux"].pop()
        self.assertTrue(any("bandeau" in e for e in ma.juger_completude(ATTENDU, m)))

    def test_une_carte_en_double_est_un_ecart(self):
        m = mesure_conforme()
        m["v2"]["cartes"].append(carte("a", 400))
        self.assertTrue(any("double" in e for e in ma.juger_completude(ATTENDU, m)))

    def test_la_liste_plate_sans_bandeau_ni_bascule_n_est_jugee_que_sur_ses_cartes(self):
        m = {"v2": ecran(["a", "b", "c", "d"]), "etage1": None, "etages2": {}}
        self.assertEqual([], ma.juger_completude(ATTENDU, m))


class JugerSeuils(unittest.TestCase):
    def v2_de(self, n):
        return {"v2": ecran(["x%d" % i for i in range(n)]), "etages2": {}}

    def test_sans_seuil_demande_rien_n_est_juge(self):
        self.assertEqual([], ma.juger_seuils(self.v2_de(1), L, H))

    def test_le_seuil_est_inclusif(self):
        # 10 cartes sur 56 px au pas de 68 : la 9e a son bas a 10 + 8 * 68 + 56 = 610 > 551 ; on fabrique
        # donc les positions pour que toutes soient dans la fenetre.
        m = {"v2": {"cartes": [carte("x%d" % i, 10, left=i) for i in range(10)]}, "etages2": {}}
        self.assertEqual([], ma.juger_seuils(m, L, H, seuil_v2=10))
        m["v2"]["cartes"].pop()
        self.assertTrue(ma.juger_seuils(m, L, H, seuil_v2=10))

    def test_le_seuil_d_un_etage_2_est_min_de_n_et_du_seuil(self):
        petit = {"v2": ecran([]), "etages2": {"/m": {"cartes": [carte("a", 10), carte("b", 78)]}}}
        self.assertEqual([], ma.juger_seuils(petit, L, H, seuil_etage2=6))
        coupe = {"v2": ecran([]), "etages2": {"/m": {"cartes": [carte("a", 10), carte("b", 520, bottom=576)]}}}
        ecarts = ma.juger_seuils(coupe, L, H, seuil_etage2=6)
        self.assertEqual(1, len(ecarts))
        self.assertIn("min(n, 6) = 2", ecarts[0])


class MatieresNommees(unittest.TestCase):
    def test_le_texte_entier_d_un_element_compte(self):
        self.assertEqual(1, ma.matieres_nommees(["Maths", "Autre"], ["Mathématiques"], {"Mathématiques": "Maths"}))

    def test_un_mot_dans_une_phrase_ne_compte_pas(self):
        self.assertEqual(0, ma.matieres_nommees(["Pour le CP", "Maths pour tous"], ["Mathématiques"], {"Mathématiques": "Maths"}))

    def test_la_valeur_du_descripteur_compte_sans_libelle_court(self):
        self.assertEqual(1, ma.matieres_nommees(["Français"], ["Français"], {}))

    def test_la_liste_actuelle_sans_matiere_en_nomme_zero(self):
        self.assertEqual(0, ma.matieres_nommees(["Pour le CP"], ["Français", "Mathématiques"], {}))


class LireAttendu(unittest.TestCase):
    def setUp(self):
        self._dossier = tempfile.TemporaryDirectory()
        self.addCleanup(self._dossier.cleanup)
        self.racine = Path(self._dossier.name)
        (self.racine / "contenu").mkdir()

    def ecrire(self, chemin, texte):
        (self.racine / chemin).write_text(texte, encoding="utf-8")

    def paquet(self, identifiant, niveau, matiere):
        self.ecrire(
            "contenu/%s.yaml" % identifiant,
            'schema: 1\nid: %s\ntype: question\ndescripteur:\n  matiere: "%s"\n  niveau: "%s"\n  domaine: "d"\nitems:\n  - id: i1\n'
            % (identifiant, matiere, niveau),
        )

    def test_joint_le_catalogue_aux_paquets_par_niveau_et_ordonne_les_matieres_comme_matieres_yaml(self):
        self.ecrire(
            "catalogue.yaml",
            "schema: 1\njeux:\n"
            "  - id: j1\n    titre: \"t\"\n    paquet: p1\n"
            "  - id: j2\n    titre: \"t\"\n    paquet: p2\n"
            "  - id: j3\n    titre: \"t\"\n    paquet: p3\n",
        )
        self.paquet("p1", "CP", "Mathématiques")
        self.paquet("p2", "CP", "Français")
        self.paquet("p3", "CE1", "Français")
        self.ecrire(
            "matieres.yaml",
            'matieres:\n  - matiere: "Français"\n    libelle: "Français"\n  - matiere: "Mathématiques"\n    libelle: "Maths"\n',
        )
        attendu, libelles = ma.lire_attendu(self.racine)
        self.assertEqual({"ids": ["j1", "j2"], "matieres": ["Français", "Mathématiques"]}, attendu["CP"])
        self.assertEqual({"ids": ["j3"], "matieres": ["Français"]}, attendu["CE1"])
        self.assertEqual("Maths", libelles["Mathématiques"])

    def test_sans_matieres_yaml_l_ordre_est_celui_de_premiere_apparition(self):
        self.ecrire(
            "catalogue.yaml",
            "jeux:\n  - id: j1\n    paquet: p1\n  - id: j2\n    paquet: p2\n",
        )
        self.paquet("p1", "CP", "Mathématiques")
        self.paquet("p2", "CP", "Français")
        attendu, libelles = ma.lire_attendu(self.racine)
        self.assertEqual(["Mathématiques", "Français"], attendu["CP"]["matieres"])
        self.assertEqual({}, libelles)

    def test_un_paquet_introuvable_est_une_erreur_et_non_un_jeu_ignore(self):
        self.ecrire("catalogue.yaml", "jeux:\n  - id: j1\n    paquet: fantome\n")
        with self.assertRaises(ValueError):
            ma.lire_attendu(self.racine)


if __name__ == "__main__":
    unittest.main(argv=[sys.argv[0]], verbosity=1)
