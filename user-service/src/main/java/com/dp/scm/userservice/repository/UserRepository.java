package com.dp.scm.userservice.repository;

import com.dp.scm.userservice.model.User;
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
public class UserRepository {

    private Map<Long, User> users = new HashMap<>();

    public Optional<User> findById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    public void add(User user) {
        log.info("add users called");
        users.put(user.getId(), user);
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public Page<User> getUsers(Pageable pageable) {
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();
        List<User> result = users.values().stream().skip(toSkip).limit(pageable.getPageSize()).collect(toList());

        return new PageImpl<>(result, pageable, users.size());
    }
}
