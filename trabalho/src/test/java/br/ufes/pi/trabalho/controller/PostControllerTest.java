package br.ufes.pi.trabalho.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.ufes.pi.trabalho.domain.BookGenre;
import br.ufes.pi.trabalho.dto.BookRequest;
import br.ufes.pi.trabalho.dto.CreatePostRequest;
import br.ufes.pi.trabalho.dto.PostResponse;
import br.ufes.pi.trabalho.service.PostService;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Test
    void publishPostSuccess() {
        BookRequest b = new BookRequest("Crepusculo", "Thais", "Capa", "Livro de vampiros feiosinhos que brilham.", 300, 2010, BookGenre.ROMANCE);
        CreatePostRequest request = new CreatePostRequest(
                "Meu post",
                "foto.png",
                b
        );

        // postService é um mock, logo não é chamado de verdade
        ResponseEntity<Void> response = postController.publishPost(request, "token123");

        assertEquals(201, response.getStatusCode().value());
        
        // confirma que o controler chamou o metodo do service
        verify(postService).registerPostById(request, "token123");
    }

    @Test
    void listPostByUserSuccess() {
        PostResponse post = new PostResponse(
                1L,
                "Meu post",
                LocalDateTime.now(),
                "foto.png",
                "Thais"
        );

        when(postService.listPostByUser("token123")).thenReturn(List.of(post));

        ResponseEntity<List<PostResponse>> response = postController.listPostByUser("token123");

        assertEquals(200, response.getStatusCode().value());

        // confirma que o controler chamou o metodo do service
        verify(postService).listPostByUser("token123");
    }
}