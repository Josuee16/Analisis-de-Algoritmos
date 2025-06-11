/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algoritmosemana3;

/**
 *
 * @author Josue
 */
public class AlgoritmoSemana3 {

    /**
     * @param args the command line arguments
     */
    public static void merge(int[] A, int p, int q, int r) {
        int nL = q - p + 1; // Longitud de A[p:q]
        int nR = r - q;     // Longitud of A[q+1:r]

        int[] L = new int[nL];
        int[] R = new int[nR];

        // Copiar la data de temp a la matriz
        for (int i = 0; i < nL; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < nR; j++) {
            R[j] = A[q + 1 + j];
        }

        int i = 0, j = 0, k = p;

        // Fusionar las matrices temporales nuevamente en[p..r]
        while (i < nL && j < nR) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        // Copiar los elmentos restantes en L[] 
        while (i < nL) {
            A[k] = L[i];
            i++;
            k++;
        }

        // Copiar los elementos restantes of R[] if any
        while (j < nR) {
            A[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};

        int p = 0;
        int q = (array.length - 1) / 2;
        int r = array.length - 1;

        merge(array, p, q, r);

        System.out.println("Array luego de hacer merge:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

}
