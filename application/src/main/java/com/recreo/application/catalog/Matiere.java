package com.recreo.application.catalog;

/**
 * ADR-0106 : une ligne de la donnee de matiere. {@code nom} est la chaine exacte que les paquets
 * declarent dans leur descripteur (ADR-0049) et sert de cle de jointure ; le libelle court, le
 * pictogramme (un nom de fichier), les deux teintes et le segment d'adresse sont ecrits a cote, jamais
 * calcules depuis {@code nom}. Le rang de la ligne dans la donnee est l'ordre d'affichage (APM-4).
 */
public record Matiere(String nom, String libelle, String pictogramme, String teinteFond,
                      String teinteContour, String segment) {
}
