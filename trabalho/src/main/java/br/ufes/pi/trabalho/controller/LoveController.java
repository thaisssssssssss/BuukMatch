package br.ufes.pi.trabalho.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import br.ufes.pi.trabalho.dto.LoveRequest;
import br.ufes.pi.trabalho.domain.Love;
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

    @PostMapping //um User realizar um Love em uma Post
    public ResponseEntity<Void> registerLoveOnPost(@RequestBody LoveRequest LoveRequest){

        loveService.registerLoveOnPost(LoveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
