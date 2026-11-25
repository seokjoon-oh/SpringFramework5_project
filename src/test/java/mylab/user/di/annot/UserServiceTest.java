package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testUserService() {

        // 1. UserService 객체 주입 확인
        assertNotNull(userService);

        // 2. UserRepository 객체 주입 확인
        assertNotNull(userService.getUserRepository());

        // 3. dbType 값 주입 확인
        assertEquals("MySQL",
                userService.getUserRepository().getDbType());

        // 4. SecurityService 객체 주입 확인
        assertNotNull(userService.getSecurityService());

        // 5. 비밀번호가 전달된 경우
        assertTrue(
                userService.registerUser("user01", "홍길동", "1234")
        );

        // 6. 비밀번호가 빈 문자열인 경우
        assertFalse(
                userService.registerUser("user02", "김철수", "")
        );

        // 7. 비밀번호가 null인 경우
        assertFalse(
                userService.registerUser("user03", "이영희", null)
        );
    }
}