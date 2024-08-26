package br.com.davireis.projectUsers.Controller;


import br.com.davireis.projectUsers.Dto.TaskDTO;
import br.com.davireis.projectUsers.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
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
    public  TaskDTO findById(@PathVariable Long id){
        return taskService.findTaskById(id);
    }
}
