package root.demo.services.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.Casopis;
import root.demo.model.NaucnaOblast;
import root.demo.model.User;
import root.demo.repository.CasopisRepository;
import root.demo.repository.UserRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.Properties;

@Service
public class PublishAddChoosenEditor implements JavaDelegate{

	@Autowired
    private CasopisRepository magazineRepository;

	@Autowired
	private UserRepository userRepository;
	

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// OK
		String choosenEditor = "";

        String naucnaOblast = (String) execution.getVariable("nsucnaOblast");
        String magazine = (String) execution.getVariable("magazineId");
        Casopis m = this.magazineRepository.findByCasopisId(Long.parseLong(magazine));
    	System.out.println(m.getNaziv() + " naziv magazinaaaa, a naucna oblast je: "+naucnaOblast);

    	Collection<User> editors = this.userRepository.findAll();
    	for (User editor : editors) {
        	System.out.println(editor.getUsername() + " naziv editora"+ " naucne oblasti editora: ");
        	for(NaucnaOblast sf : editor.getNaucna_oblast()) {
        		System.out.println(sf.getName());
        		String sfn = sf.getName();
        		if(sfn.equals(naucnaOblast)) {
        			choosenEditor = editor.getUsername(); //znaci ima editora tog casopisa za tu naucnu oblast
            		System.out.println("Postoji urednik za tu naucnu oblast i to je: "+choosenEditor);
        		}
        	}
		}
    	if(choosenEditor.equals("")) {
    		choosenEditor = (String) execution.getVariable("glavniUrednik");
    		System.out.println("Nema urednika za tu naucnu oblas dodeljuje se glavni urednik: "+choosenEditor);
    	}

    	execution.setVariable("choosenEditor", choosenEditor);
		send("koviljka.grabez","dalibor72", choosenEditor,"Dodjela editora","editorii" );

		System.out.println("dodeljujem urednika za odabir recenzenata "+choosenEditor);
	}

	public void send(String from,String password,String to,String sub,String msg){
		//Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		//get Session
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from,password);
					}
				});
		//compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			//send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {throw new RuntimeException(e);}
	}

}
