package org.example;

public class Pessoa {
    private String nome;
    private int telefone;
    private String email;
    private int id;

    public Pessoa(String nome, int telefone, String email){
        this.nome = "";
        this.telefone = 0;
        this.email = "";
    }

    public int getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(int telefone){
        this.telefone = telefone;
    }


}
