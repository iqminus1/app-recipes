package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.model.Code;
import up.pdp.apprecipes.model.User;
import up.pdp.apprecipes.repository.CodeRepository;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.service.MailService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Service
@EnableAsync
public class MailServiceImpl implements MailService {
    public static final Map<String,User> TEMP_USERS = new HashMap<>();
    private final MailSender mailSender;
    private final Random random;
    private final CodeRepository codeRepository;
    private final UserRepository userRepository;

    @Value("${app.code.attempt}")
    private Integer attempt;

    @Async
    @Override
    public void sendVerify(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recipes");
        Integer codeInt = generatePassword();
        Code code = new Code(email, codeInt.toString(), attempt);
        message.setText("Your verification code: -> "+codeInt);
        mailSender.send(message);
        codeRepository.save(code);
        userRepository.save(TEMP_USERS.get(email));
    }

    private Integer generatePassword() {
        return new Random().nextInt(9000) + 1000;
    }
}
