package br.com.davireis.projectUsers.Services;

import br.com.davireis.projectUsers.Dto.UserDTO;
import br.com.davireis.projectUsers.Exceptions.userAlreadyExistsException;
import br.com.davireis.projectUsers.Exceptions.userNotFoundException;
import br.com.davireis.projectUsers.Repository.UserRepository;
import br.com.davireis.projectUsers.entity.User;
import br.com.davireis.projectUsers.producers.UserProducer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository=userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public User insertUser(User user){
        user = userRepository.save(user);
        //verifyIfAlreadyExists(user);
        userProducer.publishMessageEmail(user);//envio de mensagens email
        return user;
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

    //Fazer retornar uma Exception caso nao encontrev
    public void changeUserPassword(String email, String login, String senha){
        User user = userRepository.findByEmailAndLogin(email, login).get();
        user.setSenha(senha);
        userProducer.changePasswordMessageEmail(user.getId(), user.getEmail());
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

    public UserDTO findByEmailAndLogin(String email, String login){
        Optional<User> userOptional = userRepository.findByEmailAndLogin(email, login);
        if(!userOptional.isPresent()){
            throw new userNotFoundException();
        }
        return null;
    }
}


