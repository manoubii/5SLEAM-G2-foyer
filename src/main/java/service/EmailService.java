package service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // Méthode pour envoyer un email
    public void sendEmail(String to, String subject, String body) {
        // Logique pour envoyer l'email (vous pouvez utiliser une bibliothèque comme JavaMail)
        System.out.println("Email envoyé à : " + to);
        System.out.println("Sujet : " + subject);
        System.out.println("Corps : " + body);
    }
}

