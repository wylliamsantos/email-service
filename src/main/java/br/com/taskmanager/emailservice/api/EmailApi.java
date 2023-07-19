package br.com.taskmanager.emailservice.api;


import br.com.taskmanager.emailservice.dto.EmailDTO;
import br.com.taskmanager.emailservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/email")
@Slf4j
public class EmailApi {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity send(@RequestBody EmailDTO email){
        log.info("Email recebido=[{}]", email.toString());
        emailService.send(email);
        return ResponseEntity.noContent().build();
    }

}