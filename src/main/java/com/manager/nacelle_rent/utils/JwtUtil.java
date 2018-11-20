package com.manager.nacelle_rent.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.manager.nacelle_rent.entity.WebAdmin;

public class JwtUtil {
    public static String getToken(WebAdmin webAdmin) {
        String token;
        token= JWT.create().withAudience(webAdmin.getWebAdminId())
                .sign(Algorithm.HMAC256(webAdmin.getWebAdminPassword()));
        return token;
    }
}
