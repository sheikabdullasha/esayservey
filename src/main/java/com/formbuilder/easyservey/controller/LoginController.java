package com.formbuilder.easyservey.controller;
import com.formbuilder.easyservey.Constant.RoleConstant;
import com.formbuilder.easyservey.entity.AuthRequest;
import com.formbuilder.easyservey.entity.User;
import com.formbuilder.easyservey.repo.IUserRepository;
import com.formbuilder.easyservey.service.SequenceGeneratorservice;
import com.formbuilder.easyservey.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/auth")
public class LoginController {

    private final JwtUtil jwtUtil;

    private  final AuthenticationManager authenticationManager;

    private final IUserRepository userRepository;

    private final SequenceGeneratorservice generatorservice;
    private final PasswordEncoder passwordEncoder;



    @RequestMapping("/home")
    public String home() {
        return "hello !!!!!";
    }


@PostMapping("/authenticate")
public String generateJwtToken(@RequestBody AuthRequest authRequest) throws Exception {
    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getMailId(), authRequest.getPassword())
        );
    } catch (Exception ex) {
        throw new Exception("invalid MailId/password",ex);
    }
    log.error("=========================================");
    return jwtUtil.generateToken(authRequest.getMailId());
}
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user){
        Optional<User> usr=userRepository.findByEmail1(user.getMailId());

        if(!usr.isPresent()){
            user.setUId(generatorservice.getSequenceNumber(User.SEQUENCE_NAME));
            user.setType(RoleConstant.User.getRole());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<String>("Registered", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("user Already Exist", HttpStatus.CREATED);
    }
}
