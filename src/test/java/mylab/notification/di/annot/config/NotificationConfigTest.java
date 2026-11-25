package mylab.notification.di.annot.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.SmsNotificationService;
import mylab.notification.di.annot.NotificationManager;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    void testNotificationManager() {

        // 1. NotificationManager 객체 주입 확인
        assertNotNull(notificationManager);

        // 2. 이메일 서비스 주입 확인
        assertNotNull(notificationManager.getEmailService());

        EmailNotificationService emailService =
                (EmailNotificationService)
                notificationManager.getEmailService();

        // 3. 이메일 서비스 설정값 검증
        assertEquals("smtp.gmail.com",
                emailService.getSmtpServer());

        assertEquals(587, emailService.getPort());

        // 4. SMS 서비스 주입 확인
        assertNotNull(notificationManager.getSmsService());

        SmsNotificationService smsService =
                (SmsNotificationService)
                notificationManager.getSmsService();

        // 5. SMS 제공업체 검증
        assertEquals("SKT", smsService.getProvider());

        // 6. 이메일 및 SMS 알림 메서드 실행
        notificationManager.sendNotificationByEmail("테스트 이메일");

        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}