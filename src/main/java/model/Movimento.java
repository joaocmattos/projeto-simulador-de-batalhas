package model;

public class Movimento {
    public enum Categoria { OFENSIVO, CLIMA, TERRENO }

    private final String nome;
    private final TipoElemental tipo;
    private final int dano;
    private final Categoria categoria;

    public Movimento(String nome, TipoElemental tipo, int dano, Categoria categoria) {
        this.nome = nome;
        this.tipo = tipo;
        this.dano = dano;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public TipoElemental getTipo() {
        return tipo;
    }

    public int getDano() {
        return dano;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public boolean isOfensivo() {
        return this.categoria == Categoria.OFENSIVO;
    }
}
