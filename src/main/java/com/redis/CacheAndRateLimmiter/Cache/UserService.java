package com.redis.CacheAndRateLimmiter.Cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;


    public List<User> getAllUsersFromUserRepo(){
        return userRepo.getAllUsers();
    }

    @Cacheable(value = "Users", key = "#id")
    public User getUserByIdFromUserRepo(String id){
        return userRepo.getUserById(id);
    }

}
