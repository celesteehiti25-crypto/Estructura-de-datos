# Ejercicio 8: Buscador de productos

#Se dispone de una lista de productos y se requieren múltiples consultas por ID.

productos = [

 {"id":"P001", "nombre":"Laptop", "categoria":"Computadoras", "precio":12500.0, "stock":5},

 {"id":"P002", "nombre":"Mouse", "categoria":"Accesorios", "precio":250.0, "stock":30},

 {"id":"P003", "nombre":"Monitor", "categoria":"Pantallas", "precio":3400.0, "stock":12},

 {"id":"P004", "nombre":"Teclado", "categoria":"Accesorios", "precio":450.0, "stock":20}

]

indice_productos = {}

for producto in productos:

    indice_productos[producto["id"]] = producto

while True:

    codigo = input("Código del producto (SALIR para terminar): ").strip().upper()

    if codigo == "SALIR":

        print("Sistema finalizado.")

        break

    producto = indice_productos.get(codigo)

    if producto is not None:

        print("Producto encontrado")

        print("Nombre:", producto["nombre"])

        print("Categoría:", producto["categoria"])

        print(f"Precio: ${producto['precio']:.2f}")

        print("Existencias:", producto["stock"])

    else:

        print("El producto no existe.")