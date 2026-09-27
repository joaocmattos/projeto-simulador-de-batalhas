package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FabricaItemMochila;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MochilaTest {
    private Mochila mochila;
    private Pokesal pokesal;

    @BeforeEach
    void setUp() {
        mochila = new Mochila();
        mochila.adicionarItem(FabricaItemMochila.criarPocao());
        mochila.adicionarItem(FabricaItemMochila.criarPocao());
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
    void testUsoLimiteDeItensExcedido() {
        mochila.usarItem(mochila.getItens().getFirst(), pokesal);
        mochila.usarItem(mochila.getItens().get(1), pokesal);
        assertThrows(
                LimiteItensExcedidoException.class,
                () -> mochila.usarItem(mochila.getItens().get(1), pokesal)
        );
    }
}
