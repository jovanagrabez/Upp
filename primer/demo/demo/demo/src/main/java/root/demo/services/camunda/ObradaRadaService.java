package root.demo.services.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.*;
import root.demo.model.DTO.AutorDTO;
import root.demo.repository.CasopisRepository;
import root.demo.repository.CoauthorRepositroy;
import root.demo.repository.NaucnaOblastRepository;
import root.demo.repository.RadRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObradaRadaService implements JavaDelegate {
    @Autowired
    private CasopisRepository casopisRepository;

    @Autowired
    private RadRepository radRepository;
    @Autowired
    private CoauthorRepositroy coauthorRepositroy;

    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;
    @Override
    public void execute(DelegateExecution execution) throws Exception {



        List<FormSubmissionDto> rad = (List<FormSubmissionDto>)execution.getVariable("rad");
        String magazineId = (String) execution.getVariable("magazineId");
        List<AutorDTO> autori = (List<AutorDTO>) execution.getVariable("autori");
        List<Coauthor> coauthors = new ArrayList<Coauthor>();
        Casopis casopis = this.casopisRepository.findByCasopisId(Long.parseLong(magazineId));

        for(int i=0; i< autori.size();i++){
            Coauthor c =  new Coauthor();
            c.setFirstname(autori.get(i).getFirstname());
            c.setLastname(autori.get(i).getLastname());
            c.setCity(autori.get(i).getCity());
            c.setEmail(autori.get(i).getEmail());
            c.setState(autori.get(i).getState());
            this.coauthorRepositroy.save(c);
            coauthors.add(c);

        }

        List<String> naziviNaucnihOblasti = rad.get(3).getFieldListValue();
        List<NaucnaOblast> naucneOblasti = new ArrayList<>();

        for(String naziv: naziviNaucnihOblasti){
            System.out.println(naziv + "usao u naziv naucne");
            NaucnaOblast no = this.naucnaOblastRepository.findByName(naziv);
            naucneOblasti.add(no);
        }


        Rad noviRad =  new Rad();
        noviRad.setNaslov(rad.get(0).getFieldValue());
        noviRad.setApstract(rad.get(1).getFieldValue());
        noviRad.setKljucniPojmovi(rad.get(2).getFieldValue());
        noviRad.setFilename(rad.get(4).getFieldValue());
        noviRad.setCasopis(casopis);
        noviRad.setNaucna_oblast(naucneOblasti);
        noviRad.setCena(100.5);
        noviRad.setKoautori(coauthors);
        this.radRepository.save(noviRad);
        casopis.getRadovi().add(noviRad);
        this.casopisRepository.save(casopis);








    }
}
