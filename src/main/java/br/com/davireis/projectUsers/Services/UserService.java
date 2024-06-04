package br.com.davireis.projectUsers.Services;

import br.com.davireis.projectUsers.Dto.UserDTO;
import br.com.davireis.projectUsers.Exceptions.userAlreadyExistsException;
import br.com.davireis.projectUsers.Exceptions.userNotFoundException;
import br.com.davireis.projectUsers.Repository.UserRepository;
import br.com.davireis.projectUsers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User insertUser(UserDTO userDTO){
        User user = new User(userDTO);
        verifyIfAlreadyExists(user);
        return userRepository.save(user);
    }

    public List<UserDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO findUserById(Long id){
        findById(id);
        User entity = userRepository.findById(id).get();
        UserDTO userDTO = new UserDTO(entity);
        return userDTO;
    }

    public void deleteUser(Long id){
        findById(id);
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(UserDTO userDTO){
        User user = new User(userDTO);
        return new UserDTO(userRepository.save(user));
    }

    

    public void verifyIfAlreadyExists(User user){
        Optional<User> userFunction = userRepository.findByName(user.getName());
        if(userFunction.isPresent()){
            throw new userAlreadyExistsException();
        }
    }

    public UserDTO findById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()) {
            throw new userNotFoundException();
        }
        return null;
    }

}


