package calories_control.features.imc.app.create;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import calories_control.features.auth.infra.security.util.SecurityContextUtil;
import calories_control.features.imc.domain.IMC;
import calories_control.features.imc.domain.IMCRepository;
import calories_control.features.imc.infra.ImcJPA;
import calories_control.features.shared.Result;
import calories_control.features.shared.State;

class CreateIMCUseCaseTest {

    @Mock
    private ImcJPA imcJPA;

    @Mock
    private IMCRepository imcRepository;

    @InjectMocks
    private CreateIMCUseCase createIMCUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd_Success() {
        // Arrange
        double peso = 70.0;
        double altura = 170.0;
        IMC imc = new IMC(peso, altura);
        imc.setResultado(24.22);
        imc.setClasificacion("Normal");

        try (MockedStatic<SecurityContextUtil> mockedSecurity = mockStatic(SecurityContextUtil.class)) {
            mockedSecurity.when(SecurityContextUtil::getUserId).thenReturn(1L);

            when(imcRepository.save(any(IMC.class))).thenReturn(Optional.of(imc));

            // Act
            Result<IMC> result = createIMCUseCase.add(peso, altura);

            // Assert
            assertEquals(State.SUCCESS, result.getState());
            assertEquals("IMC creado exitosamente", result.getMessage());
            verify(imcRepository, times(1)).save(any(IMC.class));
        }
    }

    @Test
    void testAdd_Failure() {
        // Arrange
        double peso = 70.0;
        double altura = 170.0;
        IMC imc = new IMC(peso, altura);
        imc.setResultado(24.22);
        imc.setClasificacion("Normal");

        try (MockedStatic<SecurityContextUtil> mockedSecurity = mockStatic(SecurityContextUtil.class)) {
            mockedSecurity.when(SecurityContextUtil::getUserId).thenReturn(1L);

            when(imcRepository.save(any(IMC.class))).thenReturn(Optional.empty());

            // Act
            Result<IMC> result = createIMCUseCase.add(peso, altura);

            // Assert
            assertEquals(State.NOT_FOUND, result.getState());
            assertEquals("IMC no Creado", result.getMessage());
            verify(imcRepository, times(1)).save(any(IMC.class));
        }
    }
}