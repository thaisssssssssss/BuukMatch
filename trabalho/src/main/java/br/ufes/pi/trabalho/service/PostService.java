package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Book;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.domain.ViewPost;
import br.ufes.pi.trabalho.repository.PostRepository;
import br.ufes.pi.trabalho.repository.ViewPostRepository;
import br.ufes.pi.trabalho.dto.BookRequest;
import br.ufes.pi.trabalho.dto.CreatePostRequest;
import br.ufes.pi.trabalho.dto.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void registerPostById(CreatePostRequest request, MultipartFile file, String token){

            User owner = userService.returnUserByToken(token);
            byte[] bytesDaFoto;
            try {
                bytesDaFoto = file.getBytes(); // Proteja apenas a linha que realmente pode dar IOException
            } catch (IOException e) {
                throw new RuntimeException("Falha ao ler os bytes da imagem enviada", e);
            }
            Book b_new = new Book(request.getBook().getTitle(), request.getBook().getAuthor(), request.getBook().getPublisher() ,request.getBook().getNumberOfPages(), request.getBook().getPublicationYear());

            Post p_new = new Post(request.getLegend(), bytesDaFoto, owner, b_new);
            
            System.out.println(p_new.getBook());

            owner.addPost(p_new);
            postRepository.save(p_new);
            Post teste = postRepository.findById(p_new.getId()).orElseThrow();
            System.out.println("Book após salvar: " + teste.getBook());
    }

    public List<PostResponse> listPostByUser(String token){
        User user = userService.returnUserByToken(token);

        List<Post> posts = postRepository.findByOwner(user);
        List<PostResponse> responses = new ArrayList<PostResponse>();

        for(Post p : posts){
            System.out.println("Post " + p.getId());
            System.out.println("Book no loop = " + p.getBook());
            BookRequest br = p.getBook().createBookRequest();
            System.out.println("BookRequest = " + br);
            responses.add(
                new PostResponse(
                    p.getId(),
                    p.getLegend(),
                    p.getPublicationDate(),
                    p.getPhoto(),
                    user.getName(),
                   br
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
            System.out.println("Post " + p.getId());
            System.out.println("Book no loop = " + p.getBook());
            BookRequest br = p.getBook().createBookRequest();
            System.out.println("BookRequest = " + br);
            response.add(new PostResponse(p.getId(), p.getLegend(), p.getPublicationDate(), p.getPhoto(), p.getOwner().getName(), br));
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
