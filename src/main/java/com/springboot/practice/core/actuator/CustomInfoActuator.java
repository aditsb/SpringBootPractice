package com.springboot.practice.core.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoActuator implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        boolean error=false;
        if(error==true) {
            builder.withDetail("Error Key", 123).build();
        }
        builder.withDetail("Not Error Key", 123).build();

    }
}
