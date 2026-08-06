package com.recreo.adapter.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Rejoue le decodage du corps application/x-www-form-urlencoded avec java.lang.String, qui ne
 * leve jamais sur une sequence d'octets non valide en UTF-8 - au lieu du decodeur de parametres
 * de Tomcat (org.apache.tomcat.util.http.Parameters), qui leve une IllegalStateException dans ce
 * cas (voir CorpsFormulaireEnUtf8ToleranteFilter). Le corps est lu une seule fois, ici, avant que
 * quiconque n'appelle getParameter sur la requete d'origine.
 */
final class RequeteFormulaireDecodeeEnUtf8 extends HttpServletRequestWrapper {

    private final Map<String, String[]> parametres;

    RequeteFormulaireDecodeeEnUtf8(HttpServletRequest requete) throws IOException {
        super(requete);
        this.parametres = decoder(requete.getInputStream().readAllBytes());
    }

    private static Map<String, String[]> decoder(byte[] corps) {
        String texte = new String(corps, StandardCharsets.UTF_8);
        Map<String, List<String>> valeurs = new LinkedHashMap<>();
        for (String paire : texte.split("&")) {
            if (paire.isEmpty()) {
                continue;
            }
            int separateur = paire.indexOf('=');
            String cle = decoderPart(separateur < 0 ? paire : paire.substring(0, separateur));
            String valeur = separateur < 0 ? "" : decoderPart(paire.substring(separateur + 1));
            valeurs.computeIfAbsent(cle, ignore -> new ArrayList<>()).add(valeur);
        }
        Map<String, String[]> resultat = new LinkedHashMap<>();
        valeurs.forEach((cle, liste) -> resultat.put(cle, liste.toArray(new String[0])));
        return resultat;
    }

    /** application/x-www-form-urlencoded : "+" vaut espace, "%XX" vaut l'octet hexadecimal XX. */
    private static String decoderPart(String partie) {
        return new String(decoderOctets(partie.replace('+', ' ')), StandardCharsets.UTF_8);
    }

    private static byte[] decoderOctets(String partie) {
        ByteArrayOutputStream octets = new ByteArrayOutputStream(partie.length());
        for (int i = 0; i < partie.length(); i++) {
            char caractere = partie.charAt(i);
            if (caractere == '%' && i + 2 < partie.length()) {
                try {
                    octets.write(Integer.parseInt(partie.substring(i + 1, i + 3), 16));
                    i += 2;
                    continue;
                } catch (NumberFormatException pasUneSequenceHexadecimale) {
                    // '%' non suivi de deux chiffres hexadecimaux : conserve tel quel, comme un octet ASCII
                }
            }
            octets.write(caractere);
        }
        return octets.toByteArray();
    }

    @Override
    public String getParameter(String nom) {
        String[] valeurs = parametres.get(nom);
        return valeurs != null && valeurs.length > 0 ? valeurs[0] : null;
    }

    @Override
    public String[] getParameterValues(String nom) {
        return parametres.get(nom);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(parametres);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(parametres.keySet());
    }
}
