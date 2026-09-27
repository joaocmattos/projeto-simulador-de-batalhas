package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AmbienteTest {
    private static final double DELTA = 0.0001;

    private static final Ambiente ASFALTO_ENSOLARADO
            = new Ambiente(Terreno.ASFALTO, Clima.ENSOLARADO);
    private static final Ambiente ASFALTO_CHUVOSO
            = new Ambiente(Terreno.ASFALTO, Clima.CHUVOSO);
    private static final Ambiente CANTEIRO_CENTRAL_ENSOLARADO
            = new Ambiente(Terreno.CANTEIRO_CENTRAL, Clima.ENSOLARADO);
    private static final Ambiente CANTEIRO_CENTRAL_CHUVOSO
            = new Ambiente(Terreno.CANTEIRO_CENTRAL, Clima.CHUVOSO);

    private static final Movimento MOVIMENTO_PLANTA = new Movimento(
            "Folha navalha",
            TipoElemental.PLANTA,
            40,
            Movimento.Categoria.OFENSIVO
    );
    private static final Movimento MOVIMENTO_FOGO = new Movimento(
            "Lança chamas",
            TipoElemental.FOGO,
            40,
            Movimento.Categoria.OFENSIVO
    );
    private static final Movimento MOVIMENTO_AGUA = new Movimento(
            "Bomba hídrica",
            TipoElemental.AGUA,
            40,
            Movimento.Categoria.OFENSIVO
    );

    @Test
    void testEfeitoTerrenoEstacionamentoUCSal() {
        assertAll(
                () -> assertEquals(
                        1.35,
                        ASFALTO_ENSOLARADO.modificadorDano(MOVIMENTO_FOGO),
                        DELTA
                ),
                () -> assertEquals(
                        0.75,
                        ASFALTO_ENSOLARADO.modificadorDano(MOVIMENTO_AGUA),
                        DELTA
                ),
                () -> assertEquals(
                        1.0,
                        ASFALTO_ENSOLARADO.modificadorDano(MOVIMENTO_PLANTA),
                        DELTA
                ),
                () -> assertEquals(
                        0.65,
                        ASFALTO_CHUVOSO.modificadorDano(MOVIMENTO_FOGO),
                        DELTA
                ),
                () -> assertEquals(
                        1.25,
                        ASFALTO_CHUVOSO.modificadorDano(MOVIMENTO_AGUA),
                        DELTA
                ),
                () -> assertEquals(
                        1.0,
                        ASFALTO_CHUVOSO.modificadorDano(MOVIMENTO_PLANTA),
                        DELTA
                ),
                () -> assertEquals(
                        1.25,
                        CANTEIRO_CENTRAL_ENSOLARADO.modificadorDano(MOVIMENTO_FOGO),
                        DELTA
                ),
                () -> assertEquals(
                        0.75,
                        CANTEIRO_CENTRAL_ENSOLARADO.modificadorDano(MOVIMENTO_AGUA),
                        DELTA
                ),
                () -> assertEquals(
                        0.65,
                        CANTEIRO_CENTRAL_CHUVOSO.modificadorDano(MOVIMENTO_FOGO),
                        DELTA
                ),
                () -> assertEquals(
                        1.25,
                        CANTEIRO_CENTRAL_CHUVOSO.modificadorDano(MOVIMENTO_AGUA),
                        DELTA
                ),
                () -> assertEquals(
                        1.15,
                        CANTEIRO_CENTRAL_ENSOLARADO.modificadorDano(MOVIMENTO_PLANTA),
                        DELTA
                ),
                () -> assertEquals(
                        1.15,
                        CANTEIRO_CENTRAL_CHUVOSO.modificadorDano(MOVIMENTO_PLANTA),
                        DELTA
                ),
                () -> {
                    Pokesal pokesal = new Pokesal(
                            "TesteSal",
                            TipoElemental.PLANTA,
                            50,
                            25,
                            25,
                            25,
                            25,
                            ItemSegurar.NENHUM,
                            List.of()
                    );

                    CANTEIRO_CENTRAL_ENSOLARADO.aplicarEfeitoFimDeTurno(pokesal);

                    assertEquals(28, pokesal.getHpAtual());
                }
        );
    }
}
