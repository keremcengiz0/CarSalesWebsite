package com.keremcengiz0.CarSalesProject.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${carsalesproject.app.secret}")
    private String APP_SECRET;

    @Value("${carsalesproject.expires.in}")
    private Long EXPIRES_IN;


    //Jwt Tokeni oluşturur.
    public String generateJwtToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();       //Authenticate edilecek user.
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);           //Tokenin süresinin dolacağı zamanı belirtir.

        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))     //setSubject -> user'i belirtir.
                .setIssuedAt(new Date()).setExpiration(expireDate)               //Token'in ne zaman oluşacağını ve bitiş zamanını belirtir.
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();       //Token'in hangi algoritma ile oluşturulacağını belirtir.
    }

    public Long getUserIdFromJwt(String token) {                                 //Token'dan kullanıcı ıd'sini bulmak için yazılan metot. Şifreyi çözer ve user id'sini geri döndürür.
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    boolean validateToken(String token) {                                        //Token'in doğruluğunu kontrol eder.
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);       // Pars edebiliyorsak bizim oluşturduğumuz tokendir.
            return !isTokenExpired(token);
        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    //Token'in süresinin dolup dolmadığını kontrol eder.
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    public String generateJwtTokenByUserId(Long userId) {
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(userId))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }


}
