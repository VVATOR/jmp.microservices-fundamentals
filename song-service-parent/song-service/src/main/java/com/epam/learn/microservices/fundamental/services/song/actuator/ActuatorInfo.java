package com.epam.learn.microservices.fundamental.services.song.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActuatorInfo implements InfoContributor {
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void contribute(final Info.Builder builder) {
        final Map<String, String> serviceInfo = new HashMap<>();
        serviceInfo.put("name", applicationName);
        builder.withDetail("service", serviceInfo);
    }
}