package org.threeDPortfolioGallery;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class JwtService {
    public String generateJwt(){
        return Jwt.issuer("user-jwt")
                .subject("user-jwt")
                .groups(Set.of("user", "admin"))
                .expiresAt(
                        System.currentTimeMillis() + 3600
                ).sign();
    }
}
