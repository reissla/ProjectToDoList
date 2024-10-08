package br.com.davireis.projectUsers.Services;

import br.com.davireis.projectUsers.Dto.RegisterDto;
import br.com.davireis.projectUsers.Dto.UserDTO;
import br.com.davireis.projectUsers.Exceptions.userAlreadyExistsException;
import br.com.davireis.projectUsers.Exceptions.userNotFoundException;
import br.com.davireis.projectUsers.Repository.UserRepository;
import br.com.davireis.projectUsers.domain.Roles;
import br.com.davireis.projectUsers.domain.Task;
import br.com.davireis.projectUsers.domain.User;
import br.com.davireis.projectUsers.producers.UserProducer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public RegisterDto insertUser(UserDTO dto){
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getSenha());
        User user = new User(dto.getId(), dto.getName(), dto.getLogin(), encryptedPassword, dto.getEmail(),Roles.USER);
        userRepository.save(user);
        RegisterDto userId = new RegisterDto(user.getId());
        userProducer.publishMessageEmail(user);//envio de mensagens email
        return userId;
    }

    //add user com Role ADMIN
    public User addADMIN(UserDTO dto){
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getSenha());
        User user = new User(dto.getId(), dto.getName(), dto.getLogin(), encryptedPassword, dto.getEmail(),Roles.ADMIN);
        userRepository.save(user);
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


