package root.demo.services.camunda;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.Casopis;
import root.demo.model.FormSubmissionDto;
import root.demo.model.User;
import root.demo.repository.CasopisRepository;
import root.demo.repository.UserRepository;

import java.util.List;

@Service
public class DodajUrednikaService implements JavaDelegate {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CasopisRepository casopisRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        System.out.println("IZVRSAVAA");
        String username = (String)execution.getVariable("username");
        List<FormSubmissionDto> magazine = (List<FormSubmissionDto>)execution.getVariable("magazine");
        String issn = magazine.get(1).getFieldValue();

        User user = this.userRepository.findUserByUsername(username);

        Casopis c = this.casopisRepository.findByIssn(issn);
        c.setMainEditor(user);

        this.casopisRepository.save(c);

    }
}
