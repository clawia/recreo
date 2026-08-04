package com.recreo.domain.content.contreexemple;

import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;

/**
 * ADR-0003 : viole ADR-0004 (5), le sens que la version precedente ne regardait pas. Existe pour
 * etre refusee par DecouplageMecaniqueContenuTest.leContenuIgnoreLesMecaniques.
 */
public class ContenuQuiSaitJouerContreExemple {

    public MechanicId mecaniqueAttendue(GameMechanic mecanique) {
        return mecanique.id();
    }
}
