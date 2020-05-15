package com.dp.scm.documentservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Document {

    private long id;

    @NotBlank
    @Size(min = 1, max = 200)
    private String fileName;
    private String fileExtension;
    private String fileSize;
    private Date lastModifiedDate;
    private Date lastOpenedDate;
    private String fileLocation;
    private List<String> fileTags;
}
