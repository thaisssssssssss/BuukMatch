package br.ufes.pi.trabalho.dto;

public class SendMessageRequest {
    private Long remetenteId;
    private String conteudo;
    
    public String getConteudo() {
        return conteudo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    public Long getRemetenteId() {
        return remetenteId;
    }
    
    public void setRemetenteId(Long remetenteId) {
        this.remetenteId = remetenteId;
    }

    
    
}
