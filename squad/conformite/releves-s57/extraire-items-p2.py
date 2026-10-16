import sys, json, yaml
racine, sortie, jeux = sys.argv[1], sys.argv[2], sys.argv[3].split(',')
res = racine + '/adapters/adapter-content/src/main/resources/'
cat = yaml.safe_load(open(res + 'catalogue.yaml', encoding='utf-8'))
paquet_de = {j['id']: j.get('paquet') for j in cat['jeux']}
out = {}
for jeu in jeux:
    p = yaml.safe_load(open(res + 'contenu/' + paquet_de[jeu] + '.yaml', encoding='utf-8'))
    out[jeu] = [{k: it.get(k) for k in ('enonce', 'bonneReponse', 'distracteurs', 'indice', 'texteSupport')} for it in p['items']]
    print(jeu, paquet_de[jeu], len(out[jeu]))
json.dump(out, open(sortie, 'w', encoding='utf-8'), ensure_ascii=False)
