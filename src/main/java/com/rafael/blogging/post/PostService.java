package com.rafael.blogging.post;

import com.rafael.blogging.post.records.CreatePostRequest;
import com.rafael.blogging.user.User;
import com.rafael.blogging.user.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPost(@PathVariable Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(CreatePostRequest createPostRequest) {
        User author = userRepository.findById(createPostRequest.authorId()).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        if(postRepository.existsByAuthorIdAndTitle(createPostRequest.authorId(), createPostRequest.title())) {
            throw new EntityExistsException("Post already exists");
        }

        Post post = new Post();
        post.setTitle(createPostRequest.title());
        post.setContent(createPostRequest.content());
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthor(author);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        if(!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Post not found");
        }

        postRepository.deleteById(id);
    }
}
