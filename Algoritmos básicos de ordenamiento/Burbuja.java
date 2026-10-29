public class Burbuja {
    public static void main(String[] args) {
        int[] numeros = {5, 3, 8, 1, 2};

        // Compara los elementos vecinos
        for (int i = 0; i < numeros.length - 1; i++) {
            for (int j = 0; j < numeros.length - 1 - i; j++) {

                // Intercambia si están en orden incorrecto
                if (numeros[j] > numeros[j + 1]) {
                    int temporal = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temporal;
                }
            }
        }

        System.out.println("Arreglo ordenado:");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
    }
}