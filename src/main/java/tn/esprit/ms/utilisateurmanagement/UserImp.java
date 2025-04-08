package tn.esprit.ms.utilisateurmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(User user) {
        User newUser = new User(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword())
        );
        userRepository.save(newUser); // Fixed method call to save the user
        return newUser.getNom();
    }

    @Override
    public LoginMessage loginEmployee(LoginDTO loginDTO) {
        User employee = userRepository.findByEmail(loginDTO.getEmail());
        if (employee != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                return new LoginMessage("Login Success", true); // Fixed typo in LoginMessage
            } else {
                return new LoginMessage("Password Not Match", false); // Corrected message
            }
        } else {
            return new LoginMessage("Email does not exist", false); // Corrected message
        }
    }
}