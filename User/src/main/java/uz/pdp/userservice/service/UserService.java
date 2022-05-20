package uz.pdp.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.common.ApiResponse;
import uz.pdp.userservice.entity.User;
import uz.pdp.userservice.payload.UserDto;
import uz.pdp.userservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto getUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return null;

        }
        User user = optionalUser.get();
        UserDto userDto = new UserDto(user.getFirstName(),user.getLastName(),
                user.getEmail(),user.getPhoneNumber(),user.getPassword());
        return userDto;



    }
}
