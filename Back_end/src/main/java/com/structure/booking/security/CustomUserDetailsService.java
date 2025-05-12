package com.structure.booking.security;

import com.structure.booking.config.ResourceBundleConfig;
import com.structure.booking.domain.entities.User;
import com.structure.booking.exception.BadRequestException;
import com.structure.booking.exception.ResourceNotFoundException;
import com.structure.booking.repository.UserRepository;
import com.structure.booking.utils.MessageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResourceBundleConfig resourceBundleConfig;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phone)
            throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new UsernameNotFoundException(this.resourceBundleConfig.getViMessage(MessageUtils.PHONE_DONT_EXISTS, phone))
                );
        if (Boolean.TRUE.equals(user.getIsLocked()))
        {
            throw new BadRequestException(this.resourceBundleConfig.getViMessage(MessageUtils.ACCOUNT_BLOCKED));
        }
        if (Boolean.FALSE.equals(user.getIsActive())) {
            throw new BadRequestException(this.resourceBundleConfig.getViMessage(MessageUtils.ACCOUNT_NOT_ACTIVE));
        }

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BadRequestException(this.resourceBundleConfig.getViMessage(MessageUtils.ACCOUNT_NOT_FOUND, id))
        );

        return UserPrincipal.create(user);
    }
}