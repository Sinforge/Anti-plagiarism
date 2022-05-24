package ru.sinforge.antiplagiarism.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.sinforge.antiplagiarism.domain.Role;
import ru.sinforge.antiplagiarism.domain.User;
import ru.sinforge.antiplagiarism.repos.UserRep;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRep userRep;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRep.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRep.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRep.save(user);

        if(!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
              "Hello, %s! \n" +
                      "Welcome to Anti-plagiarism. Please, visit next link: http://localhost:8080/activate/%s",
                   user.getUsername(),
                   user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRep.findByActivationCode(code);
        if(user == null) {
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);

        userRep.save(user);

        return true;
    }
}
