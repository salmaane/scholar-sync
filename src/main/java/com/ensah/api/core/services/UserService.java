package com.ensah.api.core.services;

import com.ensah.api.core.dao.UserDAO;
import com.ensah.api.core.models.Subject;
import com.ensah.api.core.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends GenericServiceImpl<User> {

    private final UserDAO dao;

    public UserService(UserDAO dao) {
        super(dao);
        this.dao = dao;
    }

    public Optional<User> findByEmail(String username) {
        return dao.findUserByEmail(username);
    }

    @Override
    public User update(Long id, User newUser) {
        return null;
    }
}
