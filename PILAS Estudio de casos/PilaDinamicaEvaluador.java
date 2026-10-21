public class PilaDinamicaEvaluador {

    // Nodo que almacena un dato y la referencia al siguiente nodo.
    static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Pila dinámica implementada manualmente.
    static class PilaDinamica<T> {
        private Nodo<T> tope;
        private int cantidad;

        public PilaDinamica() {
            tope = null;
            cantidad = 0;
        }

        // Push: agrega un elemento al tope.
        public void push(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            nuevo.siguiente = tope;
            tope = nuevo;
            cantidad++;
        }

        // Pop: retira y devuelve el elemento superior.
        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException(
                    "La pila esta vacia."
                );
            }

            T dato = tope.dato;
            tope = tope.siguiente;
            cantidad--;
            return dato;
        }

        // Peek: consulta el tope sin retirarlo.
        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException(
                    "La pila esta vacia."
                );
            }

            return tope.dato;
        }

        public boolean isEmpty() {
            return tope == null;
        }

        public int size() {
            return cantidad;
        }
    }

    // Determina la prioridad de cada operador.
    static int prioridad(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;

            default:
                return 0;
        }
    }

    // Realiza una operacion con dos numeros.
    static double calcular(double a, double b, char operador) {
        switch (operador) {
            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                if (b == 0) {
                    throw new ArithmeticException(
                        "Division entre cero."
                    );
                }
                return a / b;

            case '^':
                return Math.pow(a, b);

            default:
                throw new IllegalArgumentException(
                    "Operador no valido: " + operador
                );
        }
    }

    // Extrae dos valores y aplica un operador.
    static void aplicar(
        PilaDinamica<Double> valores,
        PilaDinamica<Character> operadores
    ) {
        if (valores.size() < 2 || operadores.isEmpty()) {
            throw new IllegalArgumentException(
                "Expresion incompleta."
            );
        }

        double b = valores.pop();
        double a = valores.pop();
        char operador = operadores.pop();

        valores.push(calcular(a, b, operador));
    }

    // Evalua una expresion infija.
    static double evaluar(String expresion) {
        if (expresion == null || expresion.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La expresion esta vacia."
            );
        }

        PilaDinamica<Double> valores =
            new PilaDinamica<>();

        PilaDinamica<Character> operadores =
            new PilaDinamica<>();

        int i = 0;
        boolean esperaOperando = true;

        while (i < expresion.length()) {
            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            // Leer un numero entero o decimal.
            if (Character.isDigit(c) || c == '.') {
                if (!esperaOperando) {
                    throw new IllegalArgumentException(
                        "Falta un operador."
                    );
                }

                int inicio = i;
                int puntos = 0;

                while (i < expresion.length()) {
                    char n = expresion.charAt(i);

                    if (Character.isDigit(n)) {
                        i++;
                    } else if (n == '.') {
                        puntos++;
                        if (puntos > 1) {
                            throw new IllegalArgumentException(
                                "Numero decimal invalido."
                            );
                        }
                        i++;
                    } else {
                        break;
                    }
                }

                String numero =
                    expresion.substring(inicio, i);

                double valor = Double.parseDouble(numero);

                if (!Double.isFinite(valor)) {
                    throw new IllegalArgumentException(
                        "Numero fuera de rango."
                    );
                }

                valores.push(valor);
                esperaOperando = false;
                continue;
            }

            if (c == '(') {
                if (!esperaOperando) {
                    throw new IllegalArgumentException(
                        "Falta un operador antes del parentesis."
                    );
                }

                operadores.push(c);
                i++;
                continue;
            }

            if (c == ')') {
                if (esperaOperando) {
                    throw new IllegalArgumentException(
                        "Parentesis vacio o expresion incompleta."
                    );
                }

                while (!operadores.isEmpty()
                       && operadores.peek() != '(') {
                    aplicar(valores, operadores);
                }

                if (operadores.isEmpty()) {
                    throw new IllegalArgumentException(
                        "Parentesis sin apertura."
                    );
                }

                operadores.pop();
                i++;
                esperaOperando = false;
                continue;
            }

            // Procesar operadores aritmeticos.
            if ("+-*/^".indexOf(c) >= 0) {
                if (esperaOperando) {
                    throw new IllegalArgumentException(
                        "Falta un operando."
                    );
                }

                while (!operadores.isEmpty()
                       && operadores.peek() != '('
                       && (prioridad(operadores.peek())
                           > prioridad(c)
                           || (prioridad(operadores.peek())
                               == prioridad(c) && c != '^'))) {

                    aplicar(valores, operadores);
                }

                operadores.push(c);
                esperaOperando = true;
                i++;
                continue;
            }

            throw new IllegalArgumentException(
                "Caracter no valido: " + c
            );
        }

        if (esperaOperando) {
            throw new IllegalArgumentException(
                "La expresion termina incompleta."
            );
        }

        // Resolver las operaciones pendientes.
        while (!operadores.isEmpty()) {
            if (operadores.peek() == '(') {
                throw new IllegalArgumentException(
                    "Falta cerrar un parentesis."
                );
            }

            aplicar(valores, operadores);
        }

        if (valores.size() != 1) {
            throw new IllegalArgumentException(
                "La expresion no es valida."
            );
        }

        return valores.pop();
    }

    public static void main(String[] args) {

        String[] expresiones = {
            "3 + 5 * 2",
            "(8 + 4) / 3",
            "10 - 2 * 3",
            "(6 + 2) * (5 - 3)",
            "18 / (3 + 3)",
            "2 ^ 3 + 4",
            "20 / (2 * 5) + 7",
            "(15 - 3) / (2 + 1)",
            "7 + 8 / (4 - 2)",
            "(2 + 3) * (4 + 1) - 6"
        };

        // Resolver los diez problemas.
        for (int i = 0; i < expresiones.length; i++) {
            try {
                double resultado = evaluar(expresiones[i]);

                System.out.println(
                    (i + 1) + ". " + expresiones[i]
                    + " = " + resultado
                );

            } catch (RuntimeException e) {
                System.out.println(
                    (i + 1) + ". Error: " + e.getMessage()
                );
            }
        }

        // Prueba de las operaciones basicas.
        PilaDinamica<Integer> pila = new PilaDinamica<>();

        pila.push(10);
        pila.push(20);
        pila.push(30);

        System.out.println("\nPrueba de la pila:");
        System.out.println("Peek: " + pila.peek());
        System.out.println("Pop: " + pila.pop());
        System.out.println("Nuevo tope: " + pila.peek());
        System.out.println("Cantidad: " + pila.size());
    }
}