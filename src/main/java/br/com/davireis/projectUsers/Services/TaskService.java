package br.com.davireis.projectUsers.Services;

import br.com.davireis.projectUsers.Dto.TaskDTO;
import br.com.davireis.projectUsers.Exceptions.userAlreadyExistsException;
import br.com.davireis.projectUsers.Exceptions.userNotFoundException;
import br.com.davireis.projectUsers.Repository.TaskRepository;
import br.com.davireis.projectUsers.entity.Task;
import br.com.davireis.projectUsers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepository;

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

    //Inserir Task no banco de dados
    public Task insertTask(TaskDTO taskDTO){
        verifyIfAlreadyExists(taskDTO);
        Task task = new Task(taskDTO);
        return taskRepository.save(task);
    }

    //Deletar Task
    public void deleteTask(Long id){
        findById(id);
        taskRepository.deleteById(id);
    }


    public TaskDTO findById(Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(!taskOptional.isPresent()) {
            throw new userNotFoundException();
        }
        return null;
    }

    public void verifyIfAlreadyExists(TaskDTO taskDTO){
        Optional<Task> userFunction = taskRepository.findByTittle(taskDTO.getTittle());
        if(userFunction.isPresent()){
            throw new userAlreadyExistsException();
        }
    }
}
