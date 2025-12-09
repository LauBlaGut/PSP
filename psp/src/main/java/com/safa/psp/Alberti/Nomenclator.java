package com.safa.psp.Alberti;

import java.util.HashMap;
import java.util.Map;

public class Nomenclator {

    private final Map<String, String> mapa = new HashMap<>();

    public Nomenclator() {
        mapa.put("123", "felipeii");
        mapa.put("124", "rey");
        mapa.put("122", "walshingam");
    }

    public String traducir(String numero) {
        return mapa.getOrDefault(numero, "");
    }
}
