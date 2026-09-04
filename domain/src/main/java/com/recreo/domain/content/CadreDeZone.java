package com.recreo.domain.content;

/**
 * ADR-0080, decision 2 : le rectangle d'une {@link Zone}, en pourcentage entier de la boite de
 * l'image qu'elle designe. {@code x} et {@code y} sont le coin superieur gauche du rectangle,
 * {@code largeur} et {@code hauteur} son etendue - les quatre en pourcentage de la largeur et de la
 * hauteur de l'image, jamais dans une unite du dessin (viewBox, pixel).
 *
 * <p>Le pourcentage n'a besoin de connaitre ni le format du fichier servi, ni sa taille declaree, ni
 * la feuille de style : le bouton d'une zone n'est pas un element du dessin, c'est un element HTML
 * pose par-dessus la boite de l'image.</p>
 *
 * <p>Invariants du constructeur compact : {@code x} et {@code y} valent au moins 0, {@code largeur}
 * et {@code hauteur} valent au moins 1, {@code x + largeur} et {@code y + hauteur} ne depassent pas
 * 100.</p>
 *
 * <p><b>Ce type est separe de {@link Zone} pour une raison architecturale et non de commodite</b> :
 * c'est ce qui permet a {@code DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaGeometrie}
 * d'exister (ADR-0080, decision 6, clause 10). Une mecanique peut transporter une {@code Zone} sans
 * jamais toucher a un {@code CadreDeZone}, et ArchUnit sait dire la difference parce que les deux
 * notions sont deux types distincts.</p>
 *
 * <p><b>Ce type ne declare aucune methode ecrite a la main</b> - en particulier aucun
 * {@code contient(int, int)} qui resoudrait un point en zone. C'est la clause 11 de
 * {@code DecouplageMecaniqueContenuTest} (ADR-0080) : un tel predicat, pose ici, ne laisserait plus
 * tenir la clause 10 que par un seul fil - le fait qu'une mecanique doive quand meme nommer ce type
 * pour l'appeler.</p>
 */
public record CadreDeZone(int x, int y, int largeur, int hauteur) {

    public CadreDeZone {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
                    "ADR-0080 : x et y d'un cadre de zone valent au moins 0 (x=" + x + ", y=" + y + ")");
        }
        if (largeur < 1 || hauteur < 1) {
            throw new IllegalArgumentException(
                    "ADR-0080 : largeur et hauteur d'un cadre de zone valent au moins 1 (largeur="
                            + largeur + ", hauteur=" + hauteur + ")");
        }
        if (x + largeur > 100 || y + hauteur > 100) {
            throw new IllegalArgumentException(
                    "ADR-0080 : un cadre de zone ne depasse pas 100% de la boite de l'image (x=" + x
                            + ", y=" + y + ", largeur=" + largeur + ", hauteur=" + hauteur + ")");
        }
    }
}
