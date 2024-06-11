package br.com.davireis.projectUsers.Controller;


import br.com.davireis.projectUsers.Dto.UserDTO;
import br.com.davireis.projectUsers.Services.UserService;
import br.com.davireis.projectUsers.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> listAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody @Valid UserDTO userDTO){
        var user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insertUser(user));
    }

    @PostMapping(value = "/teste")
    public ResponseEntity<User> changeUserPassword(@RequestBody UserDTO userDTO){
        userService.changeUserPassword(userDTO.getEmail(), userDTO.getLogin(), userDTO.getSenha());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
