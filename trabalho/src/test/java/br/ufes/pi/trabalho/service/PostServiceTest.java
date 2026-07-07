package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Book;
import br.ufes.pi.trabalho.domain.BookGenre;
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
    void registerPostSuccess() {

        User maria = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000,1,1),
                null
        );

        BookRequest b = new BookRequest("Crepusculo", "Thais", "Capa", "Livro de vampiros feiosinhos que brilham.", 300, 2010, BookGenre.ROMANCE);
        CreatePostRequest request = new CreatePostRequest(
                "Livro muito bom!",
                "foto.png",
                b
        );

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);

        postService.registerPostById(request, "tokenMaria");

        ArgumentCaptor<Post> captor = ArgumentCaptor.forClass(Post.class);

        verify(postRepository).save(captor.capture());

        Post savedPost = captor.getValue();

        assertEquals("Livro muito bom!", savedPost.getLegend());
        assertEquals("foto.png", savedPost.getPhoto());
        assertEquals(maria, savedPost.getOwner());

        assertEquals(1, maria.getPosts().size());
    }

    @Test
    void registerPostInvalidToken() {

        BookRequest b = new BookRequest("Crepusculo", "Thais", "Capa", "Livro de vampiros feiosinhos que brilham.", 300, 2010, BookGenre.ROMANCE);
        CreatePostRequest request = new CreatePostRequest(
                "Livro",
                "foto.png",
                b
        );

        when(userService.returnUserByToken("tokenInvalido"))
                .thenThrow(new ResponseStatusException(org.springframework.http.HttpStatus.UNAUTHORIZED));

        assertThrows(ResponseStatusException.class, () -> {postService.registerPostById(request, "tokenInvalido");});

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

        Book b = new Book("Crepusculo", "Thais", "Capa", "Livro de vampiros feiosinhos que brilham.", 300, 2010, BookGenre.ROMANCE);
        Post p1 = new Post("Primeiro post", "foto1.png", maria, b);
        Post p2 = new Post("Segundo post", "foto2.png", maria, b);

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);

        when(postRepository.findByOwner(maria)).thenReturn(List.of(p1, p2));

        List<PostResponse> response = postService.listPostByUser("tokenMaria");

        assertEquals(2, response.size());

        assertEquals("Primeiro post", response.get(0).getDescription());
        assertEquals("foto1.png", response.get(0).getPhoto());

        assertEquals("Segundo post", response.get(1).getDescription());
        assertEquals("foto2.png", response.get(1).getPhoto());
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