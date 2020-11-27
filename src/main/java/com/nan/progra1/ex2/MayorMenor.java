package com.nan.progra1.ex2;

public class MayorMenor {

    static int[][] m = {
            {7,0,8,9},
            {12,16,5,2},
            {3,5,13,17},
            {5,9,22,11}
    };

//    static int[][] m = {
//            {7,9},
//            {5,11}
//    };

    static int mayor = 0;
    static int menor = 0;

    public static void main(String[] args) {
        imprimirMayorMenor(-1, -1);
        System.out.println();
        System.out.println("El número mayor de la matriz fue: "+mayor);
        System.out.println("El número menor de la matriz fue: "+menor);
    }

    static void imprimirMayorMenor(int fila, int columna) {
        columna = columna < m.length-1 ? columna+1 : 0; // incrementa columna
        if (columna == 0 && fila < m.length) {
            fila = fila+1; // incrementa fila
        }
        mayor = m[fila][columna] > mayor ? m[fila][columna] : mayor;
        System.out.println("Subiendo: matriz["+fila+"]["+columna+"]="+m[fila][columna]+" Mayor: "+mayor);
        if (fila == m.length-1 && columna == m.length-1) { // llegamos al ultimo elemento de la matriz
            System.out.println("Ha llegado al caso base!!!");
            menor = m[fila][columna]; // menor se le asigna el valor del ultimo elemento de la matriz
            System.out.println("Bajando: matriz["+fila+"]["+columna+"]="+m[fila][columna]+" menor: "+menor);
            return;
        }
        imprimirMayorMenor(fila, columna);
        menor = m[fila][columna] < menor ? m[fila][columna] : menor;
        System.out.println("Bajando: matriz["+fila+"]["+columna+"]="+m[fila][columna]+" menor: "+menor);
    }
}
