package br.com.davireis.projectUsers.Controller;


import br.com.davireis.projectUsers.Dto.TaskDTO;
import br.com.davireis.projectUsers.Services.TaskService;
import br.com.davireis.projectUsers.domain.Task;
import br.com.davireis.projectUsers.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/Tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public void insertTask(@RequestBody TaskDTO taskDTO){
        taskService.insertTask(taskDTO);
    }

    @GetMapping
    public List<TaskDTO> listAll(){
        return taskService.listAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @GetMapping(value = "/{id}")
    public TaskDTO findById(@PathVariable Long id){
        return taskService.findTaskById(id);
    }

    @GetMapping(value = "/listAll/{id}")
    public List<TaskDTO> listAllTasksByUserId(@PathVariable UUID id){
        return taskService.listTasksByUserId(id);
    }

    @GetMapping(value = "/list/{id}")
    public List<TaskDTO> listTasksByUserId(@PathVariable UUID id){
        return taskService.listTasksByUserId(id);
    }
}
