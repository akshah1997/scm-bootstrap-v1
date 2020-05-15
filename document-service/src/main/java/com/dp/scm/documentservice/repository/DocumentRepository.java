package com.dp.scm.documentservice.repository;

import com.dp.scm.documentservice.model.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
@Slf4j
public class DocumentRepository {

    private Map<Long, Document> documents = new HashMap<>();

    public Optional<Document> findById(long id) {
        return Optional.ofNullable(documents.get(id));
    }

    public void add(Document document) {
        log.info("add docs called");
        documents.put(document.getId(), document);
    }

    public Collection<Document> getDocuments() {
        return documents.values();
    }

    public Page<Document> getDocuments(Pageable pageable) {
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();
        List<Document> result = documents.values().stream().skip(toSkip).limit(pageable.getPageSize()).collect(toList());

        return new PageImpl<>(result, pageable, documents.size());
    }
}
