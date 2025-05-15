package com.structure.booking.services.impl;

import com.structure.booking.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseService {
    public Long getUserId(){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return user.getId();
    }
}
