package tn.esprit.ms.utilisateurmanagement;

public interface UserService {

    String addEmployee(User employeeDTO);
    LoginMessage loginEmployee(LoginDTO loginDTO);
}
