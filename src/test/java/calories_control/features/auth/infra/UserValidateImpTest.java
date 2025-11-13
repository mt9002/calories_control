package calories_control.features.auth.infra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserValidateImpTest {

    @Mock
    private UserJpa userJpa;

    @InjectMocks
    private UserValidateImp userValidateImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsGetById_Exists() {
        // Arrange
        Long userId = 1L;
        when(userJpa.existsById(userId)).thenReturn(true);

        // Act
        boolean result = userValidateImp.isGetById(userId);

        // Assert
        assertTrue(result);
        verify(userJpa, times(1)).existsById(userId);
    }

    @Test
    void testIsGetById_NotExists() {
        // Arrange
        Long userId = 1L;
        when(userJpa.existsById(userId)).thenReturn(false);

        // Act
        boolean result = userValidateImp.isGetById(userId);

        // Assert
        assertFalse(result);
        verify(userJpa, times(1)).existsById(userId);
    }
}