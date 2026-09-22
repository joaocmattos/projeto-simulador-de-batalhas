package model;

public class ItemMochila {
    private final String nome;
    private final CategoriaItemMochila categoria;
    private final EfeitoItemMochila efeito;

    public ItemMochila(String nome, CategoriaItemMochila categoria, EfeitoItemMochila efeito) {
        this.nome = nome;
        this.categoria = categoria;
        this.efeito = efeito;
    }

    public String usar(Pokesal alvo) {
        return this.efeito.aplicar(alvo);
    }

    public String getNome() {
        return nome;
    }

    public CategoriaItemMochila getCategoria() {
        return categoria;
    }
}
