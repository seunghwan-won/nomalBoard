package cc.ewqr.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate template;

    @Test
    public void real_profile이_조회된다() throws Exception {
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(env);

        String profile = profileController.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다() throws Exception {
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController profileController = new ProfileController(env);

        String profile = profileController.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void profile은_인증없이_호출된다() throws Exception {
        String expectedProfile = "default";
        ResponseEntity<String> response = template.getForEntity("/profile", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedProfile);
    }
}
