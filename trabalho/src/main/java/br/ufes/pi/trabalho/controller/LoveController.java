package br.ufes.pi.trabalho.controller;

import org.springframework.web.bind.annotation.*;

import br.ufes.pi.trabalho.dto.LoveRequest;
import br.ufes.pi.trabalho.service.LoveService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/loves")
public class LoveController {
    private final LoveService loveService;
    
    public LoveController(LoveService loveService){
        this.loveService = loveService;
    }

    /**
     * Registra um Love (curtida de match)
     *<br><br>
     
     * URL no postman: POST http://localhost:8080/loves
     *
     * No campo headers coloca Authorization e o token recebido.
     * JSON:
     * {
     *      "idPost": 2
     * }
     * @param request LoveRequest contendo id do post e do usuário que o curtiu
     */
    @PostMapping //um User realizar um Love em uma Post
    public ResponseEntity<Void> registerLoveOnPost(@RequestBody LoveRequest request, @RequestHeader("Authorization") String token){
        loveService.registerLoveOnPost(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}