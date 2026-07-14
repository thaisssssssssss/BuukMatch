package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Book;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.PostRepository;
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

    public PostService(PostRepository postRepository, UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void registerPostById(CreatePostRequest request, MultipartFile file, String token){

            User owner = userService.returnUserByToken(token);
            byte[] bytesDaFoto;
            try {
                bytesDaFoto = file.getBytes(); // Proteja apenas a linha que realmente pode dar IOException
            } catch (IOException e) {
                throw new RuntimeException("Falha ao ler os bytes da imagem enviada", e);
            }
            Book b_new = new Book(request.getBook().getTitle(), request.getBook().getAuthor(), request.getBook().getNumberOfPages(), request.getBook().getPublicationYear(), request.getBook().getGenre());

            Post p_new = new Post(request.getLegend(), bytesDaFoto, owner, b_new);
            
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
                    p.getLegend(),
                    p.getPublicationDate(),
                    p.getPhoto()
                )
            );
        }
        return responses;
    }
}
