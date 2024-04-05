package com.dave.books.bookstore.Bookstore.BookstoreControllers;

import org.springframework.web.bind.annotation.RestController;

import com.dave.books.bookstore.DataAccessObject.BookDao;
import com.dave.books.bookstore.DataTransferObject.AuthenticationRequestDto;
import com.dave.books.bookstore.DataTransferObject.AuthenticationResponseDto;
import com.dave.books.bookstore.SecurityConfig.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    BookDao bookdao;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> createAuthenticationToken(
            @RequestBody AuthenticationRequestDto authenticationRequest) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("incorrect username or password", e);
        }
        final UserDetails user = bookdao.findUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
}
