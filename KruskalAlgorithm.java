/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recubrimientominimoarbol;

import java.util.*;

public class KruskalAlgorithm {

    static class Edge implements Comparable<Edge> {

        int origen, destino, peso;

        public Edge(int origen, int destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        @Override
        public int compareTo(Edge otra) {
            return this.peso - otra.peso;
        }
    }

    static class Subconjunto {

        int padre, rango;
    }

    int vertices;
    List<Edge> aristas;

    public KruskalAlgorithm(int vertices) {
        this.vertices = vertices;
        aristas = new ArrayList<>();
    }

    public void agregarArista(int origen, int destino, int peso) {
        aristas.add(new Edge(origen, destino, peso));
    }

    int encontrar(Subconjunto subconjuntos[], int i) {
        if (subconjuntos[i].padre != i) {
            subconjuntos[i].padre = encontrar(subconjuntos, subconjuntos[i].padre);
        }
        return subconjuntos[i].padre;
    }

    void unir(Subconjunto subconjuntos[], int x, int y) {
        int raizX = encontrar(subconjuntos, x);
        int raizY = encontrar(subconjuntos, y);

        if (subconjuntos[raizX].rango < subconjuntos[raizY].rango) {
            subconjuntos[raizX].padre = raizY;
        } else if (subconjuntos[raizX].rango > subconjuntos[raizY].rango) {
            subconjuntos[raizY].padre = raizX;
        } else {
            subconjuntos[raizY].padre = raizX;
            subconjuntos[raizX].rango++;
        }
    }

    public void kruskal() {
        List<Edge> resultado = new ArrayList<>();

        Collections.sort(aristas);

        Subconjunto[] subconjuntos = new Subconjunto[vertices];
        for (int v = 0; v < vertices; ++v) {
            subconjuntos[v] = new Subconjunto();
            subconjuntos[v].padre = v;
            subconjuntos[v].rango = 0;
        }

        int i = 0;
        while (resultado.size() < vertices - 1 && i < aristas.size()) {
            Edge siguienteArista = aristas.get(i++);

            int x = encontrar(subconjuntos, siguienteArista.origen);
            int y = encontrar(subconjuntos, siguienteArista.destino);

            if (x != y) {
                resultado.add(siguienteArista);
                unir(subconjuntos, x, y);
            }
        }

        System.out.println("Aristas en el Árbol de Recubrimiento Mínimo:");
        int pesoTotal = 0;
        for (Edge arista : resultado) {
            System.out.println(arista.origen + " -- " + arista.destino + " == " + arista.peso);
            pesoTotal += arista.peso;
        }
        System.out.println("Peso total del Árbol de Recubrimiento Mínimo: " + pesoTotal);
    }

    public static void main(String[] args) {
        KruskalAlgorithm grafo = new KruskalAlgorithm(6);

        // Definir las aristas (origen, destino, peso)
        grafo.agregarArista(0, 1, 4);
        grafo.agregarArista(0, 2, 4);
        grafo.agregarArista(1, 2, 2);
        grafo.agregarArista(1, 3, 5);
        grafo.agregarArista(2, 3, 5);
        grafo.agregarArista(2, 4, 11);
        grafo.agregarArista(3, 4, 2);
        grafo.agregarArista(3, 5, 1);
        grafo.agregarArista(4, 5, 7);

        grafo.kruskal();
    }
}
