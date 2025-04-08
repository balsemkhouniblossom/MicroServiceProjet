package tn.esprit.ms.utilisateurmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = "tn.esprit.ms.utilisateurmanagement")
public class UtilisateurManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilisateurManagementApplication.class, args);
    }



    }