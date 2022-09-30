package pl.creative.rental_server.core.global.handlersAndUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.creative.rental_server.core.features.all.register.dto.RegisterNewAccountDto;
import pl.creative.rental_server.db.entities.TokenToRegister;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    @Value("${spring.mail.username}")
    private String whoIsSendingEmail;
    @Value("${frontend.link}")
    private String frontEndLink;
    private final JavaMailSenderImpl javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(RegisterNewAccountDto account, String token) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", account);
        context.setVariable("validateLink", frontEndLink + "/validate?token=" + token);
        String emailInHtml = templateEngine.process("registerConfirmationEmail.html", context);
        log.info("Sending account verification to email {}", account.getEmail());
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject("Zweryfikuj swoj email w sieci AGH dla aplikacji RentMe");
        helper.setText(emailInHtml, true);
        helper.setTo(account.getEmail());
        helper.setFrom(whoIsSendingEmail);
        javaMailSender.send(mimeMessage);
        log.info("Send email successfully");
    }
}
