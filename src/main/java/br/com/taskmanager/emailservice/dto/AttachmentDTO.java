package br.com.taskmanager.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "base64")
public class AttachmentDTO {

    private String filename;
    private String base64;
    private String extension;

}