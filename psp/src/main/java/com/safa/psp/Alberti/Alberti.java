package com.safa.psp.Alberti;

public class Alberti {

    private final String externo = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String interno = "abcdefghijklmnopqrstuvwxyz";

    private int desplazamiento = 0;
    private int contador = 0;

    public char descifrar(char c) {
        int idx = interno.indexOf(c);
        if (idx == -1) return c;

        char claro = externo.charAt((idx + desplazamiento) % 26);

        contador++;
        if (contador == 10) {
            desplazamiento = (desplazamiento + 2) % 26;
            contador = 0;
        }

        return Character.toLowerCase(claro);
    }
}
