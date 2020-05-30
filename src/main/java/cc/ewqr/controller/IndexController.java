package cc.ewqr.controller;

import cc.ewqr.config.auth.LoginUser;
import cc.ewqr.config.auth.dto.SessionUser;
import cc.ewqr.dto.post.PostResponseDto;
import cc.ewqr.service.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    private final PostService postService;
    private final HttpSession session;

    public IndexController(PostService postService, HttpSession session) {
        this.postService = postService;
        this.session = session;
    }

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postService.findAllDesc());
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "post-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostResponseDto responseDto = postService.findById(id);
        model.addAttribute("post", responseDto);
        return "post-update";
    }
}
