# Copie du jar de reference dont ce1-texte-narratif porte les textes de nadia-prof (Q-P5). Hors depot.
import sys, zipfile, io
src, dst = sys.argv[1], sys.argv[2]
REMPL = [
 ('bonneReponse: "elle saute dans une flaque"', 'bonneReponse: "saute dans une flaque"'),
 ('distracteurs: ["Inès enfile ses bottes", "elle sort dehors"]', 'distracteurs: ["enfile ses bottes", "sort dehors"]'),
 ('bonneReponse: "il s\'assoit près de la fenêtre"', 'bonneReponse: "s\'assoit près de la fenêtre"'),
 ('distracteurs: ["Ibrahim attend le bus", "le bus arrive"]', 'distracteurs: ["attend le bus", "monte"]'),
 ('distracteurs: ["Chloé plante une graine", "elle l\'arrose chaque jour"]', 'distracteurs: ["Chloé plante une graine", "elle l\'arrose"]'),
]
NESTED = 'BOOT-INF/lib/recreo-adapter-content-0.1.0-SNAPSHOT.jar'
YAML = 'contenu/ce1-texte-narratif.yaml'
zin = zipfile.ZipFile(src)
nb = zin.read(NESTED)
nin = zipfile.ZipFile(io.BytesIO(nb))
buf = io.BytesIO()
with zipfile.ZipFile(buf, 'w', zipfile.ZIP_DEFLATED) as nout:
    for i in nin.infolist():
        data = nin.read(i.filename)
        if i.filename == YAML:
            t = data.decode('utf-8')
            for a, b in REMPL:
                assert t.count(a) == 1, a
                t = t.replace(a, b)
            data = t.encode('utf-8')
        nout.writestr(i, data)
with zipfile.ZipFile(dst, 'w') as zout:
    for i in zin.infolist():
        data = zin.read(i.filename)
        if i.filename == NESTED:
            data = buf.getvalue()
            zi = zipfile.ZipInfo(i.filename, i.date_time); zi.compress_type = zipfile.ZIP_STORED
            zout.writestr(zi, data)
        else:
            zout.writestr(i, data, compress_type=i.compress_type)
print('ok')
