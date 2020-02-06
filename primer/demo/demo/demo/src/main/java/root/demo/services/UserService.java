package root.demo.services;

import org.springframework.stereotype.Service;
import root.demo.model.DTO.ActivateRecenzentDTO;
import root.demo.model.DTO.UserDTO;
import root.demo.model.User;

import java.util.List;


@Service
public interface UserService {

    User findUserByUsername(String username);
    List<User> getAllRecenzent();
    List<User> getUrednici();
    List<User> getRec();
    UserDTO activateRecenzent(ActivateRecenzentDTO a);

}
