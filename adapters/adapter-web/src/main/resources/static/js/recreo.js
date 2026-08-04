document.body.addEventListener('htmx:afterSwap', function (evenement) {
    var cible = evenement.detail.target;
    var signal = cible.querySelector('.signal');
    if (signal) {
        signal.setAttribute('tabindex', '-1');
        signal.focus();
    }
});
