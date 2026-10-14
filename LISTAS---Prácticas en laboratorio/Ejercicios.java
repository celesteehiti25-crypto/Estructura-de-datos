
import java.util.Scanner;

public class Ejercicios {

    static Scanner sc = new Scanner(System.in);

    // ==========================================
    // CLASE LIBRO
    // ==========================================
    static class Libro {
        int id;
        String titulo;
        String autor;

        Libro(int id, String titulo, String autor) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
        }

        public String toString() {
            return "ID: " + id + " | Titulo: " + titulo
                    + " | Autor: " + autor;
        }
    }

    // ==========================================
    // NODO Y LISTA SIMPLEMENTE ENLAZADA
    // ==========================================
    static class NodoSimple {
        Libro libro;
        NodoSimple siguiente;

        NodoSimple(Libro libro) {
            this.libro = libro;
            this.siguiente = null;
        }
    }

    static class ListaSimple {
        NodoSimple cabeza;

        void insertarInicio(Libro libro) {
            NodoSimple nuevo = new NodoSimple(libro);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }

        void insertarFinal(Libro libro) {
            NodoSimple nuevo = new NodoSimple(libro);

            if (cabeza == null) {
                cabeza = nuevo;
                return;
            }

            NodoSimple actual = cabeza;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }

        void mostrar() {
            if (cabeza == null) {
                System.out.println("La lista esta vacia.");
                return;
            }

            NodoSimple actual = cabeza;

            while (actual != null) {
                System.out.println(actual.libro);
                actual = actual.siguiente;
            }
        }

        Libro buscar(int id) {
            NodoSimple actual = cabeza;

            while (actual != null) {
                if (actual.libro.id == id) {
                    return actual.libro;
                }
                actual = actual.siguiente;
            }

            return null;
        }

        boolean eliminar(int id) {
            if (cabeza == null) {
                return false;
            }

            if (cabeza.libro.id == id) {
                cabeza = cabeza.siguiente;
                return true;
            }

            NodoSimple actual = cabeza;

            while (actual.siguiente != null) {
                if (actual.siguiente.libro.id == id) {
                    actual.siguiente = actual.siguiente.siguiente;
                    return true;
                }

                actual = actual.siguiente;
            }

            return false;
        }

        void ordenarPorTitulo() {
            if (cabeza == null) return;

            boolean intercambiado;

            do {
                intercambiado = false;
                NodoSimple actual = cabeza;

                while (actual.siguiente != null) {
                    String titulo1 = actual.libro.titulo;
                    String titulo2 = actual.siguiente.libro.titulo;

                    if (titulo1.compareToIgnoreCase(titulo2) > 0) {
                        Libro temporal = actual.libro;
                        actual.libro = actual.siguiente.libro;
                        actual.siguiente.libro = temporal;
                        intercambiado = true;
                    }

                    actual = actual.siguiente;
                }
            } while (intercambiado);
        }
    }

    // ==========================================
    // NODO Y LISTA DOBLEMENTE ENLAZADA
    // ==========================================
    static class NodoDoble {
        Libro libro;
        NodoDoble anterior;
        NodoDoble siguiente;

        NodoDoble(Libro libro) {
            this.libro = libro;
        }
    }

    static class ListaDoble {
        NodoDoble cabeza;
        NodoDoble cola;
        int tamanio = 0;

        void insertarFinal(Libro libro) {
            NodoDoble nuevo = new NodoDoble(libro);

            if (cabeza == null) {
                cabeza = cola = nuevo;
            } else {
                cola.siguiente = nuevo;
                nuevo.anterior = cola;
                cola = nuevo;
            }

            tamanio++;
        }

        void insertarEnPosicion(Libro libro, int posicion) {
            if (posicion < 0 || posicion > tamanio) {
                System.out.println("Posicion no valida.");
                return;
            }

            if (posicion == tamanio) {
                insertarFinal(libro);
                return;
            }

            NodoDoble nuevo = new NodoDoble(libro);

            if (posicion == 0) {
                nuevo.siguiente = cabeza;
                cabeza.anterior = nuevo;
                cabeza = nuevo;
                tamanio++;
                return;
            }

            NodoDoble actual = cabeza;

            for (int i = 0; i < posicion; i++) {
                actual = actual.siguiente;
            }

            nuevo.anterior = actual.anterior;
            nuevo.siguiente = actual;
            actual.anterior.siguiente = nuevo;
            actual.anterior = nuevo;

            tamanio++;
        }

        boolean eliminar(int id) {
            NodoDoble actual = cabeza;

            while (actual != null && actual.libro.id != id) {
                actual = actual.siguiente;
            }

            if (actual == null) return false;

            if (actual.anterior != null) {
                actual.anterior.siguiente = actual.siguiente;
            } else {
                cabeza = actual.siguiente;
            }

            if (actual.siguiente != null) {
                actual.siguiente.anterior = actual.anterior;
            } else {
                cola = actual.anterior;
            }

            tamanio--;
            return true;
        }

        Libro buscar(int id) {
            NodoDoble actual = cabeza;

            while (actual != null) {
                if (actual.libro.id == id) {
                    return actual.libro;
                }
                actual = actual.siguiente;
            }

            return null;
        }

        void mostrarAdelante() {
            NodoDoble actual = cabeza;

            if (actual == null) {
                System.out.println("La lista esta vacia.");
                return;
            }

            while (actual != null) {
                System.out.println(actual.libro);
                actual = actual.siguiente;
            }
        }

        void mostrarAtras() {
            NodoDoble actual = cola;

            if (actual == null) {
                System.out.println("La lista esta vacia.");
                return;
            }

            while (actual != null) {
                System.out.println(actual.libro);
                actual = actual.anterior;
            }
        }

        void ordenarPorTitulo() {
            if (cabeza == null) return;

            boolean intercambiado;

            do {
                intercambiado = false;
                NodoDoble actual = cabeza;

                while (actual.siguiente != null) {
                    if (actual.libro.titulo.compareToIgnoreCase(
                            actual.siguiente.libro.titulo) > 0) {

                        Libro temporal = actual.libro;
                        actual.libro = actual.siguiente.libro;
                        actual.siguiente.libro = temporal;
                        intercambiado = true;
                    }

                    actual = actual.siguiente;
                }
            } while (intercambiado);
        }
    }

    // ==========================================
    // NODO Y LISTA CIRCULAR
    // ==========================================
    static class NodoCircular {
        String nombre;
        NodoCircular siguiente;

        NodoCircular(String nombre) {
            this.nombre = nombre;
        }
    }

    static class ListaCircular {
        NodoCircular ultimo;

        void insertar(String nombre) {
            NodoCircular nuevo = new NodoCircular(nombre);

            if (ultimo == null) {
                ultimo = nuevo;
                nuevo.siguiente = nuevo;
            } else {
                nuevo.siguiente = ultimo.siguiente;
                ultimo.siguiente = nuevo;
                ultimo = nuevo;
            }
        }

        void mostrarUnaVez() {
            if (ultimo == null) {
                System.out.println("No hay turnos registrados.");
                return;
            }

            NodoCircular primero = ultimo.siguiente;
            NodoCircular actual = primero;

            do {
                System.out.println(actual.nombre);
                actual = actual.siguiente;
            } while (actual != primero);
        }

        void simularTurnos(int cantidad) {
            if (ultimo == null) {
                System.out.println("No hay turnos registrados.");
                return;
            }

            NodoCircular actual = ultimo.siguiente;

            for (int i = 1; i <= cantidad; i++) {
                System.out.println(
                        "Turno " + i + ": " + actual.nombre
                );
                actual = actual.siguiente;
            }
        }
    }

    // ==========================================
    // FUNCIONES AUXILIARES
    // ==========================================
    static Libro leerLibro() {
        System.out.print("ID del libro: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Titulo: ");
        String titulo = sc.nextLine();

        System.out.print("Autor: ");
        String autor = sc.nextLine();

        return new Libro(id, titulo, autor);
    }

    static void cargarLibros(ListaSimple lista) {
        lista.insertarFinal(new Libro(1, "El principito", "Antoine de Saint-Exupery"));
        lista.insertarFinal(new Libro(2, "Don Quijote", "Miguel de Cervantes"));
        lista.insertarFinal(new Libro(3, "Cien anos de soledad", "Gabriel Garcia Marquez"));
    }

    static void cargarLibros(ListaDoble lista) {
        lista.insertarFinal(new Libro(1, "El principito", "Antoine de Saint-Exupery"));
        lista.insertarFinal(new Libro(2, "Don Quijote", "Miguel de Cervantes"));
        lista.insertarFinal(new Libro(3, "Cien anos de soledad", "Gabriel Garcia Marquez"));
    }

    // ==========================================
    // EJERCICIO 1
    // Registrar y mostrar libros
    // ==========================================
    static void ejercicio1() {
        ListaSimple lista = new ListaSimple();

        System.out.println("\nEJERCICIO 1: REGISTRAR LIBROS");

        for (int i = 0; i < 3; i++) {
            System.out.println("\nLibro " + (i + 1));
            lista.insertarFinal(leerLibro());
        }

        System.out.println("\nLibros registrados:");
        lista.mostrar();
    }

    // ==========================================
    // EJERCICIO 2
    // Buscar un libro por ID
    // ==========================================
    static void ejercicio2() {
        ListaSimple lista = new ListaSimple();
        cargarLibros(lista);

        System.out.println("\nEJERCICIO 2: BUSCAR LIBRO");
        lista.mostrar();

        System.out.print("Ingresa el ID que deseas buscar: ");
        int id = Integer.parseInt(sc.nextLine());

        Libro encontrado = lista.buscar(id);

        if (encontrado != null) {
            System.out.println("Libro encontrado: " + encontrado);
        } else {
            System.out.println("No existe un libro con ese ID.");
        }
    }

    // ==========================================
    // EJERCICIO 3
    // Insertar al inicio y al final
    // ==========================================
    static void ejercicio3() {
        ListaSimple lista = new ListaSimple();

        System.out.println("\nEJERCICIO 3: INSERTAR LIBROS");

        lista.insertarInicio(new Libro(1, "Libro al inicio", "Autor A"));
        lista.insertarFinal(new Libro(2, "Libro al final", "Autor B"));

        System.out.println("Resultado:");
        lista.mostrar();
    }

    // ==========================================
    // EJERCICIO 4
    // Eliminar un libro por ID
    // ==========================================
    static void ejercicio4() {
        ListaSimple lista = new ListaSimple();
        cargarLibros(lista);

        System.out.println("\nEJERCICIO 4: ELIMINAR LIBRO");
        lista.mostrar();

        System.out.print("ID del libro que deseas eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        if (lista.eliminar(id)) {
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("No se encontro el libro.");
        }

        lista.mostrar();
    }

    // ==========================================
    // EJERCICIO 5
    // Ordenar libros por titulo
    // ==========================================
    static void ejercicio5() {
        ListaSimple lista = new ListaSimple();
        cargarLibros(lista);

        System.out.println("\nEJERCICIO 5: ORDENAMIENTO DE LIBROS");
        System.out.println("Antes de ordenar:");
        lista.mostrar();

        lista.ordenarPorTitulo();

        System.out.println("\nDespues de ordenar:");
        lista.mostrar();
    }

    // ==========================================
    // EJERCICIO 6
    // Recorrer lista doble en ambas direcciones
    // ==========================================
    static void ejercicio6() {
        ListaDoble lista = new ListaDoble();
        cargarLibros(lista);

        System.out.println("\nEJERCICIO 6: RECORRIDO DOBLE");

        System.out.println("\nDe inicio a fin:");
        lista.mostrarAdelante();

        System.out.println("\nDe fin a inicio:");
        lista.mostrarAtras();
    }

    // ==========================================
    // EJERCICIO 7
    // Insertar y eliminar por posicion/ID
    // ==========================================
    static void ejercicio7() {
        ListaDoble lista = new ListaDoble();
        cargarLibros(lista);

        System.out.println("\nEJERCICIO 7: INSERTAR Y ELIMINAR");
        System.out.println("Lista inicial:");
        lista.mostrarAdelante();

        lista.insertarEnPosicion(
                new Libro(4, "Matematicas", "Autor D"), 1
        );

        System.out.println("\nDespues de insertar en posicion 1:");
        lista.mostrarAdelante();

        System.out.print("ID del libro que deseas eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        if (lista.eliminar(id)) {
            System.out.println("Libro eliminado.");
        } else {
            System.out.println("No se encontro el libro.");
        }

        lista.mostrarAdelante();
    }

    // ==========================================
    // EJERCICIO 8
    // Buscar y ordenar en lista doble
    // ==========================================
    static void ejercicio8() {
        ListaDoble lista = new ListaDoble();
        cargarLibros(lista);

        System.out.println("\nEJERCICIO 8: BUSQUEDA Y ORDENAMIENTO");
        lista.ordenarPorTitulo();

        System.out.println("Libros ordenados:");
        lista.mostrarAdelante();

        System.out.print("ID del libro que deseas buscar: ");
        int id = Integer.parseInt(sc.nextLine());

        Libro encontrado = lista.buscar(id);

        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    // ==========================================
    // EJERCICIO 9
    // Administrar turnos circulares
    // ==========================================
    static void ejercicio9() {
        ListaCircular turnos = new ListaCircular();

        System.out.println("\nEJERCICIO 9: TURNOS DE ATENCION");

        System.out.print("Cuantos turnos deseas registrar? ");
        int cantidad = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Nombre del turno o usuario " + i + ": ");
            turnos.insertar(sc.nextLine());
        }

        System.out.println("\nTurnos registrados:");
        turnos.mostrarUnaVez();
    }

    // ==========================================
    // EJERCICIO 10
    // Simular atencion continua
    // ==========================================
    static void ejercicio10() {
        ListaCircular turnos = new ListaCircular();

        turnos.insertar("Usuario A");
        turnos.insertar("Usuario B");
        turnos.insertar("Usuario C");

        System.out.println("\nEJERCICIO 10: SIMULACION DE TURNOS");
        System.out.print("Cuantos turnos deseas simular? ");

        int cantidad = Integer.parseInt(sc.nextLine());

        turnos.simularTurnos(cantidad);
    }

    // ==========================================
    // MENU PRINCIPAL
    // ==========================================
    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n==================================");
            System.out.println(" PRACTICA DE LISTAS EN JAVA");
            System.out.println("==================================");
            System.out.println("1. Registrar y mostrar libros");
            System.out.println("2. Buscar libro por ID");
            System.out.println("3. Insertar al inicio y al final");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Ordenar libros por titulo");
            System.out.println("6. Recorrido de lista doble");
            System.out.println("7. Insertar y eliminar en lista doble");
            System.out.println("8. Buscar y ordenar lista doble");
            System.out.println("9. Registrar turnos circulares");
            System.out.println("10. Simular turnos continuos");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1: ejercicio1(); break;
                    case 2: ejercicio2(); break;
                    case 3: ejercicio3(); break;
                    case 4: ejercicio4(); break;
                    case 5: ejercicio5(); break;
                    case 6: ejercicio6(); break;
                    case 7: ejercicio7(); break;
                    case 8: ejercicio8(); break;
                    case 9: ejercicio9(); break;
                    case 10: ejercicio10(); break;
                    case 0:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un numero valido.");
                opcion = -1;
            }

        } while (opcion != 0);

        sc.close();
    }
}