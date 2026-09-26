package model;

public class Treinador {

    private final String nome;
    private Pokesal pokesalAtivo;
    private final Mochila mochila;
    private int vitorias;
    private boolean desconcentrado;

    public Treinador(String nome, Pokesal pokesalAtivo) {
        this.nome = nome;
        this.pokesalAtivo = pokesalAtivo;
        this.mochila = new Mochila();
        this.vitorias = 0;
        this.desconcentrado = false;
    }

    public void incrementarVitorias() {
        this.vitorias++;
    }

    public String getNome() {
        return nome;
    }

    public Pokesal getPokesalAtivo() {
        return pokesalAtivo;
    }

    public void setPokesalAtivo(Pokesal pokesalAtivo) {
        this.pokesalAtivo = pokesalAtivo;
    }

    public Mochila getMochila() {
        return mochila;
    }

    public int getVitorias() {
        return vitorias;
    }

    public boolean isDesconcentrado() {
        return desconcentrado;
    }

    public void setDesconcentrado(boolean desconcentrado) {
        this.desconcentrado = desconcentrado;
    }
}