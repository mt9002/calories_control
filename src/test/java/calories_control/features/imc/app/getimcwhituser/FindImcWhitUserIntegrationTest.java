package calories_control.features.imc.app.getimcwhituser;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import calories_control.features.auth.infra.Role;
import calories_control.features.auth.infra.UserModel;
import calories_control.features.auth.infra.security.util.SecurityContextUtil;
import calories_control.features.imc.infra.ImcJPA;
import calories_control.features.imc.infra.ImcModel;
import calories_control.features.auth.infra.UserJpa;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FormControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserJpa userRepository;

    @Autowired
    private ImcJPA imcJPA;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        UserModel userModel = new UserModel("Test User", "testuser@example.com",
                passwordEncoder.encode("password"));
        userModel.setRole(Role.USER);
        userRepository.save(userModel);

        ImcModel imc1 = new ImcModel();
        imc1.setPeso(70.0);
        imc1.setAltura(170.0);
        imc1.setResultado(24.22);
        imc1.setClasificacion("Normal");
        imc1.setUserId(userModel.getId());
        imcJPA.save(imc1);
    }

    @Test
    void testShowRegisterFormIntegration() throws Exception {
        try (MockedStatic<SecurityContextUtil> mockedSecurity = mockStatic(SecurityContextUtil.class)) {

            // Tu controlador usa: SecurityContextUtil.getUser()
            mockedSecurity.when(SecurityContextUtil::getUser)
                    .thenReturn(new UserModel("Test User", "testuser@example.com", "password"));

            mockMvc.perform(get("/imc/formRegister")
                    .with(user("testuser@example.com").password("password").roles("USER")))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"))
                    .andExpect(model().attributeExists("registros"));
        }
    }
}
