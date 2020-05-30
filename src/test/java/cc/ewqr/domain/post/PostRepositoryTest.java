package cc.ewqr.domain.post;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "test title";
        String content = "test content";
        String author = "test author";

        postRepository.save(new Post.Builder()
                .title(title)
                .content(content)
                .author(author)
                .build()
        );
        List<Post> postList = postRepository.findAll();

        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntity_등록() throws Exception {
        LocalDateTime now = LocalDateTime.of(2020, 5, 29, 0, 0, 0);
        postRepository.save(new Post.Builder().
                title("title").
                content("content").
                build());

        List<Post> postList = postRepository.findAll();
        Post post = postList.get(0);
        System.out.println(">>>>>>>>>>>> createDate=" + post.getCreateDate() + ", modifiedDate=" + post.getModifiedDate());
        assertThat(post.getCreateDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }

}
