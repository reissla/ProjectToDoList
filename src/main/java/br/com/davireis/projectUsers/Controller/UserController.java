package br.com.davireis.projectUsers.Controller;


import br.com.davireis.projectUsers.Dto.UserDTO;
import br.com.davireis.projectUsers.Services.UserService;
import br.com.davireis.projectUsers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void insertUser(@RequestBody UserDTO userDTO){
        userService.insertUser(userDTO);
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
