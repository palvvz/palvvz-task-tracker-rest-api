package palvvz.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import palvvz.domain.User;

import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
    private final JwtConfig jwtConfig;


    public Jwt generateAccessToken(User user) {
        return generateToken(user,jwtConfig.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user) {
        return generateToken(user,jwtConfig.getRefreshTokenExpiration());
    }

    private Jwt generateToken(User user, long tokenExpiration) {
        var claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();
        var secretKey = jwtConfig.getSecretKey();
        return new Jwt(claims, secretKey);
    }


    public Jwt parseToken(String token) {
        try {
            var claims = getClaims(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        } catch (JwtException e) {
            return null;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

//    public boolean validateToken(String token) {
//
//        try {
//            Claims claims = getClaims(token);
////            Jwts.parser()
////                    .verifyWith()
////
////                    .setSigningKey(jwtConfig.getSecretKey())
////                    .parseClaimsJws(token).getBody();
////            tokenExpired = false;
////            return getClaimsInMap(claims);
////        } catch (ExpiredJwtException ex) {
////            DefaultClaims claims = (DefaultClaims) ex.getClaims();
////            return getClaimsInMap(claims);
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//
//        var claims = Jwts.claims()
//                .subject(user.getId().toString())
//                .add("email", user.getEmail())
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
//                .build();
//        var secretKey = jwtConfig.getSecretKey();
//        var token =  Jwts.builder().claims(claims).signWith(secretKey).compact();
//        return token;
//    }

//    private Claims extractAllClaims(String token,){
//        return Jwts.parser()
//                .verifyWith(getSignInKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
//    private SecretKey getSignInKey(String secretKey) {
//        return Keys.hmacShaKeyFor(secretKey.getBytes());
//
//        MacAlgorithm SECRET_KEY = Jwts.SIG.HS256.key().build();
//        return SECRET_KEY.key().build();
//    }
}
