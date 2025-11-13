package calories_control.features.auth.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calories_control.features.auth.infra.Role;

class UserTest {

    @Test
    void testConstructorWithParameters() {
        // Arrange
        String name = "John Doe";
        String email = "john@example.com";
        String password = "password123";

        // Act
        User user = new User(name, email, password);

        // Assert
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertNull(user.getId());
        assertNull(user.getRole());
    }

    @Test
    void testDefaultConstructor() {
        // Act
        User user = new User();

        // Assert
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        User user = new User();
        Long id = 1L;
        String name = "Jane Doe";
        String email = "jane@example.com";
        String password = "securepass";
        Role role = Role.USER;

        // Act
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }
}