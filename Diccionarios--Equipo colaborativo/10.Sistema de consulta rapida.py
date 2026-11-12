# Ejercicio 10: Biblioteca escolar - Sistema CRUD

#Una biblioteca necesita registrar, consultar, actualizar, eliminar y listar libros mediante un código único.

biblioteca = {

 "LIB001": {"titulo":"Estructuras de Datos", "autor":"Autor A", "cantidad":5},

 "LIB002": {"titulo":"Bases de Datos", "autor":"Autor B", "cantidad":3},

 "LIB003": {"titulo":"Programación en Python", "autor":"Autor C", "cantidad":8}

}

 

def leer_cantidad():

    while True:

        try:

            cantidad = int(input("Cantidad de ejemplares: "))

            if cantidad >= 0:

                return cantidad

            print("La cantidad no puede ser negativa.")

        except ValueError:

            print("Debe introducir un número entero.")

 

def registrar_libro():

    codigo = input("Código del libro: ").strip().upper()

    if not codigo:

        print("El código no puede estar vacío.")

        return

    if codigo in biblioteca:

        print("El libro ya está registrado.")

        return

    titulo = input("Título: ").strip()

    autor = input("Autor: ").strip()

    if not titulo or not autor:

        print("El título y el autor son obligatorios.")

        return

    biblioteca[codigo] = {"titulo": titulo, "autor": autor,

                          "cantidad": leer_cantidad()}

    print("Libro registrado correctamente.")

 

def buscar_libro():

    codigo = input("Código a buscar: ").strip().upper()

    libro = biblioteca.get(codigo)

    if libro is None:

        print("Libro no encontrado.")

        return

    print("Libro encontrado")

    print("Código:", codigo)

    print("Título:", libro["titulo"])

    print("Autor:", libro["autor"])

    print("Cantidad:", libro["cantidad"])

 

def actualizar_libro():

    codigo = input("Código a actualizar: ").strip().upper()

    libro = biblioteca.get(codigo)

    if libro is None:

        print("Libro no encontrado.")

        return

    print("Título actual:", libro["titulo"])

    titulo = input("Nuevo título (Enter para conservar): ").strip()

    if titulo:

        libro["titulo"] = titulo

    print("Autor actual:", libro["autor"])

    autor = input("Nuevo autor (Enter para conservar): ").strip()

    if autor:

        libro["autor"] = autor

    respuesta = input("¿Modificar cantidad? (s/n): ").strip().lower()

    if respuesta == "s":

        libro["cantidad"] = leer_cantidad()

    print("Libro actualizado correctamente.")

 

def eliminar_libro():

    codigo = input("Código a eliminar: ").strip().upper()

    eliminado = biblioteca.pop(codigo, None)

    print("Libro eliminado correctamente." if eliminado is not None

          else "Libro no encontrado.")

 

def mostrar_libros():

    if not biblioteca:

        print("La biblioteca está vacía.")

        return

    print("LIBROS REGISTRADOS")

    for codigo, libro in biblioteca.items():

        print("----------------------------")

        print("Código:", codigo)

        print("Título:", libro["titulo"])

        print("Autor:", libro["autor"])

        print("Cantidad:", libro["cantidad"])

 

def menu():

    while True:

        print("===== BIBLIOTECA ESCOLAR =====")

        print("1. Registrar libro")

        print("2. Buscar libro")

        print("3. Actualizar libro")

        print("4. Eliminar libro")

        print("5. Mostrar libros")

        print("6. Salir")

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "1": registrar_libro()

        elif opcion == "2": buscar_libro()

        elif opcion == "3": actualizar_libro()

        elif opcion == "4": eliminar_libro()

        elif opcion == "5": mostrar_libros()

        elif opcion == "6":

            print("Gracias por utilizar el sistema.")

            break

        else: print("Opción no válida.")

 

if __name__ == "__main__":

    menu()