package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Book;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.dto.BookRequest;
import br.ufes.pi.trabalho.dto.CreatePostRequest;
import br.ufes.pi.trabalho.dto.PostResponse;
import br.ufes.pi.trabalho.repository.PostRepository;
import br.ufes.pi.trabalho.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

        @Mock
        private PostRepository postRepository;

        @Mock
        private UserRepository userRepository;

        @Mock
        private UserService userService;

        @InjectMocks
        private PostService postService;

    @Test
void registerPostInvalidToken() {

    BookRequest b = new BookRequest("Crepusculo", "Thais", "Intrinseca",300, 2010);
    CreatePostRequest request = new CreatePostRequest(
            "Livro",
            b
    );

    MockMultipartFile mockFile = new MockMultipartFile(
            "file", 
            "foto.png", 
            "image/png", 
            new byte[0] 
    );

    // configura o mock do userService para lançar a exceção
    when(userService.returnUserByToken("tokenInvalido"))
            .thenThrow(new ResponseStatusException(org.springframework.http.HttpStatus.UNAUTHORIZED));

   
    assertThrows(ResponseStatusException.class, () -> {
        postService.registerPostById(request, mockFile, "tokenInvalido");
    });

    // garante que o repositório nunca foi chamado, já que o token era inválido
    verify(postRepository, never()).save(any(Post.class));
}

    @Test
    void listPostsSuccess() {
        User maria = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        Book b = new Book("Crepusculo", "Thais", "Intrinseca",300, 2010);
        Post p1 = new Post("Primeiro post", "foto1.png".getBytes(), maria, b);
        Post p2 = new Post("Segundo post", "foto2.png".getBytes(), maria, b);

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);

        when(postRepository.findByOwner(maria)).thenReturn(List.of(p1, p2));

        List<PostResponse> response = postService.listPostByUser("tokenMaria");

        assertEquals(2, response.size());

        assertEquals("Primeiro post", response.get(0).getLegend());
        assertArrayEquals("foto1.png".getBytes(), response.get(0).getPhoto());
        
        assertEquals("Segundo post", response.get(1).getLegend());
        assertArrayEquals("foto2.png".getBytes(), response.get(1).getPhoto());
    }

    @Test
    void listPostsEmptyList() {
        User maria = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);

        when(postRepository.findByOwner(maria)).thenReturn(List.of());

        List<PostResponse> response = postService.listPostByUser("tokenMaria");

        assertTrue(response.isEmpty());
    }

}