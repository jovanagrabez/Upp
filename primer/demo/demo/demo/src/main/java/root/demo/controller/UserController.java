package root.demo.controller;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.demo.model.FormFieldsDto;
import root.demo.model.TaskDto;
import root.demo.model.User;
import root.demo.repository.NaucnaOblastRepository;
import root.demo.security.TokenUtils;
import root.demo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    FormService formService;

    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;

    @Autowired
    RuntimeService runtimeService;

    @GetMapping(value = "/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);

        return user;
    }

    @GetMapping(value = "/recenzenti")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.getAllRecenzent();
        return new ResponseEntity<>(users, HttpStatus.OK);
        }


    @GetMapping(value = "/rec")
    public ResponseEntity<List<User>> getRecenzenti() {
        List<User> users = this.userService.getRec();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/urednici")
    public ResponseEntity<List<User>> getUrednici() {
        List<User> users = this.userService.getUrednici();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("tasks")
    public ResponseEntity getTasks() {

        String username = this.tokenUtils.getUsernameFromToken(this.tokenUtils.getToken(this.httpServletRequest));

        System.out.println("Getujem taskove za usera: " + username);
        List<Task> userTasks = taskService.createTaskQuery()
                .taskAssignee(username)
                .list();
        System.out.println(userTasks.size());
        List<TaskDto> finalTasks = new ArrayList<TaskDto>();
        for (Task t : userTasks) {
            System.out.println(t.toString());
            finalTasks.add(new TaskDto(t.getId(), t.getName(), t.getAssignee()));
        }

        System.out.println(finalTasks.size());
        return new ResponseEntity<>(finalTasks, HttpStatus.OK);
    }
    @GetMapping("task/{taskId}")
    public  @ResponseBody
    FormFieldsDto getTask(@PathVariable String taskId) {
        TaskFormData tfd = formService.getTaskFormData(taskId);
        List<FormField> properties = tfd.getFormFields();

        Task task =  taskService.createTaskQuery().taskId(taskId).singleResult();

  /*      List<NaucnaOblast> naucneOblasti= this.naucnaOblastRepository.findAll();

        for(FormField form: properties){
            if(form.getId().equals("naucnaOblasti")){
                EnumFormType enumFormType = (EnumFormType) form.getType();
                Map<String, String> items = enumFormType.getValues();
                for(NaucnaOblast naucna: naucneOblasti){
                    items.put(naucna.getName(),naucna.getName());
                }
                runtimeService.setVariable(task.getProcessInstanceId(),"naucnaOblasti", items);

            }
        }*/
        return new FormFieldsDto(taskId, task.getProcessInstanceId(), properties);
    }

}
