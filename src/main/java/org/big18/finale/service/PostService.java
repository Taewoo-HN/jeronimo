package org.big18.finale.service;

import jakarta.persistence.EntityNotFoundException;
import org.big18.finale.entity.Post;
import org.big18.finale.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(Post post) {
        post.setDate(LocalDateTime.now());
        post.setCount(0);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post updatePost(Post updatedPost) {
        return postRepository.findById(updatedPost.getId())
                .map(existingPost -> {
                    existingPost.setStock(updatedPost.getStock());
                    existingPost.setTitle(updatedPost.getTitle());
                    existingPost.setWriter(updatedPost.getWriter());
                    existingPost.setContent(updatedPost.getContent());
                    // date와 count는 업데이트하지 않음
                    return postRepository.save(existingPost);
                })
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + updatedPost.getId()));
    }


    @Transactional(readOnly = true)
    public Page<Post> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        Page<Post> postPage = postRepository.findAll(pageable);

        // 전체 게시물 수 조회
        long totalElements = postRepository.count();

        // 각 페이지의 게시물에 표시 번호 설정
        List<Post> posts = postPage.getContent();
        for (int i = 0; i < posts.size(); i++) {
            int displayNumber = (int) (totalElements - (page * size) - i);
            posts.get(i).setDisplayNumber(displayNumber);
        }

        return postPage;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post incrementViewCount(Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setCount(post.getCount() + 1);
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }
}

