package br.com.taskmanager.emailservice.service;

import br.com.taskmanager.emailservice.dto.AttachmentDTO;
import br.com.taskmanager.emailservice.dto.EmailDTO;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private Session session;

    public void send(EmailDTO email) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getSenderName()));

            for (String emailAddres: email.getTo()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddres));
            }

            message.setSubject(email.getSubject());

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(email.getText(), "UTF-8", "html");

            Multipart multipart = new MimeMultipart();
            if (!Objects.isNull(email.getAttachments()) && !email.getAttachments().isEmpty()) {
                for (AttachmentDTO attachment : email.getAttachments()) {
                    multipart.addBodyPart(buildAttachedFile(attachment));
                }

            }
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);

            Transport.send(message);

            log.info("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private MimeBodyPart buildAttachedFile(AttachmentDTO attachment) throws MessagingException {
        log.info("Anexando arquivos");
        MimeBodyPart fileAttached = new PreencodedMimeBodyPart("base64");
        String fileName = attachment.getFilename() == null ? "Arquivo" : attachment.getFilename();
        String extension = attachment.getExtension().toLowerCase();
        fileName = String.format("%s.%s", fileName, extension);

        fileAttached.setContent(attachment.getBase64(), "application/octet-stream");
        fileAttached.setFileName(fileName);
        return fileAttached;
    }

}
