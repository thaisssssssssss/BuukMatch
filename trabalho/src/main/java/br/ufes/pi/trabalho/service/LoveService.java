package br.ufes.pi.trabalho.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ufes.pi.trabalho.domain.Love;
import br.ufes.pi.trabalho.domain.Match;
import br.ufes.pi.trabalho.dto.NotificationResponse;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.dto.LoveRequest;
import br.ufes.pi.trabalho.repository.LoveRepository;
import br.ufes.pi.trabalho.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoveService {
    private final LoveRepository loveRepository;
    private final PostRepository postRepository;

    private final MatchService matchService;
    private final UserService userService;
    private final NotificationService notificationService;

    public LoveService(LoveRepository loveRepository, PostRepository postRepository, MatchService matchService, UserService userService, NotificationService notificationService){
        this.loveRepository = loveRepository;
        this.postRepository = postRepository;
        this.matchService = matchService;
        this.userService = userService;
        this.notificationService = notificationService;
    }
    

    /**
     * Regras de negócio para registrar um novo Love (curtida de match) no banco de dados.
     *<br><br>
     *
     * Confere se o usuário que curtiu o post existe no banco de dados.
     * Confere se o post curtido existe no banco de dados.
     * Confere se o usuário que curtiu o post não é o mesmo que publicou o post.
     * Cria um novo Love e salva no banco de dados.
     * De todos os posts do usuário que curtiu o post atual, confere se algum foi 
     * curtido pelo usuário que publicou o post atual. Em caso afirmativo, cria-se um Match.
     * 
     * 
     * Depois, cria um usuário e salva-o no banco de dados.
     *
     * @param request um LoveRequest com id do usuário que curtiu o post e id do post.
     */
    @Transactional
    public NotificationResponse registerLoveOnPost(LoveRequest request, String token) {
        //quem curtiu
        User user = userService.returnUserByToken(token);
        
        Post post = postRepository
                 .findById(request.getIdPost())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post nao encontrado"));
        
        //quem fez o post
        User owner = post.getOwner();

        if(owner.equals(user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nao eh possivel amar  o proprio post");
        }

        Love newLove = new Love(user, post);
        loveRepository.save(newLove);


        // como nao usa a lista de posts do proprio usuario, talvez pode retirar
        List<Post> userPosts = postRepository.findByOwner(user);
        
        for(Post uP : userPosts){
            //pra cada post do user 
            // (que inicialmente curtiu o post de owner)
            //confere se owner curtiu
            boolean likedByOwner = loveRepository.existsByUserAndPost(owner, uP);

            if(likedByOwner){
                Match match = matchService.registrar(user, owner);
                return notificationService.createMatchNotificationUser(match);
            }
        }



        return notificationService.creatWaitingMatchNotification();

    
    }

} 
