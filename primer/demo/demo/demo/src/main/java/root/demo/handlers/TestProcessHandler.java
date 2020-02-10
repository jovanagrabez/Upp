package root.demo.handlers;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.security.TokenUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class TestProcessHandler implements ExecutionListener {
	@Autowired
	IdentityService identityService;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
    TokenUtils tokenUtils;


    @Autowired
    HttpServletRequest httpServletRequest;

	@Override
	public void notify(DelegateExecution execution) throws Exception {

	/*	User user1 = identityService.newUser("jovana@yahoo.com");
		user1.setEmail("jovana@yahoo.com");
		user1.setFirstName("Pera");
		user1.setLastName("Peric");
		user1.setPassword("pass");
		identityService.saveUser(user1);*/

        String username = this.tokenUtils.getUsernameFromToken(this.tokenUtils.getToken(this.httpServletRequest));

        execution.setVariable("inicijator", username);

     /*   User user4 = identityService.newUser("jovana.grabez@gmail.com");
        user4.setEmail("pjovana.grabez@gmail.com");
        user4.setFirstName("Pera");
        user4.setLastName("Peric");
        user4.setPassword("pass");
        identityService.saveUser(user4);

        User user2 = identityService.newUser("grabez@yahoo.com");
        user2.setEmail("grabez@yahoo.com");
        user2.setFirstName("Mika");
        user2.setLastName("Mikic");
        user2.setPassword("pass");
        identityService.saveUser(user2);

        User user3 = identityService.newUser("elena@yahoo.com");
        user3.setEmail("elena@yahoo.com");
        user3.setFirstName("Zika");
        user3.setLastName("Zikic");
        user3.setPassword("pass");
        identityService.saveUser(user3);

        User user5 = identityService.newUser("andjela@yahoo.com");
        user5.setEmail("andjela@yahoo.com");
        user5.setFirstName("Zika");
        user5.setLastName("Zikic");
        user5.setPassword("pass");
        identityService.saveUser(user5);

*/

	/*	System.out.println("Poceo proces");
		List<User> users = identityService.createUserQuery().userIdIn("pera", "mika", "zika").list();
		if(users.isEmpty() ) {
			
			User user1 = identityService.newUser("pera");
			user1.setEmail("pera@mail.com");
			user1.setFirstName("Pera");
			user1.setLastName("Peric");
			user1.setPassword("pass");
			identityService.saveUser(user1);
			
			User user2 = identityService.newUser("mika");
			user2.setEmail("mika@mail.com");
			user2.setFirstName("Mika");
			user2.setLastName("Mikic");
			user2.setPassword("pass");
			identityService.saveUser(user2);
			
			User user3 = identityService.newUser("zika");
			user3.setEmail("zika@mail.com");
			user3.setFirstName("Zika");
			user3.setLastName("Zikic");
			user3.setPassword("pass");
			
			identityService.saveUser(user3);
		}*/
		
	}
}
