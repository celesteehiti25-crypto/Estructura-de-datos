public class Insercion {
    public static void main(String[] args) {
        int[] numeros = {5, 3, 8, 1, 2};

        // Recorre el arreglo desde el segundo elemento
        for (int i = 1; i < numeros.length; i++) {
            int actual = numeros[i];
            int j = i - 1;

            // Desplaza a la derecha los números mayores
            while (j >= 0 && numeros[j] > actual) {
                numeros[j + 1] = numeros[j];
                j--;
            }

            // Inserta el elemento en su posición
            numeros[j + 1] = actual;
        }

        System.out.println("Arreglo ordenado:");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
    }
}