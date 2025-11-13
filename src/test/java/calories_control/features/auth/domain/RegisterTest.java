package calories_control.features.auth.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegisterTest {

    @Test
    void testDefaultConstructor() {
        // Act
        Register register = new Register();

        // Assert
        assertNull(register.getEmail());
        assertNull(register.getPassword());
        assertNull(register.getName());
        assertNull(register.getConfirmPassword());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Register register = new Register();
        String email = "test@example.com";
        String password = "password123";
        String name = "Test User";
        String confirmPassword = "password123";

        // Act
        register.setEmail(email);
        register.setPassword(password);
        register.setName(name);
        register.setConfirmPassword(confirmPassword);

        // Assert
        assertEquals(email, register.getEmail());
        assertEquals(password, register.getPassword());
        assertEquals(name, register.getName());
        assertEquals(confirmPassword, register.getConfirmPassword());
    }
}