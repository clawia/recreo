package com.recreo.domain.mechanics.banditmanchot;

import java.util.Optional;

/**
 * Le resultat d'une manche resolue, avec la bonne reponse pour que l'ecran puisse la designer.
 *
 * <p>S10, M11 clause 3 : {@code juste} ne dit plus seulement si la reponse pressee etait la bonne,
 * il dit si la manche <b>compte comme une reussite</b>. Une manche resolue par revelation (M5 -
 * l'enfant presse la reponse que le jeu vient de nommer, apres trois echecs) porte {@code juste =
 * false} bien que le geste presse la bonne reponse : ce n'est pas une reussite, elle ne fait pas
 * monter le score et le jeu ne felicite pas l'enfant de l'avoir pressee.</p>
 *
 * <p>ADR-0024 : le verdict porte aussi l'explication de l'item qui vient d'etre joue, parce qu'il
 * est la seule trace de cet item une fois la partie terminee. Un accesseur separe sur la session,
 * sur le modele de {@code indice()}, aurait exige un champ parallele a tenir en phase avec ce
 * verdict a chacune des constructions de la session - la forme exacte du defaut d'ADR-0016. Ici, la
 * composante est obligatoire : aucun constructeur de commodite ne la laisse tomber, donc aucun site
 * ne peut construire un verdict sans decider de l'explication.</p>
 *
 * <p>{@code Optional.empty()} n'est pas un manque : c'est la reponse normale pour un item que le
 * contenu n'a pas juge digne d'explication. La mecanique ne fait aucune difference entre les deux
 * cas, elle transporte ce qu'elle recoit.</p>
 *
 * <p><b>ADR-0084, REV-3 : {@code enonce} voyage ici pour le meme motif exact qu'{@code explication},
 * et il est obligatoire.</b> L'ecran de fin de partie nommait une reponse - <i>"La bonne reponse :
 * non"</i> - sans porter la question qu'elle commente, parce qu'a cet instant la manche courante
 * n'existe plus : {@code resoudre} a mis {@code mancheCourante} a {@code null} en terminant la
 * partie, et ce verdict est la <b>seule trace</b> de l'item joue. Ce n'est donc pas un champ que
 * l'ecran pourrait recomposer autrement ; il n'y a rien a recomposer. Composante obligatoire et
 * non {@code Optional} : tout item du contrat de contenu porte un enonce, et aucun constructeur de
 * commodite ne laisse ce champ tomber - la forme du defaut d'ADR-0016 est ainsi fermee au
 * compilateur plutot qu'a la vigilance.</p>
 */
public record Verdict(boolean juste, String bonneReponse, Optional<String> explication,
                       String enonce) {
}
