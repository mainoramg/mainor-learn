package com.nan.progra1.ex1;

public class Matriz {

    private int[][] m;

    public Matriz(int filas, int columnas) {
        filas = filas < 3 ? 3 : filas;
        columnas = columnas < 3 ? 3 : columnas % 2 == 0 ? 3 : columnas;

        m = new int[filas][columnas];
    }

    public void imprimePiramide() {
//        System.out.println("Matriz filas: "+m.length);
//        System.out.println("Matriz columnas: "+m[0].length);
        int sumatoria = 0;
        for (int indexFilas = 0, valorCelda = 1, indexPiramide = m[0].length/2; indexFilas < m.length; indexFilas++) {
            for (int indexColumnas = m[indexFilas].length-1; indexColumnas >= 0; indexColumnas--) {
                m[indexFilas][indexColumnas] = valorCelda;
                valorCelda++;
            }
            if (indexFilas == 0) {
                System.out.println(m[indexFilas][indexPiramide-indexFilas]);
                sumatoria += m[indexFilas][indexPiramide-indexFilas];
            } else {
                if (indexPiramide+indexFilas < m[0].length && indexPiramide-indexFilas >= 0) {
                    System.out.println(m[indexFilas][indexPiramide+indexFilas]);
                    System.out.println(m[indexFilas][indexPiramide-indexFilas]);
                    sumatoria += m[indexFilas][indexPiramide+indexFilas];
                    sumatoria += m[indexFilas][indexPiramide-indexFilas];
                }
            }
        }
        System.out.println("Sumatoria: " + sumatoria);
//        System.out.println();
//        for (int indexFilas = 0; indexFilas < m.length; indexFilas++) {
//            for (int indexColumnas = 0; indexColumnas < m[indexFilas].length; indexColumnas++) {
//                if (m[indexFilas][indexColumnas] < 10) System.out.print(" ");
//                System.out.print(m[indexFilas][indexColumnas] + "   ");
//            }
//            System.out.println();
//        }
    }
}
