package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PokesalTest {
    private Pokesal pokesal;

    @BeforeEach
    void setUp() {
        pokesal = new Pokesal(
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
    }

    @Test
    void testReceberDanoMaiorQueHPAtual() {
        pokesal.receberDano(30);
        assertEquals(0, pokesal.getHpAtual());
    }

    @Test
    void testReceberDanoIgualHPAtual() {
        pokesal.receberDano(25);
        assertEquals(0, pokesal.getHpAtual());
    }

    @Test
    void testReceberDanoMenorQueHPAtual() {
        pokesal.receberDano(24);
        assertEquals(1, pokesal.getHpAtual());
    }

    @Test
    void testAplicarStatus() {
        assertTrue(pokesal.aplicarStatus(StatusPokesal.QUEIMADO));
        assertEquals(StatusPokesal.QUEIMADO, pokesal.getStatus());
    }

    @Test
    void testAplicarStatusEnquantoTiverOutroStatus() {
        pokesal.aplicarStatus(StatusPokesal.ENVENENADO);
        assertAll(
                () -> assertFalse(pokesal.aplicarStatus(StatusPokesal.ENVENENADO)),
                () -> assertFalse(pokesal.aplicarStatus(StatusPokesal.QUEIMADO))
        );
    }

    @Test
    void testAplicarStatusNeutroOuNull() {
        assertAll(
                () -> assertFalse(pokesal.aplicarStatus(StatusPokesal.NEUTRO)),
                () -> assertFalse(pokesal.aplicarStatus(null))
        );
    }

    @Test
    void testIncrementarTurnosVeneno() {
        pokesal.aplicarStatus(StatusPokesal.ENVENENADO);
        assertEquals(1, pokesal.getContadorTurnosVeneno());
        pokesal.incrementarTurnosVeneno();
        assertEquals(2, pokesal.getContadorTurnosVeneno());
    }

    @Test
    void testContadorTurnosVenenoAoReaplicarEnvenenado() {
        pokesal.aplicarStatus(StatusPokesal.ENVENENADO);
        assertEquals(1, pokesal.getContadorTurnosVeneno());
        pokesal.incrementarTurnosVeneno();
        pokesal.aplicarStatus(StatusPokesal.ENVENENADO);
        assertEquals(2, pokesal.getContadorTurnosVeneno());
    }

    @Test
    void testCurar() {
        assertTrue(pokesal.curar(10));
        assertEquals(35, pokesal.getHpAtual());
    }

    @Test
    void testCurarEnquantoHPEstaCheio() {
        pokesal.curar(25);
        assertFalse(pokesal.curar(20));
    }

    @Test
    void testCurarMaisQueHPMaximo() {
        pokesal.curar(50);
        assertEquals(50, pokesal.getHpAtual());
    }

    @Test
    void testCurarEnquantoDesmaiado() {
        pokesal.receberDano(25);
        assertFalse(pokesal.curar(50));
        assertEquals(0, pokesal.getHpAtual());
    }

    @Test
    void testCurarStatus() {
        pokesal.aplicarStatus(StatusPokesal.QUEIMADO);
        pokesal.curarStatus();
        assertEquals(StatusPokesal.NEUTRO, pokesal.getStatus());
    }

    @Test
    void testAtaqueEfetivoQuandoQueimado() {
        pokesal.aplicarStatus(StatusPokesal.QUEIMADO);
        assertEquals(13, pokesal.getAtaqueEfetivo());
    }
}
