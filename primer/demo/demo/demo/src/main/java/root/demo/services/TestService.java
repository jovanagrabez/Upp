package root.demo.services;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.FormSubmissionDto;
import root.demo.model.NaucnaOblast;
import root.demo.repository.NaucnaOblastRepository;
import root.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService implements JavaDelegate{

	@Autowired
	IdentityService identityService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	NaucnaOblastRepository naucnaOblastRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	//	boolean b = true;


	      List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");


		String name = registration.get(0).getFieldValue();
		String lastName = registration.get(1).getFieldValue();
		String city = registration.get(2).getFieldValue();
		String titula = registration.get(3).getFieldValue();
		String email = registration.get(4).getFieldValue();
		String recenzent = registration.get(5).getFieldValue();
		String username = registration.get(6).getFieldValue();
		String password = registration.get(7).getFieldValue();
		String state = registration.get(8).getFieldValue();
		List<String> sciFields = registration.get(9).getFieldListValue();



		boolean rev = false;
		String sciFieldsToString = "";

        if(recenzent.equals("true")){
        	rev = true;
		}
	      User user = identityService.newUser("");
		root.demo.model.User modelUser = new root.demo.model.User(email,password,name,lastName,city,state,username,rev);
//provjeri da li postoji korisnik sa ovakvim usernameom

		List<NaucnaOblast> naucnaOblast = new ArrayList<>();
		for(String nazivOblasti: sciFields){
			if(naucnaOblastRepository.findByName(nazivOblasti)!=null)
				naucnaOblast.add(naucnaOblastRepository.findByName(nazivOblasti));
		}
		modelUser.setNaucna_oblast(naucnaOblast);

	/*	if(userRepository.findUserByUsername(username) == null) {
			b=true;*/
			this.userRepository.save(modelUser);
			user.setId(name);
			user.setPassword(password);

			identityService.saveUser(user);

	/*	}else{

			b=false;
		}

		execution.setVariable("validan", b);*/
	}
}
