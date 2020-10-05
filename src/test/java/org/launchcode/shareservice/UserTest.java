package org.launchcode.shareservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.launchcode.shareservice.models.User;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class UserTest {
    User testUser = new User("Miley", "12345");

    @Test
    public void emptyTest() {
        assertEquals(10, 10, .001);
    }

    @Test
    public void userNameTest() {
        assertEquals("Miley", testUser.getName());
    }

    @Test
    public void userIsMatchingPasswordTest() {
        assertEquals(true, testUser.isMatchingPassword("12345"));
    }

}
