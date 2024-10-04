package org.big18.finale.controller;

import jakarta.servlet.http.HttpSession;
import org.big18.finale.entity.Post;
import org.big18.finale.entity.stocks.Allcode;
import org.big18.finale.service.PostService;
import org.big18.finale.service.UserNameProvider;
import org.big18.finale.service.market.AllcodeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserNameProvider userNameProvider;
    private final AllcodeService allcodeService;

    public PostController(PostService postService, UserNameProvider userNameProvider, AllcodeService allcodeService) {
        this.postService = postService;
        this.userNameProvider = userNameProvider;
        this.allcodeService = allcodeService;
    }

    @GetMapping("/bbs")
    public String listPosts(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model,
                            HttpSession session) {
        Page<Post> postPage = postService.getAllPosts(page - 1, size);
        List<Post> posts = postPage.getContent();

        // Create a map of stock codes to stock names
        Map<String, String> stockCodeToName = allcodeService.getAllStocks().stream()
                .collect(Collectors.toMap(Allcode::getCode, Allcode::getName));

        // Update posts with stock names
        posts.forEach(post -> {
            post.getFormattedDate();
            String stockName = stockCodeToName.get(post.getStock());
            post.setStockName(stockName != null ? stockName : post.getStock());
        });

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
        return "post/bbs";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model, HttpSession session) {
        Optional<Post> postOptional = postService.getPostById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.incrementViewCount();

            // Get stock name
            allcodeService.getAllStocks().stream()
                    .filter(stock -> stock.getCode().equals(post.getStock()))
                    .findFirst()
                    .ifPresent(stock -> post.setStockName(stock.getName()));

            postService.updatePost(post);
            model.addAttribute("post", post);
            userNameProvider.setUserAttributes(session, model);
            return "post/bbsdetail";
        } else {
            return "redirect:/posts/bbs";
        }
    }

    @GetMapping("/new")
    public String showWriteForm(Model model, HttpSession session) {
        if (!userNameProvider.canWritePost(session)) {
            return "redirect:/login"; // 또는 적절한 에러 페이지로 리다이렉트
        }

        Post newPost = new Post();
        String username = userNameProvider.getUsername(session);
        newPost.setWriter(username);

        model.addAttribute("post", newPost);
        model.addAttribute("formTitle", "글쓰기");
        List<Allcode> allStocks = allcodeService.getAllStocks();
        model.addAttribute("allStocks", allStocks);
        userNameProvider.setUserAttributes(session, model);
        return "post/write";
    }

    @PostMapping("/new")
    public String createPost(@ModelAttribute Post post, HttpSession session, Model model) {
        if (!userNameProvider.canWritePost(session)) {
            return "redirect:/login";
        }

        String username = userNameProvider.getUsername(session);
        post.setWriter(username);

        postService.savePost(post);
        userNameProvider.setUserAttributes(session, model);
        return "redirect:/posts/bbs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Optional<Post> postOptional = postService.getPostById(id);
        Post existingPost = postOptional.orElse(new Post());

        List<Allcode> allStocks = allcodeService.getAllStocks();
        if (existingPost.getStock() != null) {
            allStocks.forEach(stock -> {
                if (stock.getCode().equals(existingPost.getStock())) {
                    stock.setSelected(true);
                }
            });
        }

        model.addAttribute("post", existingPost);
        model.addAttribute("allStocks", allStocks);
        model.addAttribute("formTitle", "수정하기");
        userNameProvider.setUserAttributes(session, model);
        return "post/write";
    }

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