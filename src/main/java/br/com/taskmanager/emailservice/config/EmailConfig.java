package br.com.taskmanager.emailservice.config;

import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Autowired
    private Environment env;

    @Bean
    public Session session() {
        // Configurações do servidor SMTP do MailHog
        String host = env.getProperty("mail.host");
        String port = env.getProperty("mail.port");

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        return Session.getDefaultInstance(props);
    }

}
