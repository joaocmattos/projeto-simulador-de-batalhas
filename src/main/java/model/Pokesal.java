package model;

public class Pokesal {
    private String nome;
    private TipoElemental tipo;
    private int hpMaximo;
    private int hpAtual;
    private int ataque;
    private int defesa;
    private int velocidade;
    private ItemSegurar itemEquipado;

    public Pokesal(
            String nome,
            TipoElemental tipo,
            int hpMaximo,
            int hpAtual,
            int ataque,
            int defesa,
            int velocidade,
            ItemSegurar itemEquipado
    ) {
        this.nome = nome;
        this.tipo = tipo;
        this.hpMaximo = hpMaximo;
        this.hpAtual = hpAtual;
        this.ataque = ataque;
        this.defesa = defesa;
        this.velocidade = velocidade;
        this.itemEquipado = itemEquipado;
    }

    
}
