package br.ufes.pi.trabalho.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Message;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.dto.ChatResponse;
import br.ufes.pi.trabalho.dto.MessageResponse;
import br.ufes.pi.trabalho.repository.ChatRepository;
import br.ufes.pi.trabalho.repository.MessageRepository;

@Service 
public class ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserService userService;

    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository, UserService userService){
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    /**
     * Regras de negócio para listar os chats de um usuário
     *<br><br>
     *
     * Confere se o usuário esta logado em sua conta.
     * Encrontra seus chats (pega do banco de dados).
     * Para cada chat ao qual ele é remetente, cria um ChatResponse com o nome do destinatário
     * e a última mesnagem enviada.
     *
     * @param token verificação de login do usuário remetente
     * @return lista de ChatResponse com os nomes dos usuários destinatários e a última mensagem enchaminhada
     */
    public List<ChatResponse> listUserChats(String token){
        User user = userService.returnUserByToken(token);

        List<Chat> chats = chatRepository.findByUser1OrUser2(user, user);
        List<ChatResponse> response = new ArrayList<>();

        for(Chat c : chats){
            ChatResponse cs = new ChatResponse(c.getId());

            if(!c.getUser1().getId().equals(user.getId())){
                cs.setName(c.getUser1().getName());
            }
            else{
                cs.setName(c.getUser2().getName());
            }
            
            String lastMessage = messageRepository
                                 .findFirstByChatOrderByIdDesc(c)
                                 .map(Message::getConteudo)
                                 .orElse("");

            cs.setLastMessage(lastMessage);

            response.add(cs);
        }

        return response;
    }


    public List<MessageResponse> listMessage(Long chatId, String token){
        User user = userService.returnUserByToken(token);

        Chat chat = chatRepository
                    .findById(chatId)
                    .orElseThrow(() -> new RuntimeException("Chat nao encontrado"));
        
        if(!chat.getUser1().equals(user) && !chat.getUser2().equals(user)){
            throw new RuntimeException("Id do chat passado nao corresponde aos chats do usuario logado");
        }

        List<Message> msgs = messageRepository.findByChatOrderByDataRecebimentoAsc(chat);
        List<MessageResponse> response = new ArrayList<>();

        for(Message m : msgs){
            boolean sentByMe = m.getRemetente().getId().equals(user.getId());

            response.add(new MessageResponse(m.getDataRecebimento(), m.getConteudo(), m.getRemetente().getName(), sentByMe));
        }

        return response;
    }


    /**
     * Regras de negócio para envio de mensagens 
     *<br><br>
     *
     * Abre um chat a partir do id passado.
     * Confere se o usuário esta logado em sua conta.
     * Cria nova mensagem e salva no repositório.
     *
     * @param chatId id do chat cuja mensagem será encaminhada para
     * @param token verificação de login do usuário remetente
     * @param conteudo conteúdo da mensagem
     */
    public void sendMessage(Long chatId, String token, String conteudo){
        //tentar tirar isso de passar o id do chat
        Chat chat = chatRepository
                    .findById(chatId)
                    .orElseThrow(() -> new RuntimeException("Chat nao encontrado"));

        User remetente = userService.returnUserByToken(token);

        if(!chat.getUser1().equals(remetente) && !chat.getUser2().equals(remetente)){
            throw new RuntimeException("Usuario nao esta nesse chat");
        }

        Message msg = new Message(conteudo, remetente, chat);

        messageRepository.save(msg);
    }

}
