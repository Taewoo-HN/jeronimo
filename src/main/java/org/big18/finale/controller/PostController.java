package org.big18.finale.controller;


import jakarta.servlet.http.HttpSession;
import org.big18.finale.entity.Post;
import org.big18.finale.service.PostService;
import org.big18.finale.service.UserNameProvider;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserNameProvider userNameProvider;


    public PostController(PostService postService, UserNameProvider userNameProvider) {
        this.postService = postService;
        this.userNameProvider = userNameProvider;
    }

    @GetMapping("/bbs")
    public String listPosts(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model,
                            HttpSession session) {
        Page<Post> postPage = postService.getAllPosts(page - 1, size);
        List<Post> posts = postPage.getContent();

        posts.forEach(Post::getFormattedDate);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postPage.getTotalPages());

        int totalPages = postPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("showPrevious", postPage.hasPrevious());
        model.addAttribute("showNext", postPage.hasNext());
        model.addAttribute("previousPageNumber", page > 1 ? page - 1 : 1);
        model.addAttribute("nextPageNumber", page < totalPages ? page + 1 : totalPages);

        userNameProvider.setUserAttributes(session, model);
        return "bbs";
    }

    @GetMapping("/new")
    public String showWriteForm(Model model, HttpSession session) {
        model.addAttribute("post", new Post());
        model.addAttribute("formTitle", "글쓰기");
        userNameProvider.setUserAttributes(session, model);
        return "write";
    }

    // 글 작성 핸들러
    @PostMapping("/new")
    public String createPost(@ModelAttribute Post post, HttpSession session, Model model) {
        // 글 저장 로직
        postService.savePost(post);
        userNameProvider.setUserAttributes(session, model);
        return "redirect:/posts/bbs"; // 목록 페이지로 리다이렉트
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model, HttpSession session) {
        Optional<Post> postOptional = postService.getPostById(id);
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            userNameProvider.setUserAttributes(session, model);
            return "bbsdetail";
        } else {
            // 게시물이 없을 경우의 처리
            return "redirect:/posts/bbs";  // 또는 에러 페이지로 리다이렉트
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Optional<Post> post = postService.getPostById(id);
        model.addAttribute("post", post.orElse(new Post()));
        model.addAttribute("formTitle", "수정하기");
        userNameProvider.setUserAttributes(session, model);
        return "write";
    }

    // 글 수정 핸들러
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, HttpSession session, Model model) {
        Optional<Post> existingPost = postService.getPostById(id);
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setStock(post.getStock());
            updatedPost.setTitle(post.getTitle());
            updatedPost.setWriter(post.getWriter());
            updatedPost.setContent(post.getContent());

            postService.updatePost(updatedPost);
        }
        userNameProvider.setUserAttributes(session, model);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, HttpSession session, Model model) {
        postService.deletePost(id);
        userNameProvider.setUserAttributes(session, model);
        return "redirect:/posts/bbs";
    }
}