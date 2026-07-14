package br.ufes.pi.trabalho.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.http.HttpStatus;

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
        BookRequest b = new BookRequest("Crepusculo", "Thais", 300, 2010, BookGenre.ROMANCE);
        CreatePostRequest request = new CreatePostRequest(
                "Meu post",
                b
        );

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",                         
                "foto.png",                     
                "image/png",                    
                "bytes-falsos-da-imagem".getBytes() // Conteúdo em bytes da imagem para o H2 ler
        );

        ResponseEntity<Void> response = postController.publishPost(request, mockFile, "token123");

        // Verifica se retornou 201 Created
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        verify(postService).registerPostById(request, mockFile, "token123");
    }

   @Test
    void listPostByUserSuccess() {
        PostResponse post = new PostResponse(
                "Meu post",
                LocalDateTime.now(),
                "bytes-da-foto".getBytes() // Mudança aqui: passando byte[] em vez de String
        );

        when(postService.listPostByUser("token123")).thenReturn(List.of(post));

        ResponseEntity<List<PostResponse>> response = postController.listPostByUser("token123");

        assertEquals(200, response.getStatusCode().value());
        verify(postService).listPostByUser("token123");
    }
}