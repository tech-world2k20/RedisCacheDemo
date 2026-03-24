package com.redis.CacheAndRateLimmiter.Cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final CacheManager cacheManager;


    public List<User> getAllUsersFromUserRepo(){
        return userRepo.getAllUsers();
    }

    @Cacheable(value = "Users", key = "#id", unless = "#result.age <25")
    public User getUserByIdFromUserRepo(String id){
        return userRepo.getUserById(id);
    }


    @CachePut(value = "Users", key = "#user.id()")
    public User createUser(User user){

//        this is a mannual way of putting any object in cache which is also done by @CachePut
//        Cache usersCache = cacheManager.getCache("Users");
//        usersCache.putIfAbsent(user.id(), user);
        return userRepo.createUser(user);
    }


    @CacheEvict(value = "Users", key = "#id")
    public User deleteUser(String id){
        return userRepo.deleteUser(id);
    }

    @CachePut(value = "Users", key = "#user.id()")
    public User updateUser(User user) {
        return userRepo.updateUser(user);
    }
}
