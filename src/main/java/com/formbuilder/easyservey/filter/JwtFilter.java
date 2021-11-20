package com.formbuilder.easyservey.filter;



import com.formbuilder.easyservey.entity.Form;
import com.formbuilder.easyservey.entity.User;
import com.formbuilder.easyservey.repo.IUserRepository;
import com.formbuilder.easyservey.repo.QuestionsRepository;
import com.formbuilder.easyservey.service.CustomUserDetailService;
import com.formbuilder.easyservey.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;

    private final CustomUserDetailService service;

    private final IUserRepository userRepository;

    private final QuestionsRepository questionsRepository;



    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
     
        String[] arrOfStr=httpServletRequest.getServletPath().split("/");





        String url=httpServletRequest.getRequestURI();
        String[] s=url.split("/");

        String path="";

        for(int i=0;i<s.length-1;i++) {
            path+=s[i]+"/";
        }

        System.out.println(path);



        List<String> valdiatePathForAdmin=new ArrayList<>();
        valdiatePathForAdmin.add("/users/getAllUserResponse/");

        //int formId=httpServletRequest.getIntHeader("fId");





        String token = null;
        String email = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            email = jwtUtil.extractUsername(token);
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = service.loadUserByUsername(email);

            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        /***************************************/

        if(valdiatePathForAdmin.contains(path)){
            int formId=Integer.parseInt(arrOfStr[arrOfStr.length-1]);

            if(formId>0){

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUserName =null;
                if (!(authentication instanceof AnonymousAuthenticationToken)) {
                    currentUserName = authentication.getName();

                }
                Optional<User> usr=userRepository.findByEmail1(currentUserName);
                if(usr.isPresent()){

                    Optional<Form> fm=questionsRepository.findById(formId);

                    if(fm.get().getCId()!=usr.get().getUId()){

                        throw new SecurityException("Forbidden you don't have access");
                    }


                }
            }


        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
