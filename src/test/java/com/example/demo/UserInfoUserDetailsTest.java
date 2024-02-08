package com.example.demo;

import com.example.demo.model.UserInfo;
import com.example.demo.model.UserInfoUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoUserDetailsTest {

    @Test
    void testGetAuthorities() {
        // Arrange
        UserInfo userInfo = new UserInfo(1, "testUser", "test@example.com", "password", new String[]{"ROLE_USER", "ROLE_ADMIN"}, true);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        // Assert
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void testGetAuthoritiesEmptyRoles() {
        // Arrange
        UserInfo userInfo = new UserInfo(1, "testUser", "test@example.com", "password", new String[]{}, true);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        // Assert
        assertTrue(authorities.isEmpty());
    }

    @Test
    void testGetPassword() {
        // Arrange
        UserInfo userInfo = new UserInfo(1, "testUser", "test@example.com", "password", new String[]{"ROLE_USER"}, true);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        String password = userDetails.getPassword();

        // Assert
        assertEquals("password", password);
    }

    @Test
    void testGetUsername() {
        // Arrange
        UserInfo userInfo = new UserInfo(1, "testUser", "test@example.com", "password", new String[]{"ROLE_USER"}, true);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        String username = userDetails.getUsername();

        // Assert
        assertEquals("testUser", username);
    }

    @Test
    void testIsAccountNonExpired() {
        // Arrange
        UserInfo userInfo = new UserInfo(1, "testUser", "test@example.com", "password", new String[]{"ROLE_USER"}, true);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        boolean accountNonExpired = userDetails.isAccountNonExpired();

        // Assert
        assertTrue(accountNonExpired);
    }

    @Test
    void testIsAccountNonLocked() {
        // Arrange
        UserInfo userInfo = new UserInfo(1, "testUser", "test@example.com", "password", new String[]{"ROLE_USER"}, true);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        boolean accountNonLocked = userDetails.isAccountNonLocked();

        // Assert
        assertTrue(accountNonLocked);
    }

    @Test
    void testIsCredentialsNonExpired() {
        // Arrange
        UserInfo userInfo = new UserInfo(1, "testUser", "test@example.com", "password", new String[]{"ROLE_USER"}, true);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        boolean credentialsNonExpired = userDetails.isCredentialsNonExpired();

        // Assert
        assertTrue(credentialsNonExpired);
    }

    @Test
    void testIsEnabled() {
        // Arrange
        UserInfo userInfo = new UserInfo(1, "testUser", "test@example.com", "password", new String[]{"ROLE_USER"}, true);
        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        // Act
        boolean enabled = userDetails.isEnabled();

        // Assert
        assertTrue(enabled);
    }


}
