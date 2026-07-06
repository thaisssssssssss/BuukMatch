package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.PostRepository;
import br.ufes.pi.trabalho.dto.CreatePostRequest;
import br.ufes.pi.trabalho.dto.PostResponse;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void registerPostById(CreatePostRequest request, String token){
        User owner = userService.returnUserByToken(token);

        Post p_new = new Post(request.getDescription(), request.getPhoto(), owner);
        
        owner.addPost(p_new);
        postRepository.save(p_new);
    }

    public List<PostResponse> listPostByUser(String token){
        User user = userService.returnUserByToken(token);

        List<Post> posts = postRepository.findByOwner(user);
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
