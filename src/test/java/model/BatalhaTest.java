package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class BatalhaTest {
    private Treinador treinador1, treinador2;
    private Batalha batalha;

    @BeforeEach
    void setUp() {
        Ambiente ambiente = new Ambiente(Terreno.ASFALTO, Clima.ENSOLARADO);
        treinador1 = new Treinador(
                "Red",
                FabricaPokesal.criar(FabricaPokesal.PokesalInicial.BULBASAL)
        );
        treinador2 = new Treinador(
                "Green",
                FabricaPokesal.criar(FabricaPokesal.PokesalInicial.SQUIRTSAL)
        );
        batalha = new Batalha(treinador1, treinador2, ambiente, new Random(67));
    }

    @Test
    void testCalculoDanoBoundaryValues() {
        int danoCalculado = batalha.calcularDano(
                treinador1.getPokesalAtivo(),
                treinador2.getPokesalAtivo(),
                treinador1.getPokesalAtivo().getMovimentos().getFirst(),
                false
        );
        assertEquals(48, danoCalculado);
    }

    @Test
    void testOrdemDeAtaquePorVelocidade() {
        assertSame(treinador1, batalha.ordemDeAtaque()[0]);
    }

    @Test
    void testCalcularDanoCritico() {
        int danoComCritico = batalha.calcularDano(
                treinador1.getPokesalAtivo(),
                treinador2.getPokesalAtivo(),
                treinador1.getPokesalAtivo().getMovimentos().getFirst(),
                true
        );
        assertEquals(72, danoComCritico);
    }
}
