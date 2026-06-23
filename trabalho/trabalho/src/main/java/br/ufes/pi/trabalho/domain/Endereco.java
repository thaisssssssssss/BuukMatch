package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Entity;

@Entity
public class Endereco{
    private String rua;
    private String cidade;
    private String bairro;
    private Long numero;

    public Endereco(String rua, String cidade, String bairro, Long numero){
        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public Long getNumero() {
        return numero;
    }
    public String getRua() {
        return rua;
    }

}