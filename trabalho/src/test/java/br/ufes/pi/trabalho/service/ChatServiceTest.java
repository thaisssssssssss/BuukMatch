package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Message;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.dto.ChatResponse;
import br.ufes.pi.trabalho.dto.MessageResponse;
import br.ufes.pi.trabalho.repository.ChatRepository;
import br.ufes.pi.trabalho.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChatService chatService;

    @Test
    void listUserChatsSuccess() {
        User maria = new User("Maria", "maria@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        maria.setId(1L);

        User joao = new User("João", "joao@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        joao.setId(2L);

        Chat chat = new Chat();
        chat.setId(10L);
        chat.setUser1(maria);
        chat.setUser2(joao);

        Message lastMessage = new Message("Última mensagem", joao, chat);

        when(userService.returnUserByToken("token123")).thenReturn(maria);
        when(chatRepository.findByUser1OrUser2(maria, maria)).thenReturn(List.of(chat));
                                                                            // retorna um Optional vazio caso a mesnagem nao exista
        when(messageRepository.findFirstByChatOrderByIdDesc(chat)).thenReturn(Optional.of(lastMessage));

        List<ChatResponse> response = chatService.listUserChats("token123");

        assertEquals(10L, response.get(0).getChatId());
        assertEquals("João", response.get(0).getName());
        assertEquals("Última mensagem", response.get(0).getLastMessage());
    }

    @Test
    void listMessageSeparatingSentByMeSuccess() {
        User maria = new User("Maria", "maria@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        maria.setId(1L);

        User joao = new User("João", "joao@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        joao.setId(2L);

        Chat chat = new Chat();
        chat.setId(10L);
        chat.setUser1(maria);
        chat.setUser2(joao);

        Message msgMaria = new Message("Oi", maria, chat);
        Message msgJoao = new Message("Olá", joao, chat);

        when(userService.returnUserByToken("token123")).thenReturn(maria);
        when(chatRepository.findById(10L)).thenReturn(Optional.of(chat));
        when(messageRepository.findByChatOrderByDataRecebimentoAsc(chat))
                .thenReturn(List.of(msgMaria, msgJoao));

        List<MessageResponse> response = chatService.listMessage(10L, "token123");

        assertEquals("Oi", response.get(0).getContent());
        assertEquals("Maria", response.get(0).getRemetenteName());
        assertTrue(response.get(0).isSentByMe());

        assertEquals("Olá", response.get(1).getContent());
        assertEquals("João", response.get(1).getRemetenteName());
        assertFalse(response.get(1).isSentByMe());
    }

    @Test
    void sendMessageSuccess() {
        User maria = new User("Maria", "maria@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        maria.setId(1L);

        User joao = new User("João", "joao@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        joao.setId(2L);

        Chat chat = new Chat();
        chat.setId(10L);
        chat.setUser1(maria);
        chat.setUser2(joao);

        when(chatRepository.findById(10L)).thenReturn(Optional.of(chat));
        when(userService.returnUserByToken("token123")).thenReturn(maria);

        chatService.sendMessage(10L, "token123", "Oi, João!");

        // captura qualquer objeto Message que seja passado para algum método
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        //captura a mesnagem salva no messageRepository e pode inspecioná-la
        verify(messageRepository).save(captor.capture());

        // identido ao objeto salvo no banco de dados
        Message savedMessage = captor.getValue();

        assertEquals("Oi, João!", savedMessage.getConteudo());
        assertEquals(maria, savedMessage.getRemetente());
        assertEquals(chat, savedMessage.getChat());
    }

    @Test
    void sendMessageWhenChatNotFoundFail() {
        when(chatRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {chatService.sendMessage(99L, "token123", "Oi");});

        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void sendMessageWhenUserIsNotInChatFail() {
        User maria = new User("Maria", "maria@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        maria.setId(1L);

        User joao = new User("João", "joao@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        joao.setId(2L);

        User ana = new User("Ana", "ana@email.com", "123456", LocalDate.of(2000, 1, 1), null);
        ana.setId(3L);

        Chat chat = new Chat();
        chat.setId(10L);
        chat.setUser1(maria);
        chat.setUser2(joao);

        when(chatRepository.findById(10L)).thenReturn(Optional.of(chat));
        when(userService.returnUserByToken("tokenAna")).thenReturn(ana);

        assertThrows(RuntimeException.class, () -> {chatService.sendMessage(10L, "tokenAna", "Oi");});

        // verifica que o método save() nunca foi chamado, visto que como ana não está no chat, 
        // não é possível que ela mande mensagens 
        verify(messageRepository, never()).save(any(Message.class));
    }
}