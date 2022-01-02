package com.galetedanilo.employee.components;

import com.galetedanilo.employee.entities.User;
import com.galetedanilo.employee.repositories.UserRepository;
import com.galetedanilo.employee.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@AllArgsConstructor
public class JwtTokenEnhancer implements TokenEnhancer {

    private final UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Optional<User> userOptional = userRepository.findByUsername(oAuth2Authentication.getName());

        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User by email " +
                oAuth2Authentication.getName() + " does not exist"));

        Map<String, Object> map = new HashMap<>();

        map.put("userId", user.getId());

        DefaultOAuth2AccessToken accessToken = (DefaultOAuth2AccessToken) oAuth2AccessToken;

        accessToken.setAdditionalInformation(map);

        return accessToken;
    }
}
