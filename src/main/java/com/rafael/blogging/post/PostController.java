package com.rafael.blogging.post;

import com.rafael.blogging.user.User;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@NoArgsConstructor
public class PostController {

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody User user) {
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok().body(null);
    }
}
