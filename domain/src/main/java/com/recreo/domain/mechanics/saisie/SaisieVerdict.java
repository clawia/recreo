package com.recreo.domain.mechanics.saisie;

import com.recreo.domain.content.ExigenceDeForme;

import java.util.Optional;

/**
 * Le resultat d'une manche resolue de saisie, avec ce qu'il faut pour que l'ecran puisse rendre ce
 * que nadia-prof demande apres une reponse - et rien de plus.
 *
 * <p>ADR-0024, motif repris tel quel : le verdict porte la forme de reference et l'explication de
 * l'item qui vient d'etre joue, parce qu'il en est la seule trace une fois la partie terminee. Un
 * accesseur separe sur la session aurait exige un champ parallele a tenir en phase avec ce verdict
 * a chacune de ses constructions - la forme exacte du defaut d'ADR-0016.</p>
 *
 * <p><b>ADR-0058, amendement du 2026-08-25 : {@code chaineJugee} et {@code exigenceDeForme}
 * voyagent ici et nulle part ailleurs.</b> {@code SAI-2c} demande que la chaine jugee soit montree
 * <b>aussi</b> quand la reponse est bonne, et a ce moment-la la session a deja tire la manche
 * suivante : la chaine n'y existe plus. Le verdict est le seul objet qui survive a la resolution
 * d'une manche.</p>
 *
 * <p><b>Deux composantes et non une</b>, et l'ADR ecarte nommement la forme courte - une seule
 * composante facultative dont la presence vaudrait "il faut la montrer". Elle logerait dans le
 * domaine une politique d'affichage, et elle rendrait indiscernables deux vacuites : "vide parce
 * que le paquet est tolerant" et "vide parce qu'un defaut a perdu la chaine". La chaine est donc
 * portee <b>a chaque resolution</b>, sans condition, et le mode declare par l'item resolu voyage a
 * cote. Ce que l'ecran en fait n'est decide ni ici ni par cette mecanique.</p>
 *
 * <p>{@code juste} dit si la manche <b>compte comme une reussite</b>, comme chez la mecanique
 * voisine : une manche resolue par revelation porte {@code false} et ne fait pas monter le score.</p>
 */
public record SaisieVerdict(boolean juste, String formeDeReference, Optional<String> explication,
                             String chaineJugee, ExigenceDeForme exigenceDeForme) {
}
