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
public class ActivateService implements JavaDelegate {


    public static final Long admin=(long)1;
    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");

        String username = registration.get(6).getFieldValue();

        User u = this.userRepository.findUserByUsername(username);
    //    User admin = this.userRepository.findUserById((long)1);

        execution.setVariable("admin", "user@yahoo.com");
        u.setEnabled(true);
        u.setProcessId(execution.getProcessInstanceId());
        this.userRepository.save(u);

    }
}
