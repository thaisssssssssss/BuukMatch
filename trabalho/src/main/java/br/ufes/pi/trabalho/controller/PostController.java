package br.ufes.pi.trabalho.controller;
import br.ufes.pi.trabalho.service.PostService;
import br.ufes.pi.trabalho.dto.CreatePostRequest;
import br.ufes.pi.trabalho.dto.PostResponse;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> publishPost(@ModelAttribute CreatePostRequest request, @RequestPart("file") MultipartFile file, @RequestHeader("Authorization") String token){
        postService.registerPostById(request,file, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> listPostByUser(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(postService.listPostByUser(token));
    }

    @GetMapping("/nao-vistos")
    public ResponseEntity<List<PostResponse>> listUnseenPostsByUser(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(postService.listUnseenPostsByUser(token));
    }

    @PostMapping("{postId}/visualizar")
    public ResponseEntity<Void> viewPost(@PathVariable Long postId, @RequestHeader("Authorization") String token){
        postService.viewPost(postId, token);
        return ResponseEntity.noContent().build();
    }

}
