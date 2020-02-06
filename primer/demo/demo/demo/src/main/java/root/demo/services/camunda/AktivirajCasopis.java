package root.demo.services.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.Casopis;
import root.demo.model.FormSubmissionDto;
import root.demo.repository.CasopisRepository;

import java.util.List;

@Service
public class AktivirajCasopis implements JavaDelegate {

    @Autowired
    CasopisRepository casopisRepository;
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        List<FormSubmissionDto> magazine = (List<FormSubmissionDto>)execution.getVariable("magazine");
        String issn = magazine.get(1).getFieldValue();

        Casopis c = this.casopisRepository.findByIssn(issn);

        c.setActiveStatus(true);
        this.casopisRepository.save(c);






    }
}
