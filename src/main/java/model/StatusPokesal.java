package model;

public enum StatusPokesal {
    NEUTRO("Neutro"),
    QUEIMADO("Queimado"),
    ENVENENADO("Envenenado"),
    PARALISADO("Paralisado");

    private final String nome;

    StatusPokesal(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
