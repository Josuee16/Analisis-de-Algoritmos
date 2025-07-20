import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneradorCongruencialLineal {

    private long a = 1664525;
    private long c = 1013904223;
    private long m = (long) Math.pow(2, 32); // 2^32
    private long semilla;
    private long estado;

    public GeneradorCongruencialLineal(long semilla) {
        this.semilla = semilla;
        this.estado = semilla;
    }

    private long siguiente() {
        estado = (a * estado + c) % m;
        return estado;
    }


    public double siguienteNormalizado() {
        return (double) siguiente() / m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la semilla (valor inicial): ");
        long semilla = sc.nextLong();

        GeneradorCongruencialLineal gen = new GeneradorCongruencialLineal(semilla);

        double[] numeros = new double[100];
        for (int i = 0; i < 100; i++) {
            numeros[i] = gen.siguienteNormalizado();
        }

        System.out.println("\nPrimeros 10 números generados (normalizados en [0,1)):");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%.10f\n", numeros[i]);
        }

        System.out.println("\n--- ANALISIS DE LA DISTRIBUCION ---");

        // Definir los intervalos y contadores
        double[] intervalos = {0.0, 0.2, 0.4, 0.6, 0.8, 1.0};
        int[] conteos = new int[intervalos.length - 1]; 

   
        for (double num : numeros) { 
            for (int i = 0; i < intervalos.length - 1; i++) {
                if (num >= intervalos[i] && (num < intervalos[i + 1] || (i == intervalos.length - 2 && num == 1.0))) {
                    conteos[i]++;
                    break;
                }
            }
        }

 
        for (int i = 0; i < conteos.length; i++) {
            System.out.printf("Intervalo [%.1f, %.1f): %d numeros%n", intervalos[i], intervalos[i + 1], conteos[i]);
        }


        System.out.println("\n--- RESPONDER ---");
        System.out.print("La distribucion es aproximadamente uniforme? ");

        
        boolean esUniforme = true;
        int totalNumerosAnalizados = numeros.length;
        double promedioEsperado = (double) totalNumerosAnalizados / conteos.length;
        double tolerancia = 0.20 * promedioEsperado; 

        for (int count : conteos) {
            if (Math.abs(count - promedioEsperado) > tolerancia) {
                esUniforme = false;
                break;
            }
        }

        if (esUniforme) {
            System.out.println("Si, la distribucion de los 100 numeros es **aproximadamente uniforme**.");
            System.out.println("Los conteos en cada intervalo están relativamente cerca del promedio esperado de " + String.format("%.2f", promedioEsperado) + ".");
        } else {
            System.out.println("No, la distribucion de los 100 numeros **no es aproximadamente uniforme**.");
            System.out.println("Hay una desviacion significativa de los conteos esperados en algunos intervalos.");
        }

        System.out.println("\nQue efecto tiene cambiar la semilla?");
        System.out.println("Cambiar la **semilla (seed)** de un generador de numeros pseudoaleatorios produce una **secuencia completamente diferente de números**.");
        System.out.println("Si se usa la **misma semilla**, se obtiene la misma secuencia cada vez (util para reproducibilidad).");
        System.out.println("Si se **cambia la semilla**, la secuencia generada sera distinta, lo cual es importante para obtener diferentes conjuntos de numeros aleatorios y evitar sesgos en simulaciones o pruebas.");

        sc.close();
    }
}