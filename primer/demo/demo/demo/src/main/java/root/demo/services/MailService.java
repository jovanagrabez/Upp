package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import root.demo.model.FormSubmissionDto;
import root.demo.model.User;
import root.demo.repository.UserRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
public class MailService implements JavaDelegate {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        System.out.println("Sending email...");
        List<FormSubmissionDto> registration = (List<FormSubmissionDto>) execution.getVariable("registration");
        String email = registration.get(4).getFieldValue();

        User u = this.userRepository.findUserByUsername(registration.get(6).getFieldValue());

        send("koviljka.grabez","dalibor72", email,"Potvrda ragistracije","Da bi potvrdili registraciju kliknite na link ispod: \n http://localhost:4200/register/verify/".concat(u.getId().toString()) + "/".concat(execution.getProcessInstanceId()) );


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
