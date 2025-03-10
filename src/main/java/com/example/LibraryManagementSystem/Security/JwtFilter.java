package com.example.LibraryManagementSystem.Security;

import com.example.LibraryManagementSystem.DAO.UserRepository;
import com.example.LibraryManagementSystem.Model.User;
import com.example.LibraryManagementSystem.Service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ApplicationContext applicationContext;
    private final UserRepository userRepository;

    public JwtFilter(JwtService jwtService, ApplicationContext applicationContext, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Extracting authorization header
        String header=request.getHeader("Authorization");
        String username=null;
        String token=null;
        //Checking if header is not null and header is a valid token
        if(header==null || !header.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;

        }
        token=header.substring(7);
        username=jwtService.extractUserName(token);

        //Checking if already authentication is not done
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
           // UserDetails userDetails=applicationContext.getBean(CustomUserDetailService.class)
            //                        .loadUserByUsername(username);
            var userDetails=this.userRepository.findByUsername(username).orElseThrow();
            List<SimpleGrantedAuthority> roles=userDetails.getRoles().stream()
                    .map(SimpleGrantedAuthority::new).toList();
            //validate the token
            if(jwtService.isValidToken(token,userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,roles);
                //Set Authentication details
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            filterChain.doFilter(request,response);
        }

    }
}
