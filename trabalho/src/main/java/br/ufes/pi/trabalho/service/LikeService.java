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
import br.ufes.pi.trabalho.repository.UserRepository;



@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    
    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository){
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void registerLikeOnPost(LikeRequest request) {
        //quem curtiu
        User user = userRepository
                 .findById(request.getIdUser())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao encontrado"));
        
        Post post = postRepository
                 .findById(request.getIdPost())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post nao encontrado"));
        
        //quem fez o post
        User owner = post.getOwner();

        if(owner.equals(user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nao eh possivel curtir o proprio post");
        }

        Like newLike = new Like(user, post);
        likeRepository.save(newLike);


    }
}
