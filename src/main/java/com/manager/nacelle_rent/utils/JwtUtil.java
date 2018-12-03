package com.manager.nacelle_rent.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.manager.nacelle_rent.entity.User;

public class JwtUtil {
    public static String getToken(User user) {
        String token;
        token= JWT.create().withAudience(user.getUserId())
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }
}
