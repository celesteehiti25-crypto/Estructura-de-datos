# Ejercicio 2: Inventario

#Una tienda registra nombre, precio y existencias de productos identificados por un código único.

inventario = {

    "P001": {"nombre": "Teclado", "precio": 350.00, "existencias": 15},

    "P002": {"nombre": "Mouse", "precio": 180.00, "existencias": 20},

    "P003": {"nombre": "Monitor", "precio": 3200.00, "existencias": 8}

}

codigo = input("Código del producto: ").strip().upper()

producto = inventario.get(codigo)

if producto is not None:

    print("Producto:", producto["nombre"])

    print(f"Precio: ${producto['precio']:.2f}")

    print("Existencias:", producto["existencias"])

else:

    print("Producto no encontrado.")