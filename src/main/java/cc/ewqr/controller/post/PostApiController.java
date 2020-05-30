package cc.ewqr.controller.post;

import cc.ewqr.dto.post.PostResponseDto;
import cc.ewqr.dto.post.PostSaveRequestDto;
import cc.ewqr.dto.post.PostUpdateRequestDto;
import cc.ewqr.service.post.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostApiController {
    final private PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}
