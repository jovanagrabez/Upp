package root.demo.services;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.DTO.ActivateRecenzentDTO;
import root.demo.model.User;
import root.demo.repository.UserRepository;

import java.util.HashMap;

@Service
public class CamundaService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    FormService formService;

    @Autowired
    UserService userService;


    public User activateRecenzent(ActivateRecenzentDTO activateRecenzentDTO) {
        User user = userRepository.getOne(activateRecenzentDTO.getUserId());

        Task task = taskService.createTaskQuery().processInstanceId(user.getProcessId()).singleResult();
      System.out.println(task.getName() + "LALALAJOVANAA");

        HashMap<String,Object> mapa = new HashMap<>();
        mapa.put("potvrdi",activateRecenzentDTO.isActivate());

        if(activateRecenzentDTO.isActivate()==false){
            user.setRecenzent(false);
            userRepository.save(user);

        }
        formService.submitTaskForm(task.getId(),mapa);
        return user;
    }
}
