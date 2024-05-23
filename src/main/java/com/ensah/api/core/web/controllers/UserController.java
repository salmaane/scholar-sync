package com.ensah.api.core.web.controllers;

import com.ensah.api.core.dto.UserDTO;
import com.ensah.api.core.models.User;
import com.ensah.api.core.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        return users.stream().map(UserDTO::toDTO).toList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(value -> ResponseEntity.ok(UserDTO.toDTO(value)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping("profile")
    public ResponseEntity<UserDTO> profile(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Optional<User> user = userService.findByEmail(username);

        return user.map(value -> ResponseEntity.ok(UserDTO.toDTO(value)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
