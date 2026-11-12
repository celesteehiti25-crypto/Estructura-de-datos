# Ejercicio 1: Diccionario de estudiantes

estudiantes = {

    "2026001": "Ana López",

    "2026002": "Carlos Pérez",

    "2026003": "María García",

    "2026004": "Luis Hernández"

}

matricula = input("Ingrese la matrícula: ").strip()

nombre = estudiantes.get(matricula)

if nombre is not None:

    print("Estudiante encontrado:", nombre)

else:

    print("La matrícula no está registrada.")

