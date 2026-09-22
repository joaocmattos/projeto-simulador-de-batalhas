package model;

import java.util.HashMap;
import java.util.Map;

public enum TipoElemental {
    PLANTA,
    FOGO,
    AGUA;

    private static final Map<TipoElemental, Map<TipoElemental, Double>> MATRIZ = new HashMap<>();

    private static void efetividade(
            TipoElemental atacante,
            TipoElemental defensor,
            double multiplicador
    ) {
        MATRIZ.computeIfAbsent(atacante, k -> new HashMap<>())
                .put(defensor, multiplicador);
    }

    static {
        efetividade(PLANTA, AGUA, 2.0);
        efetividade(PLANTA, FOGO, 0.5);
        efetividade(FOGO, PLANTA, 2.0);
        efetividade(FOGO, AGUA, 0.5);
        efetividade(AGUA, FOGO, 2.0);
        efetividade(AGUA, PLANTA, 0.5);
    }

    public static double obterMultiplicador(TipoElemental atacante, TipoElemental defensor) {
        return MATRIZ.get(atacante).getOrDefault(defensor, 1.0);
    }
}
