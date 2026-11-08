import java.util.Scanner;

public class ABB {

    // Un nodo almacena un valor y dos referencias a sus hijos.
    static class Nodo {
        int valor;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(int valor) {
            this.valor = valor;
            izquierdo = null;
            derecho = null;
        }
    }
     // Referencia al primer nodo del arbol.
    private Nodo raiz;

    // Inserta un valor; devuelve false si ya existe.
    public boolean insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return true;
        }

        Nodo actual = raiz;
        while (true) {
            if (valor == actual.valor) {
                return false; // No se permiten valores repetidos.
            }
            
            if (valor < actual.valor) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new Nodo(valor);
                    return true;
                }
                actual = actual.izquierdo;
            } else {
                if (actual.derecho == null) {
                    actual.derecho = new Nodo(valor);
                    return true;
                }
                actual = actual.derecho;
            }
        }
    }
    
    // Busca un valor siguiendo la regla izquierda-menor, derecha-mayor.
    public boolean buscar(int valor) {
        Nodo actual = raiz;

        while (actual != null) {
            if (valor == actual.valor) {
                return true;
            }
            if (valor < actual.valor) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }
        return false;
    }
     // Preorden: raiz, izquierda, derecha.
    public void preorden() {
        recorrerPreorden(raiz);
        System.out.println();
    }

    private void recorrerPreorden(Nodo nodo) {
        if (nodo == null) return;
        System.out.print(nodo.valor + " ");
        recorrerPreorden(nodo.izquierdo);
        recorrerPreorden(nodo.derecho);
    }

    // Inorden: izquierda, raiz, derecha.
    public void inorden() {
        recorrerInorden(raiz);
        System.out.println();
    }

    private void recorrerInorden(Nodo nodo) {
        if (nodo == null) return;
        recorrerInorden(nodo.izquierdo);
        System.out.print(nodo.valor + " ");
        recorrerInorden(nodo.derecho);
    }
    
    // Postorden: izquierda, derecha, raiz.
    public void postorden() {
        recorrerPostorden(raiz);
        System.out.println();
    }

    private void recorrerPostorden(Nodo nodo) {
        if (nodo == null) return;
        recorrerPostorden(nodo.izquierdo);
        recorrerPostorden(nodo.derecho);
        System.out.print(nodo.valor + " ");
    }

    // Lee numeros sin cerrar el programa si se escribe texto.
    private static Integer leerEntero(Scanner teclado) {
        try {
            return Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Escribe un numero entero.");
            return null;
        }
    }
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ABB arbol = new ABB();

        // Datos iniciales para observar los tres recorridos.
        int[] valoresIniciales = {50, 30, 70, 20, 40, 60, 80};
        for (int valor : valoresIniciales) {
            arbol.insertar(valor);
        }
        
        System.out.println("ARBOL BINARIO DE BUSQUEDA (ABB)");
        System.out.println("Valores iniciales: 50 30 70 20 40 60 80");

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Insertar numero");
            System.out.println("2. Buscar numero");
            System.out.println("3. Recorrido Preorden");
            System.out.println("4. Recorrido Inorden");
            System.out.println("5. Recorrido Postorden");
            System.out.println("6. Mostrar todos los recorridos");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            Integer opcion = leerEntero(teclado);
            if (opcion == null) continue;

            switch (opcion) {
                case 1:
                    System.out.print("Numero a insertar: ");
                    Integer nuevo = leerEntero(teclado);
                    if (nuevo != null) {
                        if (arbol.insertar(nuevo)) {
                            System.out.println("Numero insertado correctamente.");
                        } else {
                            System.out.println("El numero ya existe en el arbol.");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Numero a buscar: ");
                    Integer buscado = leerEntero(teclado);
                    if (buscado != null) {
                        System.out.println(arbol.buscar(buscado)
                            ? "El numero SI esta en el arbol."
                            : "El numero NO esta en el arbol.");
                    }
                    break;
                case 3:
                    System.out.print("Preorden: ");
                    arbol.preorden();
                    break;
                case 4:
                    System.out.print("Inorden: ");
                    arbol.inorden();
                    break;
                case 5:
                    System.out.print("Postorden: ");
                    arbol.postorden();
                    break;
                case 6:
                    System.out.print("Preorden:  ");
                    arbol.preorden();
                    System.out.print("Inorden:   ");
                    arbol.inorden();
                    System.out.print("Postorden: ");
                    arbol.postorden();
                    break;
                case 0:
                    System.out.println("Programa finalizado.");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}

    
