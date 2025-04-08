package tn.esprit.ms.utilisateurmanagement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UtilisateurManagement")

public class UserRestAPI {
    private String title="Hello, i'm the candidate Micro-Service";

    @RequestMapping("/hello") public String sayHello(){
        System.out.println(title); return title;
    }
}
