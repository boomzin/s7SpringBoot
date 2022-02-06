package com.example.s7testtask.web;

import com.example.s7testtask.model.Gender;
import com.example.s7testtask.model.Status;
import com.example.s7testtask.model.User;
import com.example.s7testtask.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = UserController.REST_URL)
public class UserController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
    static final String REST_URL = "/api/users";

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        log.info("get user {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/{id}/with-friends")
    public ResponseEntity<User> getWithFriends(@PathVariable int id) {
        log.info("get id {} with friends", id);
        return ResponseEntity.of(repository.getWithFriends(id));
    }

    @GetMapping("/search")
    public List<User> searchWithCriteria(@RequestParam(required = false) Optional<String> email,
                                         @RequestParam(required = false) Optional<String> name,
                                         @RequestParam(required = false) Optional<String> age,
                                         @RequestParam(required = false) Optional<Boolean> hasPhoto,
                                         @RequestParam(required = false) Gender gender,
                                         @RequestParam(required = false) Status status
    ) {
        log.info("search users");
        UserSearchCriteria userSearch = new UserSearchCriteria();
        email.ifPresent(string -> createSearchCriteria(userSearch, "email", string));
        name.ifPresent(string -> createSearchCriteria(userSearch, "name", string));
        age.ifPresent(string -> createSearchCriteria(userSearch, "age", string));
        hasPhoto.ifPresent(aBoolean -> userSearch.add(new SearchCriteria("hasPhoto", ":", aBoolean)));
        if (!(gender == null)) {
            userSearch.add(new SearchCriteria("gender", ":", gender));
        }
        if (!(status == null)) {
            userSearch.add(new SearchCriteria("status", ":", status));
        }
        return repository.findAll(userSearch);
    }

    private void createSearchCriteria(UserSearchCriteria userSearch, String param, String search) {
        Pattern pattern = Pattern.compile("([<>~])?(.+)");
        Matcher matcher = pattern.matcher(search);
        matcher.find();
        if (matcher.group(1) == null) {
            userSearch.add(new SearchCriteria(param, ":", matcher.group(2)));
        } else {
            userSearch.add(new SearchCriteria(param, matcher.group(1), matcher.group(2)));
        }
    }
}
