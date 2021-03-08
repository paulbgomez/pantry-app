package com.pantry.app.pantry.microserver.pantrymicroserver.clients;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("user/{id}")
    UserDTO getUserById(@PathVariable Long id);

}