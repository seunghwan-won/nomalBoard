package cc.ewqr.controller;

import cc.ewqr.config.auth.SecurityConfig;
import cc.ewqr.controller.WelcomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest( controllers = WelcomeController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class WelcomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = "USER")
    @Test
    public void welcome() throws Exception {
        String welcome = "welcome";

        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(content().string(welcome));
    }
}
