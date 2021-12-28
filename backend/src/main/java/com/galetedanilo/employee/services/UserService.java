package com.galetedanilo.employee.services;

import com.galetedanilo.employee.entities.User;
import com.galetedanilo.employee.repositories.UserRepository;
import com.galetedanilo.employee.responses.UserResponse;
import com.galetedanilo.employee.services.exceptions.DatabaseException;
import com.galetedanilo.employee.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements Serializable {

    private static final long serialVersionUID = 1l;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserResponse> findAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map((user -> new UserResponse(user)));
    }

    @Transactional(readOnly = true)
    public UserResponse findUserByPrimaryKey(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User by id " + id + " does not exist"));

        return new UserResponse(user);
    }

    public void deleteUserByPrimaryKey(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Integrity violation");
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("User by id " + id + " not found");
        }
    }
}
