public class QuickSort {

    // Divide el arreglo usando el último elemento como pivote
    static int particion(int[] numeros, int inicio, int fin) {
        int pivote = numeros[fin];
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (numeros[j] <= pivote) {
                i++;

                int temporal = numeros[i];
                numeros[i] = numeros[j];
                numeros[j] = temporal;
            }
        }

        // Coloca el pivote en su posición correcta
        int temporal = numeros[i + 1];
        numeros[i + 1] = numeros[fin];
        numeros[fin] = temporal;

        return i + 1;
    }

    // Ordena recursivamente las dos partes
    static void ordenar(int[] numeros, int inicio, int fin) {
        if (inicio < fin) {
            int posicion = particion(numeros, inicio, fin);

            ordenar(numeros, inicio, posicion - 1);
            ordenar(numeros, posicion + 1, fin);
        }
    }

    public static void main(String[] args) {
        int[] numeros = {5, 3, 8, 1, 2};

        ordenar(numeros, 0, numeros.length - 1);

        System.out.println("Arreglo ordenado:");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
    }
}