public class Seleccion {
    public static void main(String[] args) {
        int[] numeros = {5, 3, 8, 1, 2};

        // Recorre las posiciones del arreglo
        for (int i = 0; i < numeros.length - 1; i++) {
            int menor = i;

            // Busca el elemento más pequeño
            for (int j = i + 1; j < numeros.length; j++) {
                if (numeros[j] < numeros[menor]) {
                    menor = j;
                }
            }

            // Intercambia el menor con la posición actual
            int temporal = numeros[i];
            numeros[i] = numeros[menor];
            numeros[menor] = temporal;
        }

        System.out.println("Arreglo ordenado:");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
    }
}