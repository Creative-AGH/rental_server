package pl.creative.rental_server.registerManagment.emailManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.creative.rental_server.entities.TokenToRegister;
import pl.creative.rental_server.registerManagment.dto.RegisterNewAccountDto;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSenderImpl javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String email, String token) throws MessagingException, UnsupportedEncodingException {
        Context context = new Context();
//        context.setVariable("user", user);
//        context.setVariable("validateLink", frontEndLink + "/validate?token=" + token.getToken());
//        String process = templateEngine.process("emails/registerConfirmationEmail.html", context);
        log.info("Sending account veryfication to email {}",email);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject("Działa ta jebana nazstowska technologia z AGH !");
        helper.setText("Dziala !", false);
        helper.setTo(email);
        helper.setFrom("");
        javaMailSender.send(mimeMessage);
        log.info("Send email");
    }



    public void sendMail(RegisterNewAccountDto registerNewAccountDto, TokenToRegister tokenToRegister) {

    }
}
