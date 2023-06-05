package com.example.emailtemplate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailPojo {

    private String to;

    private String from;

    private String subject;

    private String body;
}
