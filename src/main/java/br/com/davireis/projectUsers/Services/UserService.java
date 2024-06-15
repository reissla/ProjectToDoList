package br.com.davireis.projectUsers.Services;

import br.com.davireis.projectUsers.Dto.TaskDTO;
import br.com.davireis.projectUsers.Dto.UserDTO;
import br.com.davireis.projectUsers.Exceptions.userAlreadyExistsException;
import br.com.davireis.projectUsers.Exceptions.userNotFoundException;
import br.com.davireis.projectUsers.Repository.UserRepository;
import br.com.davireis.projectUsers.entity.Task;
import br.com.davireis.projectUsers.entity.User;
import br.com.davireis.projectUsers.producers.UserProducer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository=userRepository;
        this.userProducer = userProducer;
    }

    //Cadastrar um User
    @Transactional
    public User insertUser(User user){
        user = userRepository.save(user);
        //verifyIfAlreadyExists(user);
        userProducer.publishMessageEmail(user);//envio de mensagens email
        return user;
    }

    //Listar Users
    public List<UserDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    //Procurar um User usando ID
    public UserDTO findUserById(UUID id){
        findById(id);
        User entity = userRepository.findById(id).get();
        UserDTO userDTO = new UserDTO(entity);
        return userDTO;
    }

    //Deletar um User
    public void deleteUser(UUID id){
        findById(id);
        userRepository.deleteById(id);
    }

    //Atualizar colunas de um User
    public UserDTO updateUser(UserDTO userDTO){
        User user = new User(userDTO);
        return new UserDTO(userRepository.save(user));
    }

    //Fazer retornar uma Exception caso nao encontrev
    //Mudar senha do User
    //Envia um email
    public void changeUserPassword(String email, String login, String senha){
        User user = userRepository.findByEmailAndLogin(email, login).get();
        user.setSenha(senha);
        userProducer.changePasswordMessageEmail(user.getId(), user.getEmail());
    }

    //Adiciona uma task a um user
    public User addTaskToUser(UUID id, Task task){
        User user = userRepository.findById(id).get();
        user.setTaskList(task);
        return userRepository.save(user);
    }

    //Verificar se um User ja existe -> usando name
    public void verifyIfAlreadyExists(User user){
        Optional<User> userFunction = userRepository.findByName(user.getName());
        if(userFunction.isPresent()){
            throw new userAlreadyExistsException();
        }
    }

    //Verficar se um User ja existe -> usando ID
    public UserDTO findById(UUID id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()) {
            throw new userNotFoundException();
        }
        return null;
    }

    //Verificar se um User ja existe -> usando Email, login
    public UserDTO findByEmailAndLogin(String email, String login){
        Optional<User> userOptional = userRepository.findByEmailAndLogin(email, login);
        if(!userOptional.isPresent()){
            throw new userNotFoundException();
        }
        return null;
    }
}


