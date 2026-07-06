package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.Token;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.dto.*;
import br.ufes.pi.trabalho.repository.TokenRepository;
import br.ufes.pi.trabalho.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void registerWithNullEmailFail() {
        AddressRequest address = new AddressRequest(
                "Rua A",
                "Centro",
                "Vitória",
                10L
        );

        CreateUserRequest request = new CreateUserRequest(
                "Maria",
                null,
                "123456",
                LocalDate.of(2000, 1, 1),
                address
        );

        // garante que o código lance a exceção do tipo esperado
        assertThrows(IllegalArgumentException.class, () -> {userService.registerUser(request);});

        // verifica que o método save() nunca foi chamado, já que não é possível cadastrar
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerWithBlankEmailFail() {
        AddressRequest address = new AddressRequest(
                "Rua A",
                "Centro",
                "Vitória",
                10L
        );

        CreateUserRequest request = new CreateUserRequest(
                "Maria",
                "   ",
                "123456",
                LocalDate.of(2000, 1, 1),
                address
        );

        // garante que o código lance a exceção do tipo esperado
        assertThrows(IllegalArgumentException.class, () -> {userService.registerUser(request);});

        // verifica que o método save() nunca foi chamado, já que não é possível cadastrar
        verify(userRepository, never()).save(any(User.class));
    }
    

    @Test
    void registerWithSameEmailFail() {
        AddressRequest address = new AddressRequest(
                "Rua A",
                "Centro",
                "Vitória",
                10L
        );

        CreateUserRequest request = new CreateUserRequest(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                address
        );

        // retorna true quando o service pergunta se já existe esse e-mail
        when(userRepository.existsByEmail("maria@email.com")).thenReturn(true);

        // garante que o código lance a exceção do tipo esperado
        assertThrows(ResponseStatusException.class, () -> {userService.registerUser(request);});

        // verifica que o método save() nunca foi chamado, já que não é possível cadastrar
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void loginWithWrongEmailFail() {
        LoginRequest request = new LoginRequest();
        request.setEmail("naoexiste@email.com");
        request.setPassword("123456");

        when(userRepository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        // garante que o código lance a exceção do tipo esperado
        assertThrows(ResponseStatusException.class, () -> { userService.login(request);});

        // verifica que o método save() de tokenRepository nunca foi chamado, 
        // já que não é possível cadastrar um novo token, visto que o email inválido não
        // foi encontrado no banco de dados
        verify(tokenRepository, never()).save(any(Token.class));
    }

    @Test
    void loginWithWrongPasswordFail() {

        User user = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        LoginRequest request = new LoginRequest();
        request.setEmail("maria@email.com");
        request.setPassword("senhaErrada");

        when(userRepository.findByEmail("maria@email.com"))
                .thenReturn(Optional.of(user));

        // garante que o código lance a exceção do tipo esperado
        assertThrows(ResponseStatusException.class, () -> {userService.login(request);});

        // verifica que o método save() de tokenRepository nunca foi chamado, 
        // já que não é possível cadastrar um novo token, visto que o email inválido não
        // foi encontrado no banco de dados
        verify(tokenRepository, never()).save(any(Token.class));
    }

    @Test
    void correctLoginSuccess() {
        User user = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        LoginRequest login = new LoginRequest();
        login.setEmail("maria@email.com");
        login.setPassword("123456");

        when(userRepository.findByEmail("maria@email.com"))
                .thenReturn(Optional.of(user));

        LoginResponse response = userService.login(login);

        assertNotNull(response.getToken());
        assertEquals("Maria", response.getUser().getName());
        assertEquals("maria@email.com", response.getUser().getEmail());

        // verifica que o método save() do tokenRepository foi chamado uma vez
        verify(tokenRepository).save(any(Token.class));
    }

    @Test
    void returnUserByTokenSucess() {
        User user = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        Token token = new Token("token123", user);

        when(tokenRepository.findById("token123"))
                .thenReturn(Optional.of(token));

        User result = userService.returnUserByToken("token123");

        assertEquals("Maria", result.getName());
        assertEquals("maria@email.com", result.getEmail());
    }

    @Test
    void returnUserByInvalidTokenFail() {
        when(tokenRepository.findById("tokenInvalido"))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {userService.returnUserByToken("tokenInvalido");});
    }

    @Test
    void listUsersAsResponseSuccess() {
        User maria = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        User joao = new User(
                "João",
                "joao@email.com",
                "abcdef",
                LocalDate.of(1999, 5, 10),
                null
        );

        // mokcito definindo o comportamento do mock,
        // quando userRepository.findAll() for chamado, em vez de acessar um banco de verdade, 
        // devolva esta lista
        when(userRepository.findAll()).thenReturn(List.of(maria, joao));

        List<UserResponse> users = userService.listUsers();

        assertEquals(2, users.size());
        assertEquals("Maria", users.get(0).getName());
        assertEquals("João", users.get(1).getName());
    }
}