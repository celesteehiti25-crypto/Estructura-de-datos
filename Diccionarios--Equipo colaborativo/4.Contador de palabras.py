# Ejercicio 4: Contador de palabras

#Un documento necesita un conteo de apariciones por palabra sin recorrer el texto completo para cada término.

import re

texto = ("Python permite crear diccionarios. "

         "Los diccionarios permiten buscar datos. Python es útil.")

palabras = re.findall(r"\w+", texto.lower(), flags=re.UNICODE)

frecuencias = {}

for palabra in palabras:

    frecuencias[palabra] = frecuencias.get(palabra, 0) + 1

for palabra, cantidad in sorted(frecuencias.items()):

    print(f"{palabra}: {cantidad}")