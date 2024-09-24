package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.SignInDto;
import up.pdp.apprecipes.dto.request.SignUpDto;
import up.pdp.apprecipes.dto.request.UserCRUDDto;
import up.pdp.apprecipes.dto.request.UserDeleteDto;
import up.pdp.apprecipes.dto.response.JwtTokenDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.dto.response.UserDto;
import up.pdp.apprecipes.exceptions.InvalidDataException;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Attachment;
import up.pdp.apprecipes.model.User;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.security.JwtProvider;
import up.pdp.apprecipes.service.UserService;
import up.pdp.apprecipes.exceptions.AlreadyExistsException;
import up.pdp.apprecipes.utils.Validations;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final EmailService emailService;
    private final AttachmentRepository attachmentRepository;
    public static final Map<String, User> TEMP_USERS = new ConcurrentHashMap<>();

    @SneakyThrows
    @Override
    public SuccessResponse signUp(SignUpDto signUp) {
        if (!Objects.equals(signUp.getPassword(), signUp.getConfirmPassword()))
            throw new BadRequestException("Passwords do not match");
        if (userRepository.findByEmail(signUp.getEmail()).isPresent())
            throw new AlreadyExistsException("User");

        emailService.send(signUp.getEmail());
        TEMP_USERS.put(signUp.getEmail(),
                User.builder()
                        .email(signUp.getEmail())
                        .name(signUp.getName())
                        .password(passwordEncoder.encode(signUp.getPassword()))
                        .build());

        return new SuccessResponse("Email sent");
    }

    @Override
    public JwtTokenDto verifyEmail(String email, Integer code) {
        if (emailService.check(code, email)) {
            User user = TEMP_USERS.get(email);
            if (user == null)
                throw new NotFoundException("User");
            user.setEnable(true);
            return new JwtTokenDto(jwtProvider.generateToken(userRepository.save(user)));
        }
        throw new InvalidDataException("verification code");
    }

    @Override
    public JwtTokenDto signIn(SignInDto signIn) {
        User user = userRepository.findByEmail(signIn.getEmail()).orElseThrow(
                () -> NotFoundException.error("User")
        );
        if (passwordEncoder.matches(signIn.getPassword(), user.getPassword())) {
            return new JwtTokenDto(jwtProvider.generateToken(user));
        }
        throw new InvalidDataException("password");
    }

    @Override
    public UserDto getById(String id) {
        return new UserDto(userRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> NotFoundException.errorById("User", id)
        ));
    }

    @Override
    public UserDto update(UserCRUDDto crudDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (crudDto.getAttachmentId() != null) {
            Optional<Attachment> attachment = attachmentRepository.findById(crudDto.getAttachmentId());
            attachment.ifPresent(user::setAttachment);
        }
        user.setName(Validations.requireNonNullElse(crudDto.getName(), user.getName()));
        if (crudDto.getPassword() != null)
            user.setPassword(passwordEncoder.encode(crudDto.getPassword()));

        return new UserDto(userRepository.save(user));
    }

    @Override
    public SuccessResponse delete(UserDeleteDto deleteDto) {
        User byEmail = userRepository.getByEmail(deleteDto.getEmail());
        if (passwordEncoder.matches(deleteDto.getPassword(), byEmail.getPassword())) {
            userRepository.delete(byEmail);
            return new SuccessResponse("User deleted successfully");
        }
        return new SuccessResponse("User could not be deleted");
    }

}
