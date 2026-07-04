package br.ufes.pi.trabalho.controller;
import br.ufes.pi.trabalho.service.PostService;
import br.ufes.pi.trabalho.dto.CreatePostRequest;
import br.ufes.pi.trabalho.dto.PostResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> publishPost(@RequestBody CreatePostRequest request, @RequestHeader("Authorization") String token){
        postService.registerPostById(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> listPostByUser(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(postService.listPostByUser(token));
    }

}
