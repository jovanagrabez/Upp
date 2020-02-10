package root.demo.services.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.repository.CasopisRepository;

@Service
public class PlacanjeCasopisa implements JavaDelegate {
    @Autowired
    private CasopisRepository casopisRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Long id =(Long) execution.getVariable("magazineId");

  /*      Casopis casopis =this.casopisRepository.findByCasopisId(id);

        if(casopis.getPaymentMethod().equals(PaymentMethod.OPEN_ACCESS)){
            execution.setVariable("autoriPlacaju", false);

        }else*/
            execution.setVariable("autoriPlacaju", false);


    }
}
