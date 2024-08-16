package up.pdp.apprecipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.model.Code;
import up.pdp.apprecipes.repository.CodeRepository;

import java.util.Random;

@RequiredArgsConstructor
@Service
@EnableAsync
public class MailServiceImpl implements MailService {
    private final MailSender mailSender;
    private final Random random;
    private final CodeRepository codeRepository;

    @Value("${app.code.attempt}")
    private Integer attempt;

    @Async
    @Override
    public void sendVerify(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recipes");
        String codeString = generatePassword();
        Code code = new Code(email, codeString, attempt);
        message.setText("Your verification code: -> "+codeString);
        mailSender.send(message);
        codeRepository.save(code);
    }

    private String generatePassword() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (random.nextBoolean()) {
                sb.append(random.nextInt(0, 9));
            } else {
                if (random.nextBoolean()) {
                    sb.append((char) random.nextInt(65, 90));
                } else {
                    sb.append((char) random.nextInt(97, 122));
                }
            }
        }
        return sb.toString();
    }
}
