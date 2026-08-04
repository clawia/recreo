package com.recreo.adapter.web.contreexemple;

import java.util.regex.Pattern;

/**
 * ADR-0003 : viole ADR-0008. L'assertion de HTML par expression reguliere, cassante au moindre
 * changement de balisage, telle que la version precedente la pratiquait. Existe pour etre refusee
 * par AssertionsWebTest.aucuneAssertionParExpressionReguliere.
 *
 * <p>Seul contre-exemple du depot qui ne vit pas dans bootstrap/src/test : la regle qu'il viole
 * regarde le code de test de ce module, que bootstrap ne voit pas.</p>
 */
public final class AssertionRegexContreExemple {

    private static final Pattern SCORE = Pattern.compile("<span class=\"score\">(\\d+)</span>");

    public String scoreAffiche(String html) {
        return SCORE.matcher(html).results().findFirst().orElseThrow().group(1);
    }
}
