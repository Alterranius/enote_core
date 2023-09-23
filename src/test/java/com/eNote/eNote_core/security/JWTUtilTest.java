package com.eNote.eNote_core.security;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alterranius
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTUtilTest {
    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void generateToken_correctly_test() {
        String username = "Bob";
        Assertions.assertEquals(username,
                jwtUtil.validateTokenAndRetrieveClaim(
                jwtUtil.generateToken(username)));
    }

    @Test
    public void generateToken_incorrectly_test() {
        String username = "Bob";
        String fakeUsername = "Clara";
        Assertions.assertNotEquals(username,
                jwtUtil.validateTokenAndRetrieveClaim(
                        jwtUtil.generateToken(fakeUsername)));
    }
}