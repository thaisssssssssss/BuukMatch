package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Book;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.domain.ViewPost;
import br.ufes.pi.trabalho.repository.PostRepository;
import br.ufes.pi.trabalho.repository.ViewPostRepository;
import br.ufes.pi.trabalho.dto.CreatePostRequest;
import br.ufes.pi.trabalho.dto.PostResponse;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final ViewPostRepository viewPostRepository;

    public PostService(PostRepository postRepository, UserService userService, ViewPostRepository viewPostRepository){
        this.postRepository = postRepository;
        this.userService = userService;
        this.viewPostRepository = viewPostRepository;
    }

    public void registerPostById(CreatePostRequest request, String token){
        User owner = userService.returnUserByToken(token);

        Book b_new = new Book(request.getBook().getTitle(), request.getBook().getAutor(), request.getBook().getCover(), request.getBook().getDescription(), request.getBook().getNumberOfPages(), request.getBook().getPublicationYear(), request.getBook().getGenre());
        Post p_new = new Post(request.getLegend(), request.getPhoto(), owner, b_new);
        
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
                    p.getId(),
                    p.getLegend(),
                    p.getPublicationDate(),
                    p.getPhoto(),
                    user.getName()
                )
            );
        }
        return responses;
    }

    public List<PostResponse> listUnseenPostsByUser(String token){
        User user = userService.returnUserByToken(token);

        List<Post> posts = postRepository.findPostsNotViewedByUser(user.getId());

        List<PostResponse> response = new ArrayList<>();

        for(Post p : posts){
            response.add(new PostResponse(p.getId(), p.getLegend(), p.getPublicationDate(), p.getPhoto(), p.getOwner().getName()));
        }

        return response;
    }

    public void viewPost(Long postId, String token){
        User user = userService.returnUserByToken(token);

        Post post = postRepository.findById(postId)
                                  .orElseThrow(() -> new RuntimeException("Post nao encontrado"));
        
        boolean viewed = viewPostRepository.existsByUserIdAndPostId(user.getId(), postId);

        if(viewed) return;

        ViewPost viewPost = new ViewPost(user, post);
        viewPostRepository.save(viewPost);
    }
}
