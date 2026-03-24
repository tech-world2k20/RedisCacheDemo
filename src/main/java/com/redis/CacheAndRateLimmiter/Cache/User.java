package com.redis.CacheAndRateLimmiter.Cache;

import java.io.Serializable;

public record User(String id, String name, int age, Double salary) implements Serializable {}