package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Love;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.domain.Book;
import br.ufes.pi.trabalho.dto.LoveRequest;
import br.ufes.pi.trabalho.repository.LoveRepository;
import br.ufes.pi.trabalho.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoveServiceTest {

    @Mock
    private LoveRepository loveRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private MatchService matchService;

    @Mock
    private UserService userService;

    @Mock NotificationService notificationService;

    @InjectMocks
    private LoveService loveService;

    @Test
    void registerLoveOnPostWithoutMatchSuccess() {
        User maria = new User("Maria", "maria@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        User joao = new User("João", "joao@email.com", "123456", LocalDate.of(2000, 1, 1), null);

        Book b = new Book("Crepusculo", "Thais","Intrinseca" ,300, 2010);
        Post postJoao = new Post("Livro legal", "foto.png".getBytes(), joao, b);

        LoveRequest request = new LoveRequest(10L);

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);
        when(postRepository.findById(10L)).thenReturn(Optional.of(postJoao));
        when(postRepository.findByOwner(maria)).thenReturn(List.of());

        loveService.registerLoveOnPost(request, "tokenMaria");

        ArgumentCaptor<Love> captor = ArgumentCaptor.forClass(Love.class);
        verify(loveRepository).save(captor.capture());

        Love savedLove = captor.getValue();

        assertEquals(maria, savedLove.getUser());
        assertEquals(postJoao, savedLove.getPost());

        // verifica que método registrar match não foi chamado
        verify(matchService, never()).registrar(any(User.class), any(User.class));

        // verifica que mesmo um usuário tendo curtido o post de outro, se não for recíproco
        // o match não ocorre
    }

    @Test
    void registerLoveOnPostWithMatchSuccess() {
        User maria = new User("Maria", "maria@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        User joao = new User("João", "joao@email.com", "123456", LocalDate.of(2000, 1, 1), null);

        Book b = new Book("Crepusculo", "Thais", "Intrinseca",300, 2010);
        Post postJoao = new Post("Post do João", "joao.png".getBytes(), joao, b);
        Post postMaria = new Post("Post da Maria", "maria.png".getBytes(), maria, b);

        LoveRequest request = new LoveRequest(10L);

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);
        when(postRepository.findById(10L)).thenReturn(Optional.of(postJoao));
        when(postRepository.findByOwner(maria)).thenReturn(List.of(postMaria));
        when(loveRepository.existsByUserAndPost(joao, postMaria)).thenReturn(true);

        loveService.registerLoveOnPost(request, "tokenMaria");

        verify(loveRepository).save(any(Love.class));
        verify(matchService).registrar(maria, joao);
    }

    @Test
    void registerLoveOnPostWhenPostNotFoundFail() {
        LoveRequest request = new LoveRequest(99L);

        User maria = new User("Maria", "maria@email.com", "123456", LocalDate.of(2000, 1, 1), null);

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);
        when(postRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {loveService.registerLoveOnPost(request, "tokenMaria");});

        verify(loveRepository, never()).save(any(Love.class));
        verify(matchService, never()).registrar(any(User.class), any(User.class));
    }

    @Test
    void registerLoveOnPostWhenUserLikesOwnPostFail() {
        User maria = new User("Maria", "maria@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        
        Book b = new Book("Crepusculo", "Thais", "Intrinseca",300, 2010);
        Post postMaria = new Post("Meu próprio post", "foto.png".getBytes(), maria, b);

        LoveRequest request = new LoveRequest(10L);

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);
        when(postRepository.findById(10L)).thenReturn(Optional.of(postMaria));

        assertThrows(ResponseStatusException.class, () -> {loveService.registerLoveOnPost(request, "tokenMaria");});

        verify(loveRepository, never()).save(any(Love.class));
        verify(matchService, never()).registrar(any(User.class), any(User.class));
    }
}