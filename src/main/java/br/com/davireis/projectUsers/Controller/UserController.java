package br.com.davireis.projectUsers.Controller;


import br.com.davireis.projectUsers.Dto.AuthenticationDto;
import br.com.davireis.projectUsers.Dto.LoginResponse;
import br.com.davireis.projectUsers.Dto.RegisterDto;
import br.com.davireis.projectUsers.Dto.UserDTO;
import br.com.davireis.projectUsers.Repository.UserRepository;
import br.com.davireis.projectUsers.Services.UserService;
import br.com.davireis.projectUsers.config.security.TokenService;
import br.com.davireis.projectUsers.domain.Task;
import br.com.davireis.projectUsers.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/teste")
    public List<UserDTO> listAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<User> insertUser(@RequestBody @Valid UserDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insertUser(dto));
    }

    @PostMapping(value = "/change")
    public ResponseEntity<User> changeUserPassword(@RequestBody UserDTO userDTO){
        userService.changeUserPassword(userDTO.getEmail(), userDTO.getLogin(), userDTO.getSenha());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> addTaskToUser(@PathVariable UUID id,@RequestBody Task task){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addTaskToUser(id, task));
    }

    //So usuario com a Role ADMIN
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findUserById(id));
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    //So usuario com a Role ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
