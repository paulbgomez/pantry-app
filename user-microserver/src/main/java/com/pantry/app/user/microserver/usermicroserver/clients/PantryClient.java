package com.pantry.app.user.microserver.usermicroserver.clients;

import com.pantry.app.user.microserver.usermicroserver.dto.PantryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("pantry-service")
public interface PantryClient {

    @PostMapping("pantry/{id}")
    PantryDTO add(@RequestBody PantryDTO pantryDTO, @PathVariable Long id);

}
