package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.PostRepository;
import br.ufes.pi.trabalho.repository.UserRepository;
import br.ufes.pi.trabalho.dto.CreatePostRequest;
import br.ufes.pi.trabalho.dto.PostResponse;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void registerPostById(CreatePostRequest request){
        User u = userRepository.findById(request.getIdUser()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User do not exist")
        ); // usuario precisa existir

        Post p_new = new Post(request.getDescription(), request.getPhoto());
        u.addPost(p_new);

        postRepository.save(p_new);
    }

    public List<PostResponse> listPostByUser(Long id){
        User u = userRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User do not exist")
        ); // usuario precisa existir
        ArrayList<Post> posts = u.getPosts();
        List<PostResponse> responses = new ArrayList<PostResponse>();

        for(Post p : posts){
            responses.add(
                new PostResponse(
                    p.getDescription(),
                    p.getPublicationDate(),
                    p.getPhoto()
                )
            );
        }
        return responses;
    }
}
