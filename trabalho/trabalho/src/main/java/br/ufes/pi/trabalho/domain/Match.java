package br.ufes.pi.trabalho.domain;
import java.util.ArrayList;


public class Match{
    private ArrayList<Mensagem> mensagens;
    private Usuario usuario1;
    private Usuario usuario2;

    public Match (Usuario usuario1, Usuario usuario2){
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.mensagens = new ArrayList<>();

    }

    public void adicionarMensagem(Mensagem mensagem) {
        mensagens.add(mensagem);
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }
}