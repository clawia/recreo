/*
 * Animation "bandit manchot" : chaque rouleau fait defiler des nombres puis
 * s'arrete sur sa valeur (celle portee par data-value), avec un decalage entre
 * rouleaux facon machine a sous. Rebranche apres chaque echange htmx.
 *
 * Purement cosmetique : la valeur soumise reste data-value (input cache). Si le
 * JS est absent, le rouleau affiche deja la bonne valeur (degrade proprement).
 */
(function () {
  var DECOYS = 7;       // symboles "de defilement" avant l'arret (peu = defilement calme)
  var SPIN_MS = 1000;   // duree du defilement : 1 seconde

  function randInt(max) {
    return Math.floor(Math.random() * max);
  }

  // Symboles "machine a sous" qui defilent pendant que le rouleau tourne,
  // avant de s'arreter sur la vraie valeur (nombre ou operateur du calcul).
  var SYMBOLS = ['🍒', '🔔', '⭐', '7️⃣', '🍋', '🍇', '🪙', '💎'];

  function makeItem(value, extraClass) {
    var el = document.createElement('span');
    el.className = 'reel-item' + (extraClass ? ' ' + extraClass : '');
    el.textContent = value;
    return el;
  }

  function decoySymbol() {
    return SYMBOLS[randInt(SYMBOLS.length)];
  }

  function spinReel(button, index) {
    var strip = button.querySelector('.reel-strip');
    var windowEl = button.querySelector('.reel-window');
    if (!strip || !windowEl) return;

    var value = button.getAttribute('data-value');
    var itemHeight = windowEl.clientHeight; // fenetre = hauteur d'un item
    if (!itemHeight) itemHeight = 96;

    // Bande : la vraie valeur EN HAUT, puis les symboles de defilement en dessous.
    // On demarre bande remontee (un decor visible) et on descend jusqu'a la valeur
    // -> les symboles "tombent" vers le bas, comme une vraie machine a sous.
    strip.innerHTML = '';
    var frag = document.createDocumentFragment();
    frag.appendChild(makeItem(value, 'final'));
    for (var i = 0; i < DECOYS; i++) {
      frag.appendChild(makeItem(decoySymbol(), 'decoy'));
    }
    strip.appendChild(frag);

    var distance = itemHeight * DECOYS;

    // Position de depart (bande remontee), sans transition.
    strip.style.transition = 'none';
    strip.style.transform = 'translateY(-' + distance + 'px)';
    void strip.offsetHeight; // force le reflow pour figer l'etat initial

    var delay = 80 + index * 260; // decalage facon machine a sous

    button.classList.add('spinning');
    window.setTimeout(function () {
      strip.style.transition = 'transform ' + SPIN_MS + 'ms cubic-bezier(.20,.55,.30,1)';
      strip.style.transform = 'translateY(0)'; // descend et s'arrete sur la valeur (en haut)
    }, delay);

    window.setTimeout(function () {
      button.classList.remove('spinning');
      button.classList.add('landed'); // petit rebond a l'arret
      window.setTimeout(function () { button.classList.remove('landed'); }, 400);
    }, delay + SPIN_MS + 60);
  }

  function initReels() {
    var machine = document.querySelector('.machine');
    if (!machine) return;
    var reels = machine.querySelectorAll('.reel');
    reels.forEach(function (btn, idx) { spinReel(btn, idx); });
  }

  function boot() {
    initReels();
    // Rejoue l'animation a chaque fragment renvoye par htmx (nouvelle manche).
    document.body.addEventListener('htmx:afterSwap', function () { initReels(); });
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', boot);
  } else {
    boot();
  }
})();
