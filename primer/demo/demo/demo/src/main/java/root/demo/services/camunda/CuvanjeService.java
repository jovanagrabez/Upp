package root.demo.services.camunda;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.Casopis;
import root.demo.model.FormSubmissionDto;
import root.demo.model.NaucnaOblast;
import root.demo.repository.CasopisRepository;
import root.demo.repository.NaucnaOblastRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuvanjeService  implements JavaDelegate {

    @Autowired
    NaucnaOblastRepository naucnaOblastRepository;

    @Autowired
    CasopisRepository casopisRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {


        List<FormSubmissionDto> magazine = (List<FormSubmissionDto>)execution.getVariable("magazine");

        String naziv = magazine.get(0).getFieldValue();
        String issn = magazine.get(1).getFieldValue();
        String cijena = magazine.get(2).getFieldValue();
        List<String> naucna_oblast = magazine.get(3).getFieldListValue();



        Casopis casopis = new Casopis(naziv,issn,(double)15);
        List<NaucnaOblast> naucnaOblast = new ArrayList<>();
        for(String nazivOblasti: naucna_oblast){
            if(naucnaOblastRepository.findByName(nazivOblasti)!=null)
                naucnaOblast.add(naucnaOblastRepository.findByName(nazivOblasti));
        }
        casopis.setActiveStatus(false);
         casopis.setNaucna_oblast(naucnaOblast);

        this.casopisRepository.save(casopis);




    }
}
