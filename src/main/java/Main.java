import model.*;
import model.FabricaPokesal.PokesalInicial;
import service.FabricaItemMochila;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Semente fixa só para a demonstração sempre dar o mesmo resultado.
        Random random = new Random(42);

        Ambiente ambiente = Ambiente.sortear(random);
        System.out.println("=== Torneio Pokésal - Estacionamento da UCSal Pituaçu ===");
        System.out.println("Terreno sorteado: " + ambiente.getTerreno());
        System.out.println("Clima sorteado: " + ambiente.getClima());

        Pokesal bulbaSal = FabricaPokesal.criar(PokesalInicial.BULBASAL);
        Pokesal charSal = FabricaPokesal.criar(PokesalInicial.CHARSAL);

        // Itens de segurar (Requisito Autoral 2)
        bulbaSal.equiparItem(ItemSegurar.CAPACETE_DE_PEDRA);
        charSal.equiparItem(ItemSegurar.ORBE_DE_VIDA);

        Treinador ana = new Treinador("Ana", bulbaSal);
        Treinador bruno = new Treinador("Bruno", charSal);

        ana.getMochila().adicionarItem(FabricaItemMochila.criarPocao());

        System.out.println("\n" + ana.getNome() + " escolheu " + bulbaSal.getNome()
                + " (item: " + bulbaSal.getItemEquipado() + ")");
        System.out.println(bruno.getNome() + " escolheu " + charSal.getNome()
                + " (item: " + charSal.getItemEquipado() + ")");

        Batalha batalha = new Batalha(ana, bruno, ambiente, random);

        int turno = 1;
        int limiteDeTurnos = 5; // trava de segurança contra loop infinito

        while (!bulbaSal.estaDesmaiado() && !charSal.estaDesmaiado() && turno <= limiteDeTurnos) {
            System.out.println("\n--- Turno " + turno + " ---");

            Treinador[] ordem = batalha.ordemDeAtaque();
            Treinador primeiro = ordem[0];
            Treinador segundo = ordem[1];

            Movimento golpePrimeiro = primeiro.getPokesalAtivo().getMovimentos().get(0);
            int danoPrimeiro = batalha.executarMovimento(primeiro, segundo, golpePrimeiro);
            System.out.println(primeiro.getPokesalAtivo().getNome() + " usou " + golpePrimeiro.getNome()
                    + " e causou " + danoPrimeiro + " de dano em " + segundo.getPokesalAtivo().getNome());

            if (!segundo.getPokesalAtivo().estaDesmaiado()) {
                Movimento golpeSegundo = segundo.getPokesalAtivo().getMovimentos().get(0);
                int danoSegundo = batalha.executarMovimento(segundo, primeiro, golpeSegundo);
                System.out.println(segundo.getPokesalAtivo().getNome() + " usou " + golpeSegundo.getNome()
                        + " e causou " + danoSegundo + " de dano em " + primeiro.getPokesalAtivo().getNome());
            }

            batalha.finalizarTurno();

            System.out.println("HP " + bulbaSal.getNome() + ": " + bulbaSal.getHpAtual() + "/" + bulbaSal.getHpMaximo());
            System.out.println("HP " + charSal.getNome() + ": " + charSal.getHpAtual() + "/" + charSal.getHpMaximo());

            turno++;
        }

        System.out.println("\n=== Fim de batalha ===");
        if (bulbaSal.estaDesmaiado()) {
            bruno.incrementarVitorias();
            System.out.println(bruno.getNome() + " venceu!");
        } else if (charSal.estaDesmaiado()) {
            ana.incrementarVitorias();
            System.out.println(ana.getNome() + " venceu!");
        } else {
            System.out.println("Limite de " + limiteDeTurnos + " turnos atingido (demonstração).");
        }
    }
}