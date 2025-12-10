package org.example;

public class DiscoAlberti {

    private final String DISCO_FIJO  = "ABCDEFGILMNOPQRSTVXZ1234";
    private final String DISCO_MOVIL = "aklnprtz&xfdbigpqsuyom12";

    public char descifrar(char c) {
        int index = DISCO_MOVIL.indexOf(c);
        if (index != -1) {
            return DISCO_FIJO.charAt(index);
        }
        return c; // espacios o símbolos
    }
}