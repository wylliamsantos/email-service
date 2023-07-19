package br.com.taskmanager.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailDTO {

    private String subject;
    private String senderName;
    private String text;
    private List<String> to;
    private List<AttachmentDTO> attachments;

}
