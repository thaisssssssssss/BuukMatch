package br.ufes.pi.trabalho.service;

import org.springframework.stereotype.Service;

import br.ufes.pi.trabalho.domain.Match;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.MatchRepository;
import br.ufes.pi.trabalho.repository.UserRepository;

@Service
public class MatchService {
    private final  MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }
    public void registrar(User user1 , User user2){
        Match match = new Match(user1, user2); 
        user1.addMatch(match);
        user2.addMatch(match);

        this.matchRepository.save(match);
    }
}
