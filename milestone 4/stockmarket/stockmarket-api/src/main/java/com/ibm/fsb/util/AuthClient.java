package com.ibm.fsb.util;

import com.ibm.fsb.auth.GenericAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth", path = "/api")
public interface AuthClient {
    @PostMapping("/jwt/{token}")
    GenericAuthResponse verifyJwtToken(@PathVariable String token);
}
