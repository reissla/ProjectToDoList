package br.com.davireis.projectUsers.Services;

import br.com.davireis.projectUsers.Dto.TaskDTO;
import br.com.davireis.projectUsers.Exceptions.userAlreadyExistsException;
import br.com.davireis.projectUsers.Exceptions.userNotFoundException;
import br.com.davireis.projectUsers.Repository.TaskRepository;
import br.com.davireis.projectUsers.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepository;

    //Faz a mesma coisa do Autowired, mas é uma outra forma de injeção sem acoplar com o spring boot(aconselhável dessa forma)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Listar Todas as Task
    public List<Task> listAllTasks(){
        return taskRepository.findAll();
    }

    //Encontrar Task por ID
    public TaskDTO findTaskById(Long id){
        findById(id);
        Task entity = taskRepository.findById(id).get();
        TaskDTO taskDTO = new TaskDTO(entity);
        return taskDTO;
    }

    public List<TaskDTO> listAll(){
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskDTO::new).toList();
    }

    //Inserir Task no banco de dados
    public Task insertTask(TaskDTO taskDTO){
        Task task = new Task(taskDTO);
        return taskRepository.save(task);
    }

    //Deletar Task
    public void deleteTask(Long id){
        findById(id);
        taskRepository.deleteById(id);
    }

    //Listar all tasks pelo Id do user passado:
    public List<TaskDTO> listTasksByUserId(UUID id){
        List<Task> tasks = taskRepository.findAllTasksByUserId(id);
        return tasks.stream().map(TaskDTO::new).toList();
    }

    //Listar task pelo userId
    public TaskDTO findById(Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(!taskOptional.isPresent()) {
            throw new userNotFoundException();
        }
        return null;
    }

    public void verifyIfAlreadyExists(Task task){
        Optional<Task> userFunction = taskRepository.findByTitle(task.getTitle());
        if(userFunction.isPresent()){
            throw new userAlreadyExistsException();
        }
    }
}
