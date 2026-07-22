/*
 * Mecanique "puzzle" (reconstitue le mot) : l'enfant glisse les TUILES-lettres
 * (.dnd-tuile) dans les EMPLACEMENTS (.dnd-slot) pour reconstituer le mot demande,
 * aide par un INDICE. Meme mecanisme d'arrangement que le glisser-deposer (copie de
 * /js/glisser-deposer.js, seul le selecteur racine change : .puzzle). Drag-and-drop
 * HTML5 NATIF, zero dependance externe.
 *
 * A la soumission du form (.dnd-form), l'ordre des slots (dans l'ordre du DOM) est
 * lu et envoye au backend sous forme d'un champ `arrangement` REPETE
 * (arrangement=A&arrangement=B&...), via htmx:configRequest. Le champ `from` cache
 * reste porte par le form.
 *
 * Rebranche apres chaque echange htmx (htmx:afterSwap), comme bandit.js. Si le JS
 * est absent, le form ne fait qu'envoyer `from` : degrade sans planter.
 *
 * Les tuiles peuvent etre servies DEJA PLACEES dans un .dnd-slot (essai faux : le
 * serveur verrouille les positions deja reussies, UX « ranger »). wire() cable TOUTES
 * les .dnd-tuile (reserve ET slots) comme draggables, et la soumission lit l'ordre en
 * parcourant les .dnd-slot (tuiles pre-placees + celles que l'enfant vient de poser).
 */
(function () {
  var dragged = null; // la tuile en cours de deplacement

  // Renvoie la tuile (.dnd-tuile) contenue dans un slot, ou null.
  function tileIn(slot) {
    return slot.querySelector('.dnd-tuile');
  }

  function onDragStart(evt) {
    dragged = evt.target.closest('.dnd-tuile');
    if (!dragged) return;
    evt.dataTransfer.effectAllowed = 'move';
    // Certains navigateurs exigent des donnees pour demarrer le drag.
    try { evt.dataTransfer.setData('text/plain', dragged.getAttribute('data-value') || ''); } catch (e) {}
    dragged.classList.add('dragging');
  }

  function onDragEnd() {
    if (dragged) dragged.classList.remove('dragging');
    dragged = null;
  }

  function onDragOver(evt) {
    // Indispensable : sans preventDefault, le drop n'est pas autorise.
    evt.preventDefault();
    evt.dataTransfer.dropEffect = 'move';
  }

  // Depose la tuile en cours dans la zone cible (un .dnd-slot ou la reserve .dnd-tuiles).
  // Si la cible est un slot deja occupe, on ECHANGE : l'ancienne tuile repart d'ou
  // venait la tuile deplacee (slot d'origine, ou la reserve).
  function onDrop(evt) {
    evt.preventDefault();
    if (!dragged) return;

    var target = evt.currentTarget;
    var origin = dragged.parentNode; // slot d'origine ou reserve .dnd-tuiles

    if (target.classList.contains('dnd-slot')) {
      var occupant = tileIn(target);
      if (occupant && occupant !== dragged) {
        // Echange : l'occupant prend la place d'origine de la tuile deplacee.
        origin.appendChild(occupant);
      }
      target.appendChild(dragged);
    } else {
      // Retour dans la reserve de tuiles.
      target.appendChild(dragged);
    }

    dragged.classList.remove('dragging');
    dragged = null;
  }

  // Injecte dans la requete htmx l'ordre des slots remplis (ordre du DOM).
  function onConfigRequest(evt) {
    var form = evt.target;
    if (!form.classList || !form.classList.contains('dnd-form')) return;

    var root = form.closest('.puzzle') || document;
    var slots = root.querySelectorAll('.dnd-slot');
    var arrangement = [];
    slots.forEach(function (slot) {
      var tile = tileIn(slot);
      if (tile) arrangement.push(tile.getAttribute('data-value'));
    });
    // htmx enverra un champ `arrangement` repete pour chaque valeur du tableau.
    evt.detail.parameters['arrangement'] = arrangement;
  }

  function wire(root) {
    root.querySelectorAll('.dnd-tuile').forEach(function (tile) {
      tile.setAttribute('draggable', 'true');
      tile.addEventListener('dragstart', onDragStart);
      tile.addEventListener('dragend', onDragEnd);
    });
    // Les slots ET la reserve acceptent le depot (permet de retirer une tuile d'un slot).
    var zones = [];
    root.querySelectorAll('.dnd-slot').forEach(function (z) { zones.push(z); });
    root.querySelectorAll('.dnd-tuiles').forEach(function (z) { zones.push(z); });
    zones.forEach(function (zone) {
      zone.addEventListener('dragover', onDragOver);
      zone.addEventListener('drop', onDrop);
    });

    var form = root.querySelector('.dnd-form');
    if (form) form.addEventListener('htmx:configRequest', onConfigRequest);
  }

  function init() {
    // Garde : ne fait rien si le jeu puzzle n'est pas a l'ecran
    // (inoffensif sur les autres mecaniques).
    var root = document.querySelector('.puzzle');
    if (!root) return;
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
