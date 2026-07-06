package br.ufes.pi.trabalho.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ufes.pi.trabalho.domain.Like;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.dto.LikeRequest;
import br.ufes.pi.trabalho.repository.LikeRepository;
import br.ufes.pi.trabalho.repository.PostRepository;



@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    private final UserService userService;
    
    public LikeService(LikeRepository likeRepository, PostRepository postRepository, UserService userService){
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void registerLikeOnPost(LikeRequest request, String token) {
        //quem curtiu
        User user = userService.returnUserByToken(token);
        
        Post post = postRepository
                 .findById(request.getIdPost())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post nao encontrado"));
        
        //quem fez o post
        User owner = post.getOwner();
        if(owner.equals(user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nao eh possivel curtir o proprio post");
        }

        Like newLike = new Like(user, post);
        post.addLike(newLike);
        likeRepository.save(newLike);


    }
}
