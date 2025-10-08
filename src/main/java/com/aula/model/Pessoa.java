package main.java.com.aula.model;


public class Pessoa {
    Integer id;
    String nome;
    String endereco;

    public Pessoa() {
    }

    public Pessoa(Integer id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa {\n\tID = "+id+
                "\n\tNome = "+nome+
                "\n\tEndereco = "+endereco+
                "\n}\n";
    }
}