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
public class RecezentService  implements JavaDelegate {
    @Autowired
    UserRepository userRepository;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");
        String username = registration.get(6).getFieldValue();

        // da li radii commit
        User u = this.userRepository.findUserByUsername(username);

        u.setRecenzent(true);
        this.userRepository.save(u);


    }
}
