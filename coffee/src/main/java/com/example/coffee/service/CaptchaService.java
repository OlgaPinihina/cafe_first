package com.example.coffee.service;

import com.example.coffee.config.CaptchaSettings;
import com.example.coffee.entity.GoogleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class CaptchaService {

    @Autowired
    //@Resource
    private CaptchaSettings captchaSettings;

    public GoogleResponse processResponse(String response, String ip) {
        RestTemplate restTemplate = new RestTemplate();
        URI verifyUri = URI.create(
                String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                        captchaSettings.getSecret(),
                        response,
                        ip)
        );
        return restTemplate.getForObject(verifyUri, GoogleResponse.class);

    }
}


