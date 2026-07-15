package br.ufes.pi.trabalho.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.ufes.pi.trabalho.dto.CreateUserRequest;
import br.ufes.pi.trabalho.dto.LoginRequest;
import br.ufes.pi.trabalho.dto.LoginResponse;
import br.ufes.pi.trabalho.dto.UserResponse;
import br.ufes.pi.trabalho.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void registerUserSuccess() {
        CreateUserRequest request = new CreateUserRequest();

        ResponseEntity<Void> response = userController.registerUser(request);

        assertEquals(201, response.getStatusCode().value());
        
        // confirma que o controler chamou o metodo do service
        verify(userService).registerUser(request);
    }

    @Test
    void findUserByIdSuccess() {
        UserResponse user = new UserResponse("Maria", "maria@email.com");

        when(userService.findUserResponseById(1L)).thenReturn(user);

        ResponseEntity<UserResponse> response = userController.findUserById(1L);

        assertEquals(200, response.getStatusCode().value());

        // confirma que o controler chamou o metodo do service
        verify(userService).findUserResponseById(1L);
    }

    @Test
    void loginUserSuccess() {
        LoginRequest request = new LoginRequest();
        request.setEmail("maria@email.com");
        request.setPassword("123456");

        UserResponse user = new UserResponse("Maria", "maria@email.com");

        LoginResponse loginResponse = new LoginResponse("token123", user);

        when(userService.login(request)).thenReturn(loginResponse);

        ResponseEntity<LoginResponse> response = userController.loginUser(request);

        assertEquals(200, response.getStatusCode().value());

        // confirma que o controler chamou o metodo do service
        verify(userService).login(request);
    }

    @Test
    void listUsersSuccess() {
        UserResponse maria = new UserResponse("Maria", "maria@email.com");

        when(userService.listUsers()).thenReturn(List.of(maria));

        ResponseEntity<List<UserResponse>> response = userController.listUsers();

        assertEquals(200, response.getStatusCode().value());

        // confirma que o controler chamou o metodo do service
        verify(userService).listUsers();
    }
}