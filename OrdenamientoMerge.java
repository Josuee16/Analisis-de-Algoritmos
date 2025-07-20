/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recubrimientominimoarbol;

public class OrdenamientoMerge {

    public static void ordenar(int[] arreglo) {
        dividir(arreglo, 0, arreglo.length - 1);
    }

    private static void dividir(int[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            int mitad = (inicio + fin) / 2;

            dividir(arreglo, inicio, mitad);
            dividir(arreglo, mitad + 1, fin);

            combinar(arreglo, inicio, mitad, fin);
        }
    }

    private static void combinar(int[] arreglo, int inicio, int mitad, int fin) {
        int tamañoIzq = mitad - inicio + 1;
        int tamañoDer = fin - mitad;

        int[] izquierda = new int[tamañoIzq];
        int[] derecha = new int[tamañoDer];

        // Copiar datos a arreglos temporales
        for (int i = 0; i < tamañoIzq; i++) {
            izquierda[i] = arreglo[inicio + i];
        }

        for (int j = 0; j < tamañoDer; j++) {
            derecha[j] = arreglo[mitad + 1 + j];
        }

        int i = 0, j = 0, k = inicio;

        // Mezclar las partes
        while (i < tamañoIzq && j < tamañoDer) {
            if (izquierda[i] <= derecha[j]) {
                arreglo[k++] = izquierda[i++];
            } else {
                arreglo[k++] = derecha[j++];
            }
        }

        // Copiar elementos restantes
        while (i < tamañoIzq) {
            arreglo[k++] = izquierda[i++];
        }

        while (j < tamañoDer) {
            arreglo[k++] = derecha[j++];
        }
    }

    public static void main(String[] args) {
        int[] datos = {25, 17, 31, 13, 2, 8, 24, 5};

        System.out.println("Antes de ordenar:");
        mostrar(datos);

        ordenar(datos);

        System.out.println("Después de ordenar:");
        mostrar(datos);
    }

    private static void mostrar(int[] arreglo) {
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
