/*
 * Mecanique "memory" (jeu de paires) : les cartes (.memory-carte) sont affichees
 * face cachee dans .memory-grille. L'enfant clique pour en retourner deux (.flipped) ;
 * si elles forment une paire elles restent visibles (.appariee), sinon elles se
 * retournent apres ~800ms. Flip purement CSS (classe .flipped), zero dependance externe.
 *
 * Une paire = meme INDEX de groupe ET cotes DIFFERENTS. Le groupe est porte par
 * data-group au format "<index><cote>" (ex. "0g","0d") : on compare en ignorant le
 * dernier caractere (le cote g/d).
 *
 * A l'init on melange les positions des cartes dans la grille (Fisher-Yates sur les
 * noeuds DOM) en gardant leurs attributs data-group/data-value. Quand TOUTES les
 * cartes sont appariees, on construit les listes PARALLELES a soumettre :
 *   assocItem   = data-value de la carte de cote "g" de chaque paire
 *   assocTarget = data-value de la carte de cote "d" de chaque paire
 * puis on pose ces listes sur le form cache (.memory-form) via htmx:configRequest et
 * on declenche sa soumission htmx. Rebranche apres chaque echange htmx (htmx:afterSwap),
 * comme bandit.js. Les clics sont bloques pendant l'animation de comparaison.
 */
(function () {
  var LOCK_MS = 800;   // duree d'affichage d'une paire non trouvee avant retournement
  var busy = false;    // vrai pendant l'animation de comparaison : bloque les clics
  var first = null;    // premiere carte retournee en attente d'une seconde

  function randInt(max) {
    return Math.floor(Math.random() * max);
  }

  // Melange Fisher-Yates des noeuds .memory-carte dans la grille (garde leurs attributs).
  function shuffle(grille) {
    var cartes = [];
    grille.querySelectorAll('.memory-carte').forEach(function (c) { cartes.push(c); });
    // Melange en memoire (Fisher-Yates), puis reinsertion dans l'ordre melange :
    // appendChild deplace chaque noeud existant a la fin -> reordonne la grille.
    for (var i = cartes.length - 1; i > 0; i--) {
      var j = randInt(i + 1);
      var tmp = cartes[i]; cartes[i] = cartes[j]; cartes[j] = tmp;
    }
    cartes.forEach(function (c) { grille.appendChild(c); });
  }

  // Index de paire = data-group sans le dernier caractere (le cote g/d).
  function pairIndex(carte) {
    var g = carte.getAttribute('data-group') || '';
    return g.slice(0, -1);
  }

  function side(carte) {
    var g = carte.getAttribute('data-group') || '';
    return g.slice(-1);
  }

  function allMatched(root) {
    var cartes = root.querySelectorAll('.memory-carte');
    if (!cartes.length) return false;
    for (var i = 0; i < cartes.length; i++) {
      if (!cartes[i].classList.contains('appariee')) return false;
    }
    return true;
  }

  // Construit les listes paralleles (une entree par paire) et soumet le form cache.
  function submit(root) {
    var form = root.querySelector('.memory-form');
    if (!form) return;

    var items = {};   // index de paire -> data-value du cote "g"
    var targets = {}; // index de paire -> data-value du cote "d"
    root.querySelectorAll('.memory-carte').forEach(function (carte) {
      var idx = pairIndex(carte);
      if (side(carte) === 'g') items[idx] = carte.getAttribute('data-value');
      else targets[idx] = carte.getAttribute('data-value');
    });

    var assocItem = [];
    var assocTarget = [];
    Object.keys(items).forEach(function (idx) {
      if (targets[idx] != null) {
        assocItem.push(items[idx]);
        assocTarget.push(targets[idx]);
      }
    });

    var onConfig = function (evt) {
      evt.detail.parameters['assocItem'] = assocItem;
      evt.detail.parameters['assocTarget'] = assocTarget;
    };
    form.addEventListener('htmx:configRequest', onConfig);
    if (window.htmx) {
      window.htmx.trigger(form, 'submit');
    } else if (form.requestSubmit) {
      form.requestSubmit();
    }
  }

  function onCardClick(root, carte) {
    if (busy) return;
    if (carte.classList.contains('appariee')) return;
    if (carte.classList.contains('flipped')) return;

    carte.classList.add('flipped');

    if (!first) {
      first = carte;
      return;
    }

    // Deuxieme carte retournee : on compare.
    var second = carte;
    var isPair = pairIndex(first) === pairIndex(second) && side(first) !== side(second);

    if (isPair) {
      first.classList.add('appariee');
      second.classList.add('appariee');
      first = null;
      if (allMatched(root)) {
        submit(root);
      }
    } else {
      busy = true;
      var a = first, b = second;
      first = null;
      window.setTimeout(function () {
        a.classList.remove('flipped');
        b.classList.remove('flipped');
        busy = false;
      }, LOCK_MS);
    }
  }

  function wire(root) {
    var grille = root.querySelector('.memory-grille');
    if (grille) shuffle(grille);
    root.querySelectorAll('.memory-carte').forEach(function (carte) {
      carte.addEventListener('click', function () { onCardClick(root, carte); });
    });
  }

  function init() {
    // Garde : ne fait rien si la mecanique memory n'est pas a l'ecran
    // (inoffensif sur les autres mecaniques).
    var root = document.querySelector('.memory');
    if (!root) return;
    busy = false;
    first = null;
    wire(root);
  }

  function boot() {
    init();
    // Rebranche apres chaque fragment renvoye par htmx (nouvelle manche).
    document.body.addEventListener('htmx:afterSwap', init);
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', boot);
  } else {
    boot();
  }
})();
