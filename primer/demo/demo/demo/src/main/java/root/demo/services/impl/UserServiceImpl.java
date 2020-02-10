package root.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import root.demo.model.DTO.ActivateRecenzentDTO;
import root.demo.model.DTO.UserDTO;
import root.demo.model.User;
import root.demo.repository.UserRepository;
import root.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {




    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;


    public User getUserById(Long id) {
        User user =  this.userRepository.findUserById(id);
        return user;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        User u = userRepository.findUserByUsername(username);
        return u;
    }

    @Override
    public List<User> getAllRecenzent() {
        List<User> usersAll = this.userRepository.findAll();
        List<User> recenzenti = new ArrayList<User>();
        for(int i =0; i< usersAll.size(); i++){
            recenzenti.add(usersAll.get(i));
        }
        return  recenzenti;
    }

    @Override
    public List<User> getUrednici(){
        List<User> usersAll = this.userRepository.findAll();
        List<User> urednici = new ArrayList<User>();
   /*     for(int i=0; i<usersAll.size();i++){
            for(int j=0; j<usersAll.get(i).getA().size();j++) {
                if (usersAll.get(i).getA().get(j).getName().equals("UREDNIK")) {
                    urednici.add(usersAll.get(i));
                }
            }
        }*/
        return urednici;

    }

    @Override
    public List<User> getRec(){
        List<User> usersAll = this.userRepository.findAll();
        List<User> urednici = new ArrayList<User>();
    /*    for(int i=0; i<usersAll.size();i++){
            for(int j=0; j<usersAll.get(i).getA().size();j++) {
                if (usersAll.get(i).getA().get(j).getName().equals("RECENZENT")) {
                    urednici.add(usersAll.get(i));
                }
            }
        }*/
        return urednici;

    }

    @Override
    public UserDTO activateRecenzent(ActivateRecenzentDTO a) {
return null;
    }


}
