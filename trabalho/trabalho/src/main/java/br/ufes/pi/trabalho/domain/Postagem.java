package br.ufes.pi.trabalho.domain;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Postagem{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String textoDescritivo;
    private LocalDateTime dataPublicacao ;
    private String foto;

    @ManyToOne
    private Usuario dono;
    
    private StatusPostagem status;
    
    @Enumerated(EnumType.STRING)
    private StatusPostagem _status; 
    // tem que inicializar o dono
    public Postagem(String texto_descritivo, LocalDateTime data_publicacao, String foto){
        this.textoDescritivo = texto_descritivo;
        this.foto = foto;
        this.dataPublicacao = data_publicacao;
        this.status = StatusPostagem.RESERVADO;
    }
    public Postagem() {}

    public String getTextoDescritivo() {
        return textoDescritivo;
    }
    public String getFoto() {
        return foto;
    }
    public StatusPostagem getStatus() {
        return status;
    }
    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }
    public Long getId() {
        return id;
    }

    public Usuario getDono() {
        return dono;
    }
}