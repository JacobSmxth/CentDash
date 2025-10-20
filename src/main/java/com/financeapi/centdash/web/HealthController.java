package com.financeapi.centdash.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public HashMap<String, Object> getHealth() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "ok");
        return map;
    }
}
