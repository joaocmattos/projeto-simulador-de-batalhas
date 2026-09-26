package model;

import java.util.Random;

public class Ambiente {
    private static final double CURA_CANTEIRO_CENTRAL_PLANTA = 1.0 / 16.0;
    private static final double BONUS_DANO_CANTEIRO_CENTRAL_PLANTA = 0.15;

    private static final double BONUS_DANO_FOGO_ENSOLARADO = 0.25;
    private static final double BONUS_DANO_FOGO_ENSOLARADO_ASFALTO = 0.35;
    private static final double PENALIDADE_DANO_AGUA_ENSOLARADO = 0.25;

    private static final double BONUS_DANO_AGUA_CHUVOSO = 0.25;
    private static final double PENALIDADE_DANO_FOGO_CHUVOSO = 0.35;

    private Terreno terreno;
    private Clima clima;

    public Ambiente(Terreno terreno, Clima clima){
        this.terreno = terreno;
        this.clima = clima;
    }

    public static Ambiente sortear(Random random){
        Terreno[] terrenos = Terreno.values();
        Clima[] climas = Clima.values();

        Terreno terrenoSorteado = terrenos[random.nextInt(terrenos.length)];
        Clima climaSorteado = climas[random.nextInt(climas.length)];

        return new Ambiente(terrenoSorteado, climaSorteado);
    }

    public double modificadorDano(Movimento movimento) {
        double modificador = 1.0;
        TipoElemental tipo = movimento.getTipo();

        if (this.terreno == Terreno.CANTEIRO_CENTRAL && tipo == TipoElemental.PLANTA){
            modificador *= (1.0 + BONUS_DANO_CANTEIRO_CENTRAL_PLANTA);
        }
        if (this.clima == Clima.ENSOLARADO){
            if (tipo == TipoElemental.FOGO) {
                if (this.terreno == Terreno.ASFALTO) {
                    modificador *= (1.0 + BONUS_DANO_FOGO_ENSOLARADO_ASFALTO);
                } else {
                    modificador *= (1.0 + BONUS_DANO_FOGO_ENSOLARADO);
                }
            } else if (tipo == TipoElemental.AGUA) {
                modificador *= (1.0 - PENALIDADE_DANO_AGUA_ENSOLARADO);
            }
        }
        if (this.clima == Clima.CHUVOSO) {
            if (tipo == TipoElemental.AGUA) {
                modificador *= (1.0 + BONUS_DANO_AGUA_CHUVOSO);
            } else if (tipo == TipoElemental.FOGO) {
                modificador *= (1.0 - PENALIDADE_DANO_FOGO_CHUVOSO);
            }
        }

        return modificador;
    }
    public void aplicarEfeitoFimDeTurno(Pokesal pokesal){
        if (pokesal.estaDesmaiado()){
            return;
        }
        if (this.terreno == Terreno.CANTEIRO_CENTRAL && pokesal.getTipo() == TipoElemental.PLANTA){
            int cura = (int) Math.round(pokesal.getHpMaximo() * CURA_CANTEIRO_CENTRAL_PLANTA);
            pokesal.curar(cura);
        }
    }
    public Terreno getTerreno(){
        return terreno;
    }

    public Clima getClima(){
        return clima;
    }

    public void setClima(Clima clima){
        this.clima = clima;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }
}


