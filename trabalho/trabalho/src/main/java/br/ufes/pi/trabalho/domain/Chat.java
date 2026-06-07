package br.ufes.pi.trabalho.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receptor;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Mensagem> mensagens = new ArrayList<>();

    private LocalDateTime horario;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Chat(String receptor) {
        this.setReceptor(receptor);
        this.setHorario();
    }

    protected Chat() {
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario() {
        this.horario = LocalDateTime.now();
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagens.add(mensagem);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
