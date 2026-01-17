package calories_control.features.auth.app.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import calories_control.features.shared.Result;
import calories_control.features.shared.State;

class LoginUseCaseTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn("user");
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        SecurityContext mockSecurityContext = mock(SecurityContext.class);

        try (MockedStatic<SecurityContextHolder> mockedSecurity = mockStatic(SecurityContextHolder.class)) {
            mockedSecurity.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);
            // Act
            Result result = loginService.login(email, password);

            // Assert
            assertEquals(State.SUCCESS, result.getState());
            assertEquals("login success ", result.getMessage());
            verify(authenticationManager, times(1)).authenticate(any());
        }
    }

    @Test
    void testLogin_Failure() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        when(authenticationManager.authenticate(any())).thenReturn(null);

        // Act
        Result result = loginService.login(email, password);

        // Assert
        assertEquals(State.UNAUTHORIZED, result.getState());
        assertEquals("login failed", result.getMessage());
        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test
    void testLogin_NotAuthenticated() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        // Act
        Result result = loginService.login(email, password);

        // Assert
        assertEquals(State.UNAUTHORIZED, result.getState());
        assertEquals("login failed", result.getMessage());
        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test
    void testLogin_AnonymousUser() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn("anonymousUser");
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        // Act
        Result result = loginService.login(email, password);

        // Assert
        assertEquals(State.UNAUTHORIZED, result.getState());
        assertEquals("login failed", result.getMessage());
        verify(authenticationManager, times(1)).authenticate(any());
    }
}