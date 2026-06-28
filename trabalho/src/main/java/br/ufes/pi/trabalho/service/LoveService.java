package br.ufes.pi.trabalho.service;

import java.util.List;

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

    private final MatchService matchService;

    public LoveService(LoveRepository loveRepository, UserRepository userRepository, PostRepository postRepository, MatchService matchService){
        this.loveRepository = loveRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.matchService = matchService;
    }
    

    // registra um novo Love no banco de dados
    public void registerLoveOnPost(LoveRequest LoveRequest) {
        //quem curtiu
        User user = userRepository
                 .findById(LoveRequest.getIdUser())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao encontrado"));
        
        Post post = postRepository
                 .findById(LoveRequest.getIdPost())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post nao encontrado"));
        
        //quem fez o post
        User owner = post.getOwner();

        if(owner.equals(user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nao eh possivel curtir o proprio post");
        }

        Love newLove = new Love(user, post);
        loveRepository.save(newLove);


        List<Post> userPosts = postRepository.findByOwner(user);
        
        for(Post uP : userPosts){
            //pra cada post do user 
            // (que inicialmente curtiu o post de owner)
            //confere se owner curtiu
            boolean likedByOwner = loveRepository.existsByUserAndPost(owner, uP);

            if(likedByOwner){
                matchService.registrar(user, owner);
                break;
            }
        }




        // !!!!!! tem que buscar se existe o Love inverso
        // se existe cria um match também e coloca no banco de dados
        // >>>> matchService.registrar(UserAtual, donoDoPost); <<<<<
        // ai teria que chamar o serviço que cria o match
        //FEITO!!
    
    }

} 
