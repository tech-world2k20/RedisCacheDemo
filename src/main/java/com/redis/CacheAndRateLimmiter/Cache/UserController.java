package com.redis.CacheAndRateLimmiter.Cache;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/createUser")
    public ResponseEntity<User> createUSer(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
