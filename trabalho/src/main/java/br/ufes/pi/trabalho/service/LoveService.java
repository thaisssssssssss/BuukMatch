package br.ufes.pi.trabalho.service;

import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ufes.pi.trabalho.domain.Love;
import br.ufes.pi.trabalho.dto.LoveRequest;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.LoveRepository;
import br.ufes.pi.trabalho.repository.UserRepository;
import br.ufes.pi.trabalho.repository.PostRepository;


@Service
public class LoveService {
    private final LoveRepository loveRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LoveService(LoveRepository loveRepository, UserRepository userRepository, PostRepository postRepository){
        this.loveRepository = loveRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }
    

    // registra um novo Love no banco de dados
    public void registerLoveOnPost(LoveRequest LoveRequest) {
        User u = userRepository
        .findById(LoveRequest.getIdUser())
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,"User não existe"));
        
        Post p = postRepository
        .findById(LoveRequest.getIdPost())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não existe"));
        

        // !!!!!! tem que buscar se existe o Love inverso
        // se existe cria um match também e coloca no banco de dados
        // >>>> matchService.registrar(UserAtual, donoDoPost); <<<<<
        // ai teria que chamar o serviço que cria o match
        Love novo_Love = new Love(u, p);
        loveRepository.save(novo_Love);
    
    }

} 
