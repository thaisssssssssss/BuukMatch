package br.ufes.pi.trabalho.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conteudo;
    private LocalDateTime dataRecebimento;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Message(String conteudo, LocalDateTime dataRecebimento){
        setConteudo(conteudo);
        this.dataRecebimento = dataRecebimento;
    }

    protected Message(){}
}
