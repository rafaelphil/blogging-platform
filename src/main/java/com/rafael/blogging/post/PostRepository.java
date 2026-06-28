package com.rafael.blogging.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByAuthorIdAndTitle(Long authorId, String title);
}
