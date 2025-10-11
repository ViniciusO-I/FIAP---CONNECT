package br.com.fiapconnect.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {

    public static void main(String[] args) {
        // ponto de entrada da aplicacao
        System.out.println("iniciar a aplicacao");
        SpringApplication.run(ChallengeApplication.class, args);
        System.out.println(" aplicacao iniciada");
    }
}
