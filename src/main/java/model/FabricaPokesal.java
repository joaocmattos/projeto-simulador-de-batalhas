package model;

import java.util.List;

public class FabricaPokesal {
    public enum PokesalInicial{
        BULBASAL, CHARSAL, SQUIRTSAL, CHIKOSAL, CYNDASAL, TOTOSAL
    }

    public static Pokesal criar(PokesalInicial inicial) {
        switch (inicial) {
            case BULBASAL:
                return new Pokesal("BulbaSal", TipoElemental.PLANTA, 45, 45, 49, 49, 45,
                        ItemSegurar.NENHUM,
                        List.of(new Movimento("Chicote de Videira", TipoElemental.PLANTA, 40, Movimento.Categoria.OFENSIVO)));

            case CHARSAL:
                return new Pokesal("CharSal", TipoElemental.FOGO, 39, 39, 52, 43, 65,
                        ItemSegurar.NENHUM,
                        List.of(new Movimento("Investida de Fogo", TipoElemental.FOGO, 40, Movimento.Categoria.OFENSIVO)));

            case SQUIRTSAL:
                return new Pokesal("SquirtSal", TipoElemental.AGUA, 44, 44, 48, 65, 43,
                        ItemSegurar.NENHUM,
                        List.of(new Movimento("Jato de Água", TipoElemental.AGUA, 40, Movimento.Categoria.OFENSIVO)));

            case CHIKOSAL:
                return new Pokesal("ChikoSal", TipoElemental.PLANTA, 45, 45, 49, 65, 45,
                        ItemSegurar.NENHUM,
                        List.of(new Movimento("Folha Navalha", TipoElemental.PLANTA, 40, Movimento.Categoria.OFENSIVO)));

            case CYNDASAL:
                return new Pokesal("CyndaSal", TipoElemental.FOGO, 39, 39, 52, 43, 65,
                        ItemSegurar.NENHUM,
                        List.of(new Movimento("Chamas Rápidas", TipoElemental.FOGO, 40, Movimento.Categoria.OFENSIVO)));

            case TOTOSAL:
                return new Pokesal("TotoSal", TipoElemental.AGUA, 50, 50, 65, 64, 43,
                        ItemSegurar.NENHUM,
                        List.of(new Movimento("Investida de Água", TipoElemental.AGUA, 40, Movimento.Categoria.OFENSIVO)));

            default:
                throw new IllegalArgumentException("Pokésal inicial inválido!");
        }
    }
}
