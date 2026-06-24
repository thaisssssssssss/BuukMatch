package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Embeddable
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