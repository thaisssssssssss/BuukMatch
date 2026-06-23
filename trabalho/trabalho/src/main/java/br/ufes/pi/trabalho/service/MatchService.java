package br.ufes.pi.trabalho.service;

import org.springframework.stereotype.Service;

import br.ufes.pi.trabalho.domain.Match;
import br.ufes.pi.trabalho.domain.Usuario;
import br.ufes.pi.trabalho.repository.MatchRepository;
import br.ufes.pi.trabalho.repository.UsuarioRepository;

@Service
public class MatchService {
    private final  MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }
    public void registrar(Usuario usuario1 , Usuario usuario2){
        Match match = new Match(usuario1, usuario2); 
        usuario1.adiciona_match(match);
        usuario2.adiciona_match(match);

        this.matchRepository.save(match);
    }
}
