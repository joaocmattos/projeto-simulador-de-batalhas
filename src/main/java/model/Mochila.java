package model;

import java.util.ArrayList;
import java.util.List;

public class Mochila {
    private static final int LIMITE_ITENS_POR_BATALHA = 2;

    private final List<ItemMochila> itens;
    private int itensUsados;

    public Mochila(){
        this.itens = new ArrayList<>();
        this.itensUsados = 0;
    }

    public void adicionarItem(ItemMochila item){
        this.itens.add(item);
    }

    public String usarItem(ItemMochila item, Pokesal alvo){
        if (this.itensUsados >= LIMITE_ITENS_POR_BATALHA){
            throw new LimiteItensExcedidoException(
                    "Limite de " + LIMITE_ITENS_POR_BATALHA + " itens por batalha excedido"
            );
        }
        String resultado = item.usar(alvo);
        this.itensUsados++;
        return resultado;
    }
    public int getItensUsados(){
        return itensUsados;
    }

    public int getLimiteItensPorBatalha(){
        return LIMITE_ITENS_POR_BATALHA;
    }

    public List<ItemMochila> getItens(){
        return itens;
    }
}
