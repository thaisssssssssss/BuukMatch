package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Match;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.ChatRepository;
import br.ufes.pi.trabalho.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private ChatRepository chatRepository;

    @Mock NotificationService notificationService;

    @InjectMocks
    private MatchService matchService;

    @Test
    void registerMatchSuccess() {
        User maria = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );
        maria.setId(1L);

        User joao = new User(
                "João",
                "joao@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );
        joao.setId(2L);

        // quando chama save(match), devolve o match recebido ao invés de null
        // padrão do mock é null, invocation permite devolver o objeto recebido
        when(matchRepository.save(any(Match.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Match result = matchService.registrar(maria, joao);

        assertNotNull(result);
        assertEquals(maria, result.getUser1());
        assertEquals(joao, result.getUser2());

        verify(matchRepository).save(any(Match.class));
        verify(chatRepository).save(any(Chat.class));
    }
    
}