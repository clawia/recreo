#!/usr/bin/env python
"""Eprouve le juge pur outils/juger-pli-deux-oeuvres.py (J1 a J5, avis-theo-poste-8-art-cp-10.md, 4.2) avec
des releves fabriques : aucun navigateur, aucun jar, aucun chemin de poste.

Chaque regle est eprouvee des deux cotes : un releve conforme passe, puis une seule violation fabriquee la
fait rougir (et seulement elle). Ce que ces tests prouvent : les verdicts, aux bords (551 et 552, 1 px, 0,1 px).
Ce qu'ils ne prouvent pas : que le releve d'un jar reel est tel que le pilote l'ecrit. Cette preuve est la
mesure de B2 jugee par le script, citee dans le rapport qualite.

Usage :
    python outils/juger-pli-deux-oeuvres-test.py
"""

import contextlib
import importlib.util
import io
import json
import sys
import tempfile
import unittest
from pathlib import Path

CHEMIN = Path(__file__).resolve().parent / "juger-pli-deux-oeuvres.py"


def charger_module():
    spec = importlib.util.spec_from_file_location("juger_pli_deux_oeuvres", CHEMIN)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


jp = charger_module()

NB_ITEMS = 3
LARGEURS = (720, 744, 768, 992, 1200)


def boite(top, left, w, h):
    return {"top": top, "bottom": top + h, "left": left, "right": left + w, "w": w, "h": h}


def mesure(nom, largeur, essayees, m4_bas=None, indice_bas=None, marques_bas=None):
    """Une mesure conforme : cases immobiles, cabinet dont seule la hauteur depend de la presence d'une
    case essayee (O1), message sous le cabinet et dans ses bords."""
    cab_h = 224 if essayees else 184
    cabinet = boite(240, 224, 496, cab_h)
    cases = [
        {"cls": "deja-essayee" if i < essayees else "", "rev": False, "box": boite(260, 244 + 156 * i, 144, 144)}
        for i in range(3)
    ]
    m = {
        "innerW": largeur, "innerH": 551, "cabinet": cabinet, "cases": cases,
        "messageJeu": None, "m4": None, "indice": None, "marques": [],
    }
    if nom != "fraiche":
        m["messageJeu"] = boite(cabinet["bottom"], cabinet["left"], 496, 84)
    if nom == "revelation":
        m["m4"] = boite(468, 468, 244, 60)
        if m4_bas is not None:
            m["m4"]["bottom"] = m4_bas
    if nom.startswith("echec"):
        m["indice"] = boite(468, 468, 244, 80)
        m["indice"]["bottom"] = 548 if indice_bas is None else indice_bas
        m["marques"] = [
            {"top": 406, "bottom": 446 if marques_bas is None else marques_bas, "w": 40, "h": 40}
            for _ in range(essayees)
        ]
    return m


def item(largeur):
    return {
        "fraiche": mesure("fraiche", largeur, 0),
        "echec1": mesure("echec1", largeur, 1),
        "echec2": mesure("echec2", largeur, 2),
        "juste1": mesure("juste1", largeur, 0),
        "revelation": mesure("revelation", largeur, 2),
        "sh_fraiche": 1,
    }


def releve_conforme(largeurs=LARGEURS, nb_items=NB_ITEMS):
    return {str(w): {"/oeuvres/oeuvre-%d.jpg" % i: item(w) for i in range(nb_items)} for w in largeurs}


def verdict(releve, code, nb_items=NB_ITEMS):
    return {v.code: v for v in jp.juger(releve, nb_items)}[code]


def tous(releve, nb_items=NB_ITEMS):
    return {v.code: v.ok for v in jp.juger(releve, nb_items)}


class Conforme(unittest.TestCase):
    def test_un_releve_conforme_passe_les_cinq_regles(self):
        self.assertEqual({"J1": True, "J2": True, "J3": True, "J4": True, "J5": True}, tous(releve_conforme()))


class J1Completude(unittest.TestCase):
    def test_une_hauteur_de_fenetre_autre_que_551_sur_une_seule_mesure_est_rouge(self):
        r = releve_conforme()
        r["992"]["/oeuvres/oeuvre-1.jpg"]["echec2"]["innerH"] = 600
        v = verdict(r, "J1")
        self.assertFalse(v.ok)
        self.assertTrue(any("600" in ligne for ligne in v.lignes), v.lignes)

    def test_la_largeur_744_absente_est_rouge(self):
        r = releve_conforme()
        del r["744"]
        self.assertFalse(verdict(r, "J1").ok)

    def test_la_largeur_992_absente_est_rouge(self):
        r = releve_conforme()
        del r["992"]
        self.assertFalse(verdict(r, "J1").ok)

    def test_les_largeurs_768_1200_et_720_ne_sont_pas_exigees(self):
        self.assertTrue(verdict(releve_conforme(largeurs=(744, 992)), "J1").ok)

    def test_un_item_de_moins_que_le_paquet_est_rouge_a_une_seule_largeur(self):
        r = releve_conforme()
        del r["768"]["/oeuvres/oeuvre-2.jpg"]
        v = verdict(r, "J1")
        self.assertFalse(v.ok)
        self.assertTrue(any(ligne.startswith("768 :") for ligne in v.lignes), v.lignes)

    def test_le_compte_des_items_vient_du_paquet_et_non_du_juge(self):
        self.assertTrue(verdict(releve_conforme(nb_items=3), "J1", nb_items=3).ok)
        self.assertFalse(verdict(releve_conforme(nb_items=3), "J1", nb_items=4).ok)
        self.assertTrue(verdict(releve_conforme(nb_items=7), "J1", nb_items=7).ok)

    def test_un_item_sans_fraiche_echec1_ou_revelation_est_rouge(self):
        for etat in ("fraiche", "echec1", "revelation"):
            r = releve_conforme()
            del r["744"]["/oeuvres/oeuvre-0.jpg"][etat]
            v = verdict(r, "J1")
            self.assertFalse(v.ok, etat)
            self.assertTrue(any(etat in ligne for ligne in v.lignes), (etat, v.lignes))

    def test_echec2_et_juste1_ne_sont_pas_exiges(self):
        r = releve_conforme()
        for items in r.values():
            for it in items.values():
                del it["echec2"]
                del it["juste1"]
        self.assertTrue(verdict(r, "J1").ok)

    def test_un_releve_qui_n_a_pas_la_forme_du_pilote_est_rouge(self):
        for r in ([{"largeur": 720, "variante": "A"}], {}, {"744": []}, "texte"):
            self.assertFalse(verdict(r, "J1").ok, r)


