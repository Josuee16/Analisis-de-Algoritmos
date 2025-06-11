/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algoritmofibonacisemana7;

import java.util.Scanner;

/**
 *
 * @author Josue
 */
public class AlgoritmoFibonaciSemana7 {

    public static void fibonacciIterativo(int cantidad) {
        int a = 0, b = 1;

        for (int i = 0; i < cantidad; i++) {
            System.out.print(a + " ");
            int siguiente = a + b;
            a = b;
            b = siguiente;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Cuantos terminos de la serie Fibonacci deseas mostrar? ");
        int cantidad = scanner.nextInt();

        System.out.println("Serie de Fibonacci con " + cantidad + " terminos:");
        fibonacciIterativo(cantidad);

        scanner.close();
    }
}
