package calories_control.features.auth.infra;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import calories_control.features.imc.infra.ImcJPA;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CreateRegisterUserIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterUser() throws Exception {

        mockMvc.perform(post("/auth/register")
                .param("name", "Ana")
                .param("email", "ana@example.com")
                .param("password", "1234")
                .param("confirmPassword", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/register?mensaje=User+registered+successfully"));
    }
}
