package model;

import java.util.Random;

/**
 * Motor de batalha: ordem de ataque, cálculo de dano, itens de segurar,
 * movimentos de clima/terreno, uso de mochila e efeitos de fim de turno.
 *
 * Esta classe só manipula ESTADO (HP, status, ambiente). Não gera texto
 * de narração — isso é responsabilidade de quem chama (ex: Main.java).
 */
public class Batalha {

    private static final double CHANCE_CRITICO = 0.15;
    private static final double MULTIPLICADOR_CRITICO = 1.5;

    private static final double PERCENTUAL_DANO_QUEIMADURA = 0.06;
    private static final double PERCENTUAL_DANO_VENENO_POR_TURNO = 0.05;

    private final Treinador treinador1;
    private final Treinador treinador2;
    private final Ambiente ambiente;
    private final Random random;

    public Batalha(Treinador treinador1, Treinador treinador2, Ambiente ambiente, Random random) {
        this.treinador1 = treinador1;
        this.treinador2 = treinador2;
        this.ambiente = ambiente;
        this.random = random;
    }


    public Treinador[] ordemDeAtaque() {
        Pokesal p1 = treinador1.getPokesalAtivo();
        Pokesal p2 = treinador2.getPokesalAtivo();

        if (p1.getVelocidadeEfetiva() >= p2.getVelocidadeEfetiva()) {
            return new Treinador[]{treinador1, treinador2};
        }
        return new Treinador[]{treinador2, treinador1};
    }


    public boolean houveCritico() {
        return random.nextDouble() < CHANCE_CRITICO;
    }


    public int calcularDano(Pokesal atacante, Pokesal defensor, Movimento movimento, boolean critico) {
        if (!movimento.isOfensivo()) {
            return 0;
        }

        int danoBase = movimento.getDano() + atacante.getAtaqueEfetivo() - defensor.getDefesaEfetiva();
        if (danoBase < 1) {
            danoBase = 1;
        }

        double multiplicadorTipo = TipoElemental.obterMultiplicador(movimento.getTipo(), defensor.getTipo());
        double multiplicadorItem = atacante.getItemEquipado().getModificadorDeDanoCausado();
        double multiplicadorAmbiente = ambiente.modificadorDano(movimento);
        double multiplicadorCritico = critico ? MULTIPLICADOR_CRITICO : 1.0;

        double danoFinal = danoBase * multiplicadorTipo * multiplicadorItem
                * multiplicadorAmbiente * multiplicadorCritico;

        return (int) Math.round(danoFinal);
    }


    public int executarMovimento(Treinador treinadorAtacante, Treinador treinadorDefensor, Movimento movimento) {
        Pokesal atacante = treinadorAtacante.getPokesalAtivo();
        Pokesal defensor = treinadorDefensor.getPokesalAtivo();

        if (!atacante.podeExecutarMovimento(movimento)) {
            return 0;
        }

        if (movimento.getCategoria() == Movimento.Categoria.CLIMA) {
            aplicarMovimentoDeClima(movimento);
            return 0;
        }

        if (movimento.getCategoria() == Movimento.Categoria.TERRENO) {
            aplicarMovimentoDeTerreno(movimento);
            return 0;
        }

        boolean critico = houveCritico();
        int dano = calcularDano(atacante, defensor, movimento, critico);
        defensor.receberDano(dano);

        // Orbe de Vida: atacante perde 10% do HP máximo a cada ataque que executa
        int recuoAoAtacar = atacante.getItemEquipado().calcularDanoRecuoAoAtacar(atacante.getHpMaximo());
        if (recuoAoAtacar > 0) {
            atacante.receberDano(recuoAoAtacar);
        }

        // Capacete de Pedra: quem ataca o portador perde 1/6 do próprio HP máximo
        int recuoContraAtaque = defensor.getItemEquipado().calcularDanoRecuoContraAtaque(atacante.getHpMaximo());
        if (recuoContraAtaque > 0) {
            atacante.receberDano(recuoContraAtaque);
        }

        return dano;
    }

    private void aplicarMovimentoDeClima(Movimento movimento) {
        if (movimento.getTipo() == TipoElemental.FOGO) {
            ambiente.setClima(Clima.ENSOLARADO);
        } else if (movimento.getTipo() == TipoElemental.AGUA) {
            ambiente.setClima(Clima.CHUVOSO);
        }
    }

    private void aplicarMovimentoDeTerreno(Movimento movimento) {
        if (movimento.getTipo() == TipoElemental.PLANTA) {
            ambiente.setTerreno(Terreno.CANTEIRO_CENTRAL);
        }
    }

    /**
     * Um treinador usa um item da mochila em seu próprio pokésal ativo.
     */
    public void usarItem(Treinador treinador, ItemMochila item) {
        treinador.getMochila().usarItem(item, treinador.getPokesalAtivo());
    }

    /**
     * Aplica os efeitos de fim de turno: status dos dois pokésal e ambiente.
     */
    public void finalizarTurno() {
        aplicarEfeitoStatus(treinador1.getPokesalAtivo());
        aplicarEfeitoStatus(treinador2.getPokesalAtivo());

        ambiente.aplicarEfeitoFimDeTurno(treinador1.getPokesalAtivo());
        ambiente.aplicarEfeitoFimDeTurno(treinador2.getPokesalAtivo());
    }

    private void aplicarEfeitoStatus(Pokesal pokesal) {
        if (pokesal.estaDesmaiado()) {
            return;
        }

        StatusPokesal status = pokesal.getStatus();

        if (status == StatusPokesal.QUEIMADO) {
            int dano = (int) Math.round(pokesal.getHpMaximo() * PERCENTUAL_DANO_QUEIMADURA);
            pokesal.receberDano(dano);
        } else if (status == StatusPokesal.ENVENENADO) {
            pokesal.incrementarTurnosVeneno();
            int dano = (int) Math.round(
                    pokesal.getHpMaximo() * PERCENTUAL_DANO_VENENO_POR_TURNO * pokesal.getContadorTurnosVeneno()
            );
            pokesal.receberDano(dano);
        }
        // Paralisado não causa dano aqui: o efeito já reduz a SPD
        // via Pokesal.getVelocidadeEfetiva().
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public Treinador getTreinador1() {
        return treinador1;
    }

    public Treinador getTreinador2() {
        return treinador2;
    }
}