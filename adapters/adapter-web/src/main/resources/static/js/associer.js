/*
 * Mecanique "associer" (associer / classer) : l'enfant glisse les ELEMENTS
 * (.assoc-item) depuis la reserve vers les ZONES-CIBLES / categories (.assoc-slot,
 * zone de depot .assoc-drop) pour les classer. Drag-and-drop HTML5 NATIF, zero
 * dependance externe (calque sur glisser-deposer.js).
 *
 * A la soumission du form (.assoc-form), on parcourt chaque .assoc-item DEPOSE dans
 * une .assoc-slot et on pousse deux listes PARALLELES via htmx:configRequest :
 *   assocItem   = valeur (data-value) de l'element place
 *   assocTarget = cible (data-target) de la zone ou il est pose
 * Le controller les zippe en une map {assocItem[i] -> assocTarget[i]}. On n'envoie
 * QUE les elements effectivement places. Le champ `from` cache reste porte par le form.
 *
 * Rebranche apres chaque echange htmx (htmx:afterSwap), comme glisser-deposer.js. Si
 * le JS est absent, le form ne fait qu'envoyer `from` : degrade sans planter.
 */
(function () {
  var dragged = null; // l'element en cours de deplacement

  function onDragStart(evt) {
    dragged = evt.target.closest('.assoc-item');
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

  // Depose l'element en cours dans la zone cible (une .assoc-drop de categorie ou la
  // reserve .assoc-reserve pour reprendre l'element).
  function onDrop(evt) {
    evt.preventDefault();
    if (!dragged) return;
    evt.currentTarget.appendChild(dragged);
    dragged.classList.remove('dragging');
    dragged = null;
  }

  // Injecte dans la requete htmx les listes paralleles assocItem / assocTarget pour
  // les elements effectivement places dans une categorie.
  function onConfigRequest(evt) {
    var form = evt.target;
    if (!form.classList || !form.classList.contains('assoc-form')) return;

    var root = form.closest('.associer') || document;
    var items = [];
    var targets = [];
    root.querySelectorAll('.assoc-slot').forEach(function (slot) {
      var target = slot.getAttribute('data-target');
      slot.querySelectorAll('.assoc-item').forEach(function (item) {
        items.push(item.getAttribute('data-value'));
        targets.push(target);
      });
    });
    // htmx enverra assocItem / assocTarget repetes pour chaque valeur des tableaux.
    evt.detail.parameters['assocItem'] = items;
    evt.detail.parameters['assocTarget'] = targets;
  }

  function wire(root) {
    root.querySelectorAll('.assoc-item').forEach(function (item) {
      item.setAttribute('draggable', 'true');
      item.addEventListener('dragstart', onDragStart);
      item.addEventListener('dragend', onDragEnd);
    });
    // Les zones de depot des categories ET la reserve acceptent le depot (permet de
    // reprendre un element deja place).
    var zones = [];
    root.querySelectorAll('.assoc-drop').forEach(function (z) { zones.push(z); });
    root.querySelectorAll('.assoc-reserve').forEach(function (z) { zones.push(z); });
    zones.forEach(function (zone) {
      zone.addEventListener('dragover', onDragOver);
      zone.addEventListener('drop', onDrop);
    });

    var form = root.querySelector('.assoc-form');
    if (form) form.addEventListener('htmx:configRequest', onConfigRequest);
  }

  function init() {
    // Garde : ne fait rien si la mecanique associer n'est pas a l'ecran
    // (inoffensif sur les autres mecaniques).
    var root = document.querySelector('.associer');
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
