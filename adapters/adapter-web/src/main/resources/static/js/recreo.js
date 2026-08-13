document.body.addEventListener('htmx:afterSwap', function (evenement) {
    var cible = evenement.detail.target;
    var ecran = cible.querySelector('[data-focus]');
    var pointDeFocus = null;
    if (ecran) {
        pointDeFocus = ecran.getAttribute('data-focus') === 'enonce'
            ? ecran.querySelector('.enonce')
            : ecran.querySelector('[data-focus-cible="true"]');
    } else {
        pointDeFocus = cible.querySelector('.signal');
    }
    if (pointDeFocus) {
        pointDeFocus.setAttribute('tabindex', '-1');
        pointDeFocus.focus();
    }
});
