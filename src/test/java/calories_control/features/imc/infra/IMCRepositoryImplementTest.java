package calories_control.features.imc.infra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import calories_control.features.imc.domain.IMC;

class IMCRepositoryImplementTest {

    @Mock
    private ImcJPA imcJPA;

    @Mock
    private ImcMapper imcMapper;

    @InjectMocks
    private IMCRepositoryImplement imcRepositoryImplement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_Success() {
        // Arrange
        IMC imc = new IMC(70.0, 170.0);
        ImcModel imcModel = new ImcModel(70.0, 170.0, 24.22, 1L);
        imcModel.setId(1L);

        when(imcMapper.toImcModel(imc)).thenReturn(imcModel);
        when(imcJPA.save(imcModel)).thenReturn(imcModel);
        when(imcMapper.toIMC(imcModel)).thenReturn(imc);

        // Act
        Optional<IMC> result = imcRepositoryImplement.save(imc);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(imc, result.get());
        verify(imcMapper, times(1)).toImcModel(imc);
        verify(imcJPA, times(1)).save(imcModel);
        verify(imcMapper, times(1)).toIMC(imcModel);
    }

    @Test
    void testSave_NullImc() {
        // Act
        Optional<IMC> result = imcRepositoryImplement.save(null);

        // Assert
        assertFalse(result.isPresent());
        verify(imcJPA, never()).save(any());
    }

    @Test
    void testSave_SaveFails() {
        // Arrange
        IMC imc = new IMC(70.0, 170.0);
        ImcModel imcModel = new ImcModel(70.0, 170.0, 24.22, 1L);

        when(imcMapper.toImcModel(imc)).thenReturn(imcModel);
        when(imcJPA.save(imcModel)).thenReturn(null);

        // Act
        Optional<IMC> result = imcRepositoryImplement.save(imc);

        // Assert
        assertFalse(result.isPresent());
        verify(imcMapper, times(1)).toImcModel(imc);
        verify(imcJPA, times(1)).save(imcModel);
        verify(imcMapper, never()).toIMC(any());
    }
}