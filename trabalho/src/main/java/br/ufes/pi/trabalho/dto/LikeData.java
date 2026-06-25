package br.ufes.pi.trabalho.dto;

public class LikeData {
    private Long idUsuario;
    private Long idPostagem;

    public LikeData(Long idUsuario, Long idPostagem){
        this.idPostagem = idPostagem;
        this.idUsuario = idUsuario;
    }

    public Long getIdPostagem() {
        return idPostagem;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
}
