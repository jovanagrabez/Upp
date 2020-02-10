package root.demo.services.camunda;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.Casopis;
import root.demo.model.User;
import root.demo.repository.CasopisRepository;
import root.demo.repository.UserRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SlanjeRadService implements JavaDelegate {

    @Autowired
    private CasopisRepository casopisRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

    String magazineId = (String) execution.getVariable("magazineId");
        String email = (String) execution.getVariable("inicijator");

        System.out.println("USAOO U MAIL" + email);
        Casopis casopis = this.casopisRepository.findByCasopisId(Long.parseLong(magazineId));
        User glavniUrednik = this.userRepository.findUserById(casopis.getMainEditor().getId());


        execution.setVariable("glavniUrednik", glavniUrednik.getEmail());
        send("koviljka.grabez","dalibor72", glavniUrednik.getEmail(),"Potvrda ragistracije","Da bi potvrdili registraciju kliknite na link ispod: \n http://localhost:4200/register/verify/" );
        send("koviljka.grabez","dalibor72", "stanagrabez75@gmail.com","Potvrda ragistracije","Da bi potvrdili registraciju kliknite na link ispod: \n http://localhost:4200/register/verify/" );

//TODO NEKAKO IZVCUI autora i poslati mu mail


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
