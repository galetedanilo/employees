package com.galetedanilo.employee.services;

import com.galetedanilo.employee.entities.Role;
import com.galetedanilo.employee.entities.User;
import com.galetedanilo.employee.repositories.RoleRepository;
import com.galetedanilo.employee.repositories.UserRepository;
import com.galetedanilo.employee.requests.RoleRequest;
import com.galetedanilo.employee.requests.UserRequest;
import com.galetedanilo.employee.responses.UserResponse;
import com.galetedanilo.employee.services.exceptions.DatabaseException;
import com.galetedanilo.employee.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService, Serializable {

    private static final long serialVersionUID = 1l;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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

    @Transactional
    public UserResponse saveNewUser(UserRequest userRequest) {
        User user = new User();

        helperVerifyUserEmailExist(userRequest.getEmail());

        mapperUserRequestToUser(user, userRequest);

        user.setCreatedAt(Instant.now());

        user = userRepository.save(user);

        return new UserResponse(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        try {
            User user = userRepository.getById(id);

            mapperUserRequestToUser(user, userRequest);

            user.setUpdatedAt(Instant.now());

            user = userRepository.save(user);

            return new UserResponse(user);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("User by id " + id + " does not exist");
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        return user;
    }

    private void mapperUserRequestToUser(User user, UserRequest userRequest) {
        user.setUsername(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.getRoleSet().clear();

        for(RoleRequest roleRequest : userRequest.getRoleRequestSet()) {
            Role role = roleRepository.getById(roleRequest.getId());

            user.getRoleSet().add(role);
        }
    }

    private void helperVerifyUserEmailExist(String email) {
        if(userRepository.findByUsername(email).isPresent())
            throw new DatabaseException("User email " + email + " is already exist");
    }
}
