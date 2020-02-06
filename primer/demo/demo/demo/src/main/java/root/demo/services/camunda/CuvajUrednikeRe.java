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

import java.util.ArrayList;
import java.util.List;

@Service
public class CuvajUrednikeRe  implements JavaDelegate {

    @Autowired
    CasopisRepository casopisRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public void execute(DelegateExecution execution) throws Exception {


       List<FormSubmissionDto> ljudi = (List<FormSubmissionDto>)execution.getVariable("varijabla");
        List<FormSubmissionDto> magazine = (List<FormSubmissionDto>)execution.getVariable("magazine");


        List<String> urednici = ljudi.get(0).getFieldListValue();
        List<String> recenzenti = ljudi.get(1).getFieldListValue();
        String issn = magazine.get(1).getFieldValue();

        List<User> ured= new ArrayList<>();
        for(String nazivOblasti: urednici){
            if(userRepository.findUserByUsername(nazivOblasti.concat("yahoo.com"))!=null)
                ured.add(userRepository.findUserByUsername(nazivOblasti.concat("yahoo.com")));
        }

        List<User>  rec= new ArrayList<>();
        for(String nazivOblasti: recenzenti){
            if(userRepository.findUserByUsername(nazivOblasti.concat("yahoo.com"))!=null)
                rec.add(userRepository.findUserByUsername(nazivOblasti.concat("yahoo.com")));
        }

        Casopis c = this.casopisRepository.findByIssn(issn);

        c.setReviewers(rec);
        c.setScientificAreasEditors(ured);

        this.casopisRepository.save(c);


    }
}
