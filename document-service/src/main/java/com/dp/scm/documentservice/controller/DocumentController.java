package com.dp.scm.documentservice.controller;

import com.dp.scm.documentservice.exception.DocumentNotFoundException;
import com.dp.scm.documentservice.model.Document;
import com.dp.scm.documentservice.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
@RequestMapping("/api/document")
@Slf4j
public class DocumentController {

    @Autowired
    private DocumentRepository repository;

    @GetMapping("/{id}")
    public Document findById(@PathVariable long id) {
        log.info("get document called");
        return repository.findById(id)
                .orElseThrow(DocumentNotFoundException::new);
    }

    @GetMapping("/")
    public Collection<Document> findDocuments() {
        log.info("get all documents called");
        return repository.getDocuments();
    }

    @GetMapping("/filter")
    public Page<Document> filterDocuments(Pageable pageable) {
        return repository.getDocuments(pageable);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Document updateDocument(@PathVariable("id") final String id, @RequestBody final Document document) {
        return document;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Document patchDocument(@PathVariable("id") final String id, @RequestBody final Document document) {
        return document;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Document postDocument(@NotNull @Valid @RequestBody final Document document) {
        log.info("post document called");
        repository.add(document);
        return document;
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Document headDocument() {
        return new Document();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public long deleteDocument(@PathVariable final long id) {
        return id;
    }
}

