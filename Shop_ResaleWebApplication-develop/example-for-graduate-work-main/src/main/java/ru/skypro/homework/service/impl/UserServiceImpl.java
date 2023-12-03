package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.validation.constraints.NotNull;

import java.io.IOException;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;


    /**
     * метод matches проверяет, закодированный пароль, полученный из хранилища,
     * совпадает с отправленным необработанным паролем после того, как он тоже закодирован.
     * Возвращает true, если пароли совпадают, false, если они не совпадают.
     * Сам сохраненный пароль никогда не расшифровывается.
     * newPasswordDTO.getCurrentPassword() - исходный пароль для кодирования и сопоставления.
     * passwords- кодированный пароль из хранилища для сравнения.
     * метод encode - кодирует необработанный пароль
     *
     * @param newPasswordDTO
     * @return tru or false
     */
    @Override
    public boolean setPassword(NewPasswordDTO newPasswordDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findUserByUserName(username).orElseThrow(()->new UserNotFoundException("UserNotFound"));

        if (!passwordEncoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Доделать");
        }

        String encode = passwordEncoder.encode(newPasswordDTO.getNewPassword());
        user.setPassword(encode);
        userRepository.save(user);
        return true;
    }


    @Override
    public UserDTO getUser() {
        //Если, аутентификация прошла успешно — это значит, имя и пароль верные.
        //Тогда объект Authentication сохраняется в SecurityContext, а тот, в свою очередь, — в SecurityContextHolder:
        //Authentication-объект, отражающий информацию о текущем пользователе и его привилегиях.
        //getPrincipal()-метод получения текущего пользователя
        //После  успешной аутентификации в поле Principal объекта Authentication будет реальный пользователь в виде UserDetails:

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findUserByUserName(username).orElseThrow(()->new UserNotFoundException("UserNotFound"));
        if (isNull(user)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        UserDTO userDTO = usersMapper.usersEntityToUsersDto(user);
        return userDTO;
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findUserByUserName(username).orElseThrow(()->new UserNotFoundException("UserNotFound"));
        User userEntity = usersMapper.updateUserDtoToUserEntity(updateUserDTO);
        userRepository.save(userEntity);
        return usersMapper.userEntityToUpdateUsersDto(userEntity);
    }

    @Override
    public boolean updateUserImage(MultipartFile image)throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findUserByUserName(username).orElseThrow(()->new UserNotFoundException("UserNotFound"));
        user.setImage(imageService.saveToDb(image));
        return true;
    }

//    @NotNull
//    private String objectAuthentication(Authentication authentication){
//        Authentication authentications = SecurityContextHolder.getContext().getAuthentication();
//        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
//        return username;
//    }

}
