package model;

import org.junit.jupiter.api.Test;

import static model.TipoElemental.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TipoElementalTest {
    private static final double DELTA = 0.0001;

    @Test
    void testVantagemElemental() {
        assertAll(
                () -> assertEquals(
                        2.0, TipoElemental.obterMultiplicador(PLANTA, AGUA), DELTA
                ),
                () -> assertEquals(
                        0.5, TipoElemental.obterMultiplicador(PLANTA, FOGO), DELTA
                ),
                () -> assertEquals(
                        0.5, TipoElemental.obterMultiplicador(PLANTA, PLANTA), DELTA
                ),
                () -> assertEquals(
                        2.0, TipoElemental.obterMultiplicador(FOGO, PLANTA), DELTA
                ),
                () -> assertEquals(
                        0.5, TipoElemental.obterMultiplicador(FOGO, AGUA), DELTA
                ),
                () -> assertEquals(
                        0.5, TipoElemental.obterMultiplicador(FOGO, FOGO), DELTA
                ),
                () -> assertEquals(
                        2.0, TipoElemental.obterMultiplicador(AGUA, FOGO), DELTA
                ),
                () -> assertEquals(
                        0.5, TipoElemental.obterMultiplicador(AGUA, PLANTA), DELTA
                ),
                () -> assertEquals(
                        0.5, TipoElemental.obterMultiplicador(AGUA, AGUA), DELTA
                )
        );
    }
}
