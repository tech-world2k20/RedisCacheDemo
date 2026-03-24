package com.redis.CacheAndRateLimmiter.Cache;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class UserRepo {

    private static final List<User> users = new ArrayList<>();
    static {
        for (int i = 1; i <= 50; i++) {
            users.add(new User(
                    String.format("U%03d", i),
                    "User" + i,
                    20 + (i % 10),
                    30000.0 + (i * 1000)
            ));
        }
    }

    public List<User> getAllUsers(){
//        mimic db call
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  users;
    }

    public User getUserById(String id) {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("getting from db");
        return users.stream()
                .filter(s -> s.id().equals(id))
                .findFirst().orElse(null);
    }

    public User createUser(User user) {
        User userById = getUserById(user.id());
        if(userById != null) throw new RuntimeException ("User already exists");

        users.add(user);
        return user;
    }


    public User deleteUser(String id) {
        User userById = getUserById(id);
        if(userById == null) throw new RuntimeException ("User does not exists");

        boolean remove = users.remove(userById);
        return userById;
    }

    public User updateUser(User user) {
        User userById = getUserById(user.id());
        if(userById != null) throw new RuntimeException ("User already exists");

        users.add(user);

        return user;
    }
}
