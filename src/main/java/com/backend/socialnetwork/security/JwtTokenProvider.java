package com.backend.socialnetwork.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${socialnetwork.app.secret}")
    private String APP_SECRET; //Bu uygulama calısırken kullanacağımız özel bir key. Buna göre bir Token oluşturacağız
    @Value("${socialnetwork.expires.in}")
    private Long EXPIRES_IN; //Saniye biçiminden kaç saniye içerisinde bu tokenlar geçerliliğini yitiriyor gibi.

    //token oluşturma
    public String generateJwtToken(Authentication auth){
        JwtUserDetails userDetails=(JwtUserDetails) auth.getPrincipal(); //getPrinciple()--> authenticate edeceğimiz user demek. JwtUserDetails e (kest) ediyoruz.
        Date expireDate=new Date(new Date().getTime() + EXPIRES_IN); //bu satıra kadar user ı elimize alıyoruz sonra ne zaman expire olucağını buluyoruz.
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512,APP_SECRET).compact();
    } //user aldık expire aldık hazır metodlar kullanarak objeyi user id sini, şuan oluşturuldu şu zamanda expire olucak dedim bu algoritma biçimine göre olucak dedim.

    //yukarıda oluşturmuş olduğumuz tokendan user id yi geri almak için aşağıya suan bu metodu yazıyoruz. Keyden(APP_SECRET) userId yi extract edicez yani.
    Long getUserIdFromJwt(String token){
        Claims claims=Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody(); //tamamen geri gidiyoruz gibi az önce token olusturudk ve APP_SECRET e gomduk simdi o gömdüğümüz userId yi exract ediyoruz.
        return Long.parseLong(claims.getSubject()); //key i çözdük içerisinden userId yi aldık.
    } //ıd neden alıyoz
    //ilk metotda oluşturduğumuz token ı validate ediyoruz biz oluşturduk mu vs kontrollerini yapıyoruz.
    boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token); //parse edebiliyosak APP_SECRET ı kullanarak bu bizim uygulamamızın oluşturduğu bir keydir kontrolü yapıyoruz
            return !isTokenExpired(token); //expired olduysa false dönsün expired olmaıdysa true donsun
        }catch (SignatureException e){
            return false;
        }catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }catch (UnsupportedJwtException e){
            return false;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }


}
