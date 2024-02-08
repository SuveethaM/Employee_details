package com.example.demo;
import com.example.demo.model.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {

    @Test
    void testNoArgsConstructor() {
        // Act
        UserInfo userInfo = new UserInfo();

        // Assert
        assertEquals(0, userInfo.getId());
        assertNull(userInfo.getName());
        assertNull(userInfo.getEmail());
        assertNull(userInfo.getPassword());
        assertNull(userInfo.getRoles());
        assertFalse(userInfo.isEnabled());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        int id = 1;
        String name = "John Doe";
        String email = "john@example.com";
        String password = "password";
        String[] roles = {"USER", "ADMIN"};
        boolean enabled = true;

        // Act
        UserInfo userInfo = new UserInfo(id, name, email, password, roles, enabled);

        // Assert
        assertEquals(id, userInfo.getId());
        assertEquals(name, userInfo.getName());
        assertEquals(email, userInfo.getEmail());
        assertEquals(password, userInfo.getPassword());
        assertEquals("USER,ADMIN", userInfo.getRoles()); // roles are joined with ","
        assertTrue(userInfo.isEnabled());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        UserInfo userInfo = new UserInfo();
        int id = 1;
        String name = "John Doe";
        String email = "john@example.com";
        String password = "password";
        String[] roles = {"USER", "ADMIN"};
        boolean enabled = true;

        // Act
        userInfo.setId(id);
        userInfo.setName(name);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setRoles(String.join(",", roles));
        userInfo.setEnabled(enabled);

        // Assert
        assertEquals(id, userInfo.getId());
        assertEquals(name, userInfo.getName());
        assertEquals(email, userInfo.getEmail());
        assertEquals(password, userInfo.getPassword());
        assertEquals("USER,ADMIN", userInfo.getRoles()); // roles are joined with ","
        assertTrue(userInfo.isEnabled());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        int id1 = 1;
        int id2 = 2;
        String name = "John Doe";
        String email = "john@example.com";
        String password = "password";
        String[] roles = {"USER", "ADMIN"};
        boolean enabled = true;

        // Act
        UserInfo userInfo1 = new UserInfo(id1, name, email, password, roles, enabled);
        UserInfo userInfo2 = new UserInfo(id1, name, email, password, roles, enabled);
        UserInfo userInfo3 = new UserInfo(id2, name, email, password, roles, enabled);

        // Assert
        assertTrue(userInfo1.equals(userInfo2) && userInfo2.equals(userInfo1));
        assertEquals(userInfo1.hashCode(), userInfo2.hashCode());
        assertFalse(userInfo1.equals(userInfo3) || userInfo3.equals(userInfo1));
        assertNotEquals(userInfo1.hashCode(), userInfo3.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        int id = 1;
        String name = "John Doe";
        String email = "john@example.com";
        String password = "password";
        String[] roles = {"USER", "ADMIN"};
        boolean enabled = true;

        // Act
        UserInfo userInfo = new UserInfo(id, name, email, password, roles, enabled);

        // Assert
        assertTrue(userInfo.toString().contains("id=" + id));
        assertTrue(userInfo.toString().contains("name=" + name));
        assertTrue(userInfo.toString().contains("email=" + email));
        assertTrue(userInfo.toString().contains("password=" + password));
        assertTrue(userInfo.toString().contains("roles=USER,ADMIN"));
        assertTrue(userInfo.toString().contains("enabled=true"));
    }

    // Add more tests for validations, if any

}
