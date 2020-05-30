package cc.ewqr.service.post;

import cc.ewqr.domain.post.Post;
import cc.ewqr.domain.post.PostRepository;
import cc.ewqr.dto.post.PostListResponseDto;
import cc.ewqr.dto.post.PostResponseDto;
import cc.ewqr.dto.post.PostSaveRequestDto;
import cc.ewqr.dto.post.PostUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    final private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다." +
                "id=" + id));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다." +
                "id=" + id));
        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다." +
                "id=" + id));
        postRepository.delete(entity);
    }
}
