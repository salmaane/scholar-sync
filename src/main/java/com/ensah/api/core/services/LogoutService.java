package com.ensah.api.core.services;

import com.ensah.api.core.dao.TokenDAO;
import com.ensah.api.core.models.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenDAO tokenDAO;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);
        Token storedToken = tokenDAO.findByToken(token).orElse(null);
        if(storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenDAO.save(storedToken);
        }
    }
}
