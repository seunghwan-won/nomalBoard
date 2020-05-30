package cc.ewqr.controller.post;

import cc.ewqr.domain.post.Post;
import cc.ewqr.domain.post.PostRepository;
import cc.ewqr.dto.post.PostSaveRequestDto;
import cc.ewqr.dto.post.PostUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest ( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private PostRepository postRepository;

    @After
    public void teardown() throws Exception {
        postRepository.deleteAll();
    }

    @Test
    public void 게시물_등록() throws Exception {
        String title = "test title";
        String content = "test content";
        String author = "test author";
        PostSaveRequestDto requestDto = new PostSaveRequestDto.Builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = template.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    @Test
    public void post_수정() throws Exception {
        Post savedPost = postRepository.save(new Post.Builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updatedId = savedPost.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequestDto requestDto = new PostUpdateRequestDto.Builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = template.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
