package service;

import model.CategoriaItemMochila;
import model.ItemMochila;
import model.Pokesal;
import model.StatusPokesal;

public class FabricaItemMochila {
    public static ItemMochila criarPocao() {
        return new ItemMochila("Poção", CategoriaItemMochila.CURA_HP, alvo -> {
            if (alvo.estaDesmaiado() || alvo.getHpAtual() == alvo.getHpMaximo()) {
                return mensagemSemEfeito(alvo);
            }
            int hpAnterior = alvo.getHpAtual();
            alvo.curar(20);
            int quantidadeCura = alvo.getHpAtual() - hpAnterior;
            return String.format("%s recuperou %d HP!", alvo.getNome(), quantidadeCura);
        });
    }

    public static ItemMochila criarCuraQueimadura() {
        return criarCuraStatus("Cura queimadura", StatusPokesal.QUEIMADO);
    }

    public static ItemMochila criarAntidoto() {
        return criarCuraStatus("Antídoto", StatusPokesal.ENVENENADO);
    }

    public static ItemMochila criarCuraParalisia() {
        return criarCuraStatus("Cura paralisia", StatusPokesal.PARALISADO);
    }

    private static ItemMochila criarCuraStatus(String nome, StatusPokesal statusCurado) {
        return new ItemMochila(nome, CategoriaItemMochila.CURA_STATUS, alvo -> {
            if (alvo.estaDesmaiado() || alvo.getStatus() != statusCurado) {
                return mensagemSemEfeito(alvo);
            }
            alvo.curarStatus();
            return String.format("%s foi curado do status %s!", alvo.getNome(), statusCurado.getNome());
        });
    }

    private static String mensagemSemEfeito(Pokesal alvo) {
        return String.format("O item não teve efeito em %s!", alvo.getNome());
    }
}
