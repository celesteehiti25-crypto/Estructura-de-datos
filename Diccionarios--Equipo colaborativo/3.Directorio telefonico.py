# Ejercicio 3: Directorio telefónico

#Se necesita registrar contactos y recuperar un teléfono mediante el nombre.

directorio = {

    "Ana": "5512345678",

    "Pedro": "5587654321",

    "Laura": "5534567890"

}

nombre = input("Nombre del nuevo contacto: ").strip()

telefono = input("Número telefónico: ").strip()

if nombre and telefono:

    directorio[nombre] = telefono  # Insertar o actualizar

    print("Contacto guardado correctamente.")

else:

    print("Datos incompletos.")

buscar = input("Nombre que desea buscar: ").strip()

numero = directorio.get(buscar)

if numero is not None:

    print("Teléfono:", numero)

else:

    print("Contacto no encontrado.")