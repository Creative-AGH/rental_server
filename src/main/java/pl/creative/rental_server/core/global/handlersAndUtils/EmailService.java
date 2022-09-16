package pl.creative.rental_server.core.global.handlersAndUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.creative.rental_server.db.entities.TokenToRegister;
import pl.creative.rental_server.core.features.all.register.dto.RegisterNewAccountDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    @Value("${spring.mail.username}")
    private String whoIsSendingEmail;
    private final JavaMailSenderImpl javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String email, String token) throws MessagingException, UnsupportedEncodingException {
        Context context = new Context();
//        context.setVariable("user", user);
//        context.setVariable("validateLink", frontEndLink + "/validate?token=" + token);
//        String emailInHtml = templateEngine.process("emails/registerConfirmationEmail.html", context); TODO uncomment that line and update register email
        log.info("Sending account veryfication to email {}",email);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject("Zweryfikuj swoj email w sieci lokalnej Creative");
//        helper.setText(emailInHtml, true);//TODO uncomment that line
        helper.setTo(email);
        helper.setFrom(whoIsSendingEmail);
        javaMailSender.send(mimeMessage);
        log.info("Send email succesfully");
    }



    public void sendMail(RegisterNewAccountDto registerNewAccountDto, TokenToRegister tokenToRegister) {

    }
}
