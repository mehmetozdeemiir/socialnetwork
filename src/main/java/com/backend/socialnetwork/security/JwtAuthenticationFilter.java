package com.backend.socialnetwork.security;


import com.backend.socialnetwork.business.Concretes.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//filter classı

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //bir request geldiğinde spring securinin yaptıgı cok filter var fakat birtanede biz ekliyoruz. jwt kontrol filter aşamaı ekliyoruz autorize oldumu diye

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwtToken= extractJwtFromRequest(request);
            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) //bu token doluysa ve validse bu token içerisinden authenticate ediceğimiz user ı alıcaz.
            {
                Long id =jwtTokenProvider.getUserIdFromJwt(jwtToken);
                UserDetails user=userDetailsService.loadUserById(id);
                if(user!=null){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }catch (Exception e){
            return;
        }
        filterChain.doFilter(request,response);
    }

    private String extractJwtFromRequest(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if(StringUtils.hasText(bearer)&& bearer.startsWith("Bearer "))
            return bearer.substring("Bearer".length()+1);
        return null;
    }

}
