package com.example.s7testtask.web;

import com.example.s7testtask.model.Role;
import com.example.s7testtask.model.User;
import com.example.s7testtask.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.EnumSet;
import java.util.List;

import static com.example.s7testtask.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AccountController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AccountController.class);
    static final String REST_URL = "/api/account";

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AccountController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get account {}", authUser.id());
        return authUser.getUser();
    }

    @GetMapping("/friends")
    public List<User> getFriends(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get friends for account {}", authUser.id());
        User user = repository.getWithFriends(authUser.id()).orElseThrow();
        return user.getFriends();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        log.info("register {}", user);
        checkNew(user);
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(EnumSet.of(Role.USER));
        User created = repository.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Transactional
    @PatchMapping("/add-or-remove-friend")
    public User addOrRemoveFriend (@RequestParam int id, @AuthenticationPrincipal AuthUser authUser) {
        User user = repository.getWithFriends(authUser.id()).get();
        User friend = repository.findById(id).get();
        log.info(user.getFriends().contains(friend) ? "delete from friends {} for user {}" : "add into friends {} for user {}", friend.id(), user.id());
        user.addOrRemoveFriend(friend);
        return repository.save(user);
    }
}