class J2BarreDuRefus(unittest.TestCase):
    def test_le_bas_de_m4_a_551_passe_et_a_552_est_rouge(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["revelation"]["m4"]["bottom"] = 551
        self.assertTrue(verdict(r, "J2").ok)
        r["744"]["/oeuvres/oeuvre-0.jpg"]["revelation"]["m4"]["bottom"] = 552
        v = verdict(r, "J2")
        self.assertFalse(v.ok)
        self.assertTrue(any("552" in ligne for ligne in v.lignes), v.lignes)

    def test_un_seul_item_trop_bas_suffit(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-2.jpg"]["revelation"]["m4"]["bottom"] = 568
        self.assertFalse(verdict(r, "J2").ok)

    def test_le_bas_de_m4_n_est_juge_qu_a_744(self):
        r = releve_conforme()
        for w in ("720", "768", "992", "1200"):
            for it in r[w].values():
                it["revelation"]["m4"]["bottom"] = 700
        self.assertTrue(verdict(r, "J2").ok)

    def test_un_m4_absent_a_la_revelation_est_rouge(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["revelation"]["m4"] = None
        self.assertFalse(verdict(r, "J2").ok)

    def test_la_largeur_744_absente_n_est_pas_un_vert(self):
        r = releve_conforme()
        del r["744"]
        self.assertFalse(verdict(r, "J2").ok)

    def test_cotes_du_refus_de_s56_656_676_et_568_sont_rouges(self):
        r = releve_conforme()
        for i, bas in enumerate((656, 676, 568)):
            r["744"]["/oeuvres/oeuvre-%d.jpg" % i]["revelation"]["m4"]["bottom"] = bas
        v = verdict(r, "J2")
        self.assertFalse(v.ok)
        for cote in ("656", "676", "568"):
            self.assertTrue(any(cote in ligne for ligne in v.lignes), (cote, v.lignes))


class J3RdoCpR1(unittest.TestCase):
    def test_le_bas_de_indice_a_744_passe_a_551_et_rougit_a_552(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["indice"]["bottom"] = 551
        self.assertTrue(verdict(r, "J3").ok)
        r["744"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["indice"]["bottom"] = 552
        self.assertFalse(verdict(r, "J3").ok)

    def test_l_indice_du_second_echec_est_juge_aussi(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-1.jpg"]["echec2"]["indice"]["bottom"] = 560
        self.assertFalse(verdict(r, "J3").ok)

    def test_le_bas_d_une_marque_a_992_passe_a_551_et_rougit_a_552(self):
        r = releve_conforme()
        r["992"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["marques"][0]["bottom"] = 551
        self.assertTrue(verdict(r, "J3").ok)
        r["992"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["marques"][0]["bottom"] = 552
        v = verdict(r, "J3")
        self.assertFalse(v.ok)
        self.assertTrue(any("552" in ligne for ligne in v.lignes), v.lignes)

    def test_une_seule_marque_trop_basse_du_second_echec_suffit(self):
        r = releve_conforme()
        r["992"]["/oeuvres/oeuvre-0.jpg"]["echec2"]["marques"][1]["bottom"] = 600
        self.assertFalse(verdict(r, "J3").ok)

    def test_l_indice_n_est_pas_juge_a_992_ni_les_marques_a_744(self):
        r = releve_conforme()
        for it in r["992"].values():
            it["echec1"]["indice"]["bottom"] = 700
        for it in r["744"].values():
            it["echec1"]["marques"][0]["bottom"] = 700
        self.assertTrue(verdict(r, "J3").ok)

    def test_un_indice_ou_des_marques_absents_a_un_echec_sont_rouges(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["indice"] = None
        self.assertFalse(verdict(r, "J3").ok)
        r = releve_conforme()
        r["992"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["marques"] = []
        self.assertFalse(verdict(r, "J3").ok)

    def test_un_echec_de_j3_seul_laisse_j2_vert_et_est_marque_reserve(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["indice"]["bottom"] = 560
        resultats = tous(r)
        self.assertEqual({"J1": True, "J2": True, "J3": False, "J4": True, "J5": True}, resultats)
        self.assertTrue(verdict(r, "J3").reserve)
        self.assertFalse(verdict(r, "J2").reserve)

    def test_un_echec_de_j2_n_est_pas_une_reserve_et_ne_rougit_pas_j3(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["revelation"]["m4"]["bottom"] = 600
        self.assertEqual({"J1": True, "J2": False, "J3": True, "J4": True, "J5": True}, tous(r))


class J4VetoDeNadia(unittest.TestCase):
    def test_un_message_colle_sous_le_cabinet_et_dans_ses_bords_passe(self):
        v = verdict(releve_conforme(), "J4")
        self.assertTrue(v.ok)
        self.assertIn("60 mesures", v.resume)

    def test_un_message_qui_commence_a_1_px_au_dessus_du_bas_du_cabinet_passe_et_a_2_px_rougit(self):
        r = releve_conforme()
        m = r["744"]["/oeuvres/oeuvre-0.jpg"]["echec1"]
        m["messageJeu"]["top"] = m["cabinet"]["bottom"] - 1
        self.assertTrue(verdict(r, "J4").ok)
        m["messageJeu"]["top"] = m["cabinet"]["bottom"] - 2
        self.assertFalse(verdict(r, "J4").ok)

    def test_un_bord_gauche_a_1_px_en_dehors_passe_et_a_2_px_rougit(self):
        r = releve_conforme()
        m = r["992"]["/oeuvres/oeuvre-0.jpg"]["revelation"]
        m["messageJeu"]["left"] = m["cabinet"]["left"] - 1
        self.assertTrue(verdict(r, "J4").ok)
        m["messageJeu"]["left"] = m["cabinet"]["left"] - 2
        self.assertFalse(verdict(r, "J4").ok)

    def test_un_bord_droit_a_1_px_en_dehors_passe_et_a_2_px_rougit(self):
        r = releve_conforme()
        m = r["768"]["/oeuvres/oeuvre-0.jpg"]["echec2"]
        m["messageJeu"]["right"] = m["cabinet"]["right"] + 1
        self.assertTrue(verdict(r, "J4").ok)
        m["messageJeu"]["right"] = m["cabinet"]["right"] + 2
        self.assertFalse(verdict(r, "J4").ok)

    def test_la_forme_p_mesuree_signal_a_x_16_5_pour_un_meuble_a_216_5_est_rouge(self):
        r = releve_conforme()
        m = r["744"]["/oeuvres/oeuvre-0.jpg"]["echec1"]
        m["cabinet"].update(left=216.5, right=712.5)
        m["messageJeu"].update(left=16.5, right=712.5)
        v = verdict(r, "J4")
        self.assertFalse(v.ok)
        self.assertTrue(any("16.5" in ligne and "216.5" in ligne for ligne in v.lignes), v.lignes)

    def test_la_regle_s_applique_des_720_et_pas_en_dessous(self):
        r = releve_conforme(largeurs=(719, 744, 992))
        for it in r["719"].values():
            for m in it.values():
                if isinstance(m, dict) and m.get("messageJeu"):
                    m["messageJeu"]["left"] = 0
        self.assertTrue(verdict(r, "J4").ok)
        r = releve_conforme(largeurs=(720, 744, 992))
        for it in r["720"].values():
            it["echec1"]["messageJeu"]["left"] = 0
        self.assertFalse(verdict(r, "J4").ok)

    def test_un_message_absent_apres_une_reponse_est_rouge(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["messageJeu"] = None
        self.assertFalse(verdict(r, "J4").ok)

    def test_la_fraiche_n_a_pas_de_message_et_n_est_pas_jugee(self):
        self.assertIsNone(releve_conforme()["744"]["/oeuvres/oeuvre-0.jpg"]["fraiche"]["messageJeu"])
        self.assertTrue(verdict(releve_conforme(), "J4").ok)

    def test_un_releve_sans_aucune_largeur_de_grille_n_est_pas_un_vert(self):
        self.assertFalse(verdict(releve_conforme(largeurs=(500, 600)), "J4").ok)


class J5Geometrie(unittest.TestCase):
    def modifier(self, largeur, etat, fn):
        r = releve_conforme()
        fn(r[str(largeur)]["/oeuvres/oeuvre-1.jpg"][etat])
        return r

    def test_le_haut_d_une_case_qui_bouge_est_rouge(self):
        r = self.modifier(744, "echec1", lambda m: m["cases"][2]["box"].update(top=261))
        v = verdict(r, "J5")
        self.assertFalse(v.ok)
        self.assertTrue(any("cases.haut" in ligne for ligne in v.lignes), v.lignes)

    def test_la_largeur_d_une_case_qui_bouge_est_rouge(self):
        r = self.modifier(744, "revelation", lambda m: m["cases"][0]["box"].update(w=139))
        self.assertTrue(any("cases.largeur" in ligne for ligne in verdict(r, "J5").lignes))

    def test_la_hauteur_d_une_case_qui_bouge_est_rouge(self):
        r = self.modifier(992, "juste1", lambda m: m["cases"][1]["box"].update(h=143.9))
        self.assertTrue(any("cases.hauteur" in ligne for ligne in verdict(r, "J5").lignes))

    def test_un_dixieme_de_pixel_suffit(self):
        r = self.modifier(744, "echec1", lambda m: m["cases"][0]["box"].update(top=260.1))
        self.assertFalse(verdict(r, "J5").ok)

    def test_le_decalage_lateral_des_cases_n_est_pas_juge(self):
        def decaler(m):
            for c in m["cases"]:
                c["box"]["left"] -= 7.5
                c["box"]["right"] -= 7.5

        self.assertTrue(verdict(self.modifier(744, "echec1", decaler), "J5").ok)

    def test_le_nombre_de_cases_qui_change_est_rouge(self):
        r = self.modifier(744, "echec1", lambda m: m["cases"].pop())
        self.assertFalse(verdict(r, "J5").ok)

    def test_le_haut_du_cabinet_qui_bouge_est_rouge(self):
        r = self.modifier(744, "echec1", lambda m: m["cabinet"].update(top=241))
        self.assertTrue(any("cabinet.haut" in ligne for ligne in verdict(r, "J5").lignes))

    def test_la_largeur_du_cabinet_qui_bouge_est_rouge(self):
        r = self.modifier(744, "echec2", lambda m: m["cabinet"].update(w=481))
        self.assertTrue(any("cabinet.largeur" in ligne for ligne in verdict(r, "J5").lignes))

    def test_le_bord_gauche_du_cabinet_qui_bouge_est_rouge(self):
        r = self.modifier(744, "echec1", lambda m: m["cabinet"].update(left=216.5))
        v = verdict(r, "J5")
        self.assertFalse(v.ok)
        self.assertTrue(any("cabinet.gauche" in ligne and "216.5" in ligne for ligne in v.lignes), v.lignes)

    def test_la_hauteur_du_cabinet_entre_deux_etats_avec_case_essayee_doit_etre_identique(self):
        r = self.modifier(744, "echec2", lambda m: m["cabinet"].update(h=268))
        v = verdict(r, "J5")
        self.assertFalse(v.ok)
        self.assertTrue(any("cabinet.hauteur" in ligne for ligne in v.lignes), v.lignes)

    def test_la_hauteur_du_cabinet_entre_deux_etats_sans_case_essayee_doit_etre_identique(self):
        r = self.modifier(744, "juste1", lambda m: m["cabinet"].update(h=190))
        self.assertTrue(any("cabinet.hauteur" in ligne for ligne in verdict(r, "J5").lignes))

    def test_la_hauteur_du_cabinet_peut_differer_entre_sans_et_avec_case_essayee(self):
        r = releve_conforme()
        cabinets = {
            nom: m["cabinet"]["h"] for nom, m in r["744"]["/oeuvres/oeuvre-0.jpg"].items() if isinstance(m, dict)
        }
        self.assertNotEqual(cabinets["fraiche"], cabinets["echec1"])
        self.assertTrue(verdict(r, "J5").ok)

    def test_une_case_essayee_s_entend_de_la_classe_deja_essayee(self):
        r = releve_conforme()
        for etat in ("echec1", "echec2", "revelation"):
            for c in r["744"]["/oeuvres/oeuvre-0.jpg"][etat]["cases"]:
                c["cls"] = ""
        self.assertFalse(verdict(r, "J5").ok)

    def test_une_derive_a_une_seule_largeur_est_nommee_a_cette_largeur(self):
        r = self.modifier(1200, "echec1", lambda m: m["cases"][0]["box"].update(top=300))
        v = verdict(r, "J5")
        self.assertFalse(v.ok)
        self.assertTrue(all(ligne.startswith("1200 ") for ligne in v.lignes), v.lignes)


class PaquetEtInvocation(unittest.TestCase):
    YAML = "schema: 1\nitems:\n" + "".join("  - enonce: x%d\n    indice: y\n" % i for i in range(5)) + "suite: 1\n"

    def test_le_compte_des_items_se_lit_dans_la_liste_items_du_paquet(self):
        self.assertEqual(5, jp.compter_items_paquet(self.YAML))
        self.assertEqual(2, jp.compter_items_paquet("items:\n  - a: 1\n  - a: 2\n"))

    def test_une_entree_apres_la_liste_n_est_pas_comptee(self):
        self.assertEqual(1, jp.compter_items_paquet("items:\n  - a: 1\nautre:\n  - b: 2\n"))

    def test_un_paquet_sans_item_est_une_erreur_et_non_un_zero(self):
        with self.assertRaises(ValueError):
            jp.compter_items_paquet("schema: 1\n")

    def test_le_paquet_du_depot_porte_au_moins_un_item(self):
        self.assertGreater(jp.compter_items_paquet(jp.PAQUET_PAR_DEFAUT.read_text(encoding="utf-8")), 0)


class Commande(unittest.TestCase):
    def setUp(self):
        self.tmp = tempfile.TemporaryDirectory()
        self.dir = Path(self.tmp.name)
        self.paquet = self.dir / "paquet.yaml"
        self.paquet.write_text("items:\n" + "  - a: 1\n" * NB_ITEMS, encoding="utf-8")

    def tearDown(self):
        self.tmp.cleanup()

    def lancer(self, releve, paquet=None):
        f = self.dir / "releve.json"
        f.write_text(releve if isinstance(releve, str) else json.dumps(releve), encoding="utf-8")
        sortie = io.StringIO()
        with contextlib.redirect_stdout(sortie), contextlib.redirect_stderr(io.StringIO()):
            code = jp.main([str(f), "--paquet", str(paquet or self.paquet)])
        return code, sortie.getvalue()

    def test_un_releve_conforme_sort_0_et_imprime_un_verdict_par_regle(self):
        code, sortie = self.lancer(releve_conforme())
        self.assertEqual(0, code)
        for j in ("J1 OK", "J2 OK", "J3 OK", "J4 OK", "J5 OK"):
            self.assertIn(j, sortie)

    def test_une_regle_rouge_sort_1_et_cite_la_cote(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["revelation"]["m4"]["bottom"] = 568
        code, sortie = self.lancer(r)
        self.assertEqual(1, code)
        self.assertIn("J2 ROUGE", sortie)
        self.assertIn("m4 = 568 > 551", sortie)

    def test_j3_rouge_est_imprime_comme_reserve_a_part_de_j2(self):
        r = releve_conforme()
        r["744"]["/oeuvres/oeuvre-0.jpg"]["echec1"]["indice"]["bottom"] = 560
        code, sortie = self.lancer(r)
        self.assertEqual(1, code)
        self.assertIn("J2 OK", sortie)
        self.assertIn("J3 ROUGE (reserve)", sortie)

    def test_un_releve_incomplet_sort_1(self):
        r = releve_conforme()
        del r["992"]
        self.assertEqual(1, self.lancer(r)[0])

    def test_un_releve_a_la_mauvaise_forme_sort_1_et_non_0(self):
        self.assertEqual(1, self.lancer([{"largeur": 720}])[0])

    def test_un_fichier_absent_un_json_invalide_ou_un_paquet_absent_sortent_2(self):
        with contextlib.redirect_stderr(io.StringIO()):
            code = jp.main([str(self.dir / "absent.json"), "--paquet", str(self.paquet)])
        self.assertEqual(2, code)
        self.assertEqual(2, self.lancer("{pas du json")[0])
        self.assertEqual(2, self.lancer(releve_conforme(), paquet=self.dir / "absent.yaml")[0])

    def test_le_compte_du_paquet_fait_foi_a_l_execution(self):
        self.paquet.write_text("items:\n" + "  - a: 1\n" * (NB_ITEMS + 1), encoding="utf-8")
        self.assertEqual(1, self.lancer(releve_conforme())[0])


if __name__ == "__main__":
    unittest.main(argv=[sys.argv[0]], verbosity=1)
