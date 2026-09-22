package model;

import java.util.ArrayList;
import java.util.List;

public class Pokesal {
    private final String nome;
    private final TipoElemental tipo;
    private final int hpMaximo;
    private int hpAtual;
    private final int ataque;
    private final int defesa;
    private final int velocidade;
    private ItemSegurar itemEquipado;
    private final List<Movimento> movimentos;

    public Pokesal(
            String nome,
            TipoElemental tipo,
            int hpMaximo,
            int hpAtual,
            int ataque,
            int defesa,
            int velocidade,
            ItemSegurar itemEquipado,
            List<Movimento> movimentos
    ) {
        this.nome = nome;
        this.tipo = tipo;
        this.hpMaximo = hpMaximo;
        this.hpAtual = hpAtual;
        this.ataque = ataque;
        this.defesa = defesa;
        this.velocidade = velocidade;
        this.itemEquipado = itemEquipado;
        this.movimentos = new ArrayList<>(movimentos);
    }

    public int getDefesaEfetiva() {
        return (int) Math.round(this.defesa * this.itemEquipado.getModificadorDeDefesa());
    }

    public boolean podeExecutarMovimento(Movimento movimento) {
        return this.itemEquipado.checarMovimentoPermitido(movimento.isOfensivo());
    }

    public void equiparItem(ItemSegurar item) {
        this.itemEquipado = item != null ? item : ItemSegurar.NENHUM;
    }

    public void receberDano(int dano) {
        this.hpAtual = Math.max(0, this.hpAtual - dano);
    }

    public void curar(int cura) {
        this.hpAtual = Math.min(this.hpMaximo, this.hpAtual + cura);
    }

    public boolean estaDesmaiado() {
        return this.hpAtual <= 0;
    }

    public String getNome() {
        return nome;
    }

    public TipoElemental getTipo() {
        return tipo;
    }

    public int getHpMaximo() {
        return hpMaximo;
    }

    public int getHpAtual() {
        return hpAtual;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public ItemSegurar getItemEquipado() {
        return itemEquipado;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }
}
