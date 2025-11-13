package calories_control.features.imc.app.create;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import calories_control.features.auth.infra.UserJpa;
import calories_control.features.imc.infra.ImcJPA;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CreateIMCControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserJpa userRepository;

    @Autowired
    private ImcJPA imcJPA;

    @Test
    void testCreateImc() throws Exception {

        mockMvc.perform(post("/imc/create")
                .param("peso", "70")
                .param("altura", "170")
                .with(user("testuser@example.com").password("password").roles("USER")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/imc/formRegister"));
    }
}
