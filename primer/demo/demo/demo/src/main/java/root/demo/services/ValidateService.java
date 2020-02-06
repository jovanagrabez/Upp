package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.FormSubmissionDto;
import root.demo.model.User;
import root.demo.repository.UserRepository;

import java.util.List;

@Service
public class ValidateService implements JavaDelegate {


    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private boolean b;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");
     //    b=true;
        String username = registration.get(6).getFieldValue();

       User u =this.userRepository.findUserByUsername(username);

        if(u==null){
            b=true;
        }else{
            b=false;
        }


        execution.setVariable("validan", b);
        }
}
