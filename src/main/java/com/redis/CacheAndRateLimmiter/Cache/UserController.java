package com.redis.CacheAndRateLimmiter.Cache;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsersFromUserRepo());
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserByIdFromUserRepo(id));
    }
}
