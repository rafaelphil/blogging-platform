package com.rafael.blogging.post;

import com.rafael.blogging.post.records.CreatePostRequest;
import com.rafael.blogging.post.records.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/posts")
    public List<PostDto> getPosts() {
        return postService.getAllPosts().stream().map(postMapper::toPostDto).toList();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        Post post =  postService.getPost(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postMapper.toPostDto(post));
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequest createPostRequest) {
        try{
            Post post = postService.createPost(createPostRequest);
            return ResponseEntity.ok(postMapper.toPostDto(post));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        try{
            postService.deletePost(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
