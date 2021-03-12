package com.seattleu.imageprocclientapp.config;

import com.seattleu.imageprocessor.client.api.ImageProcessorControllerApi;
import com.seattleu.imageprocessor.client.invoker.ApiClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ImageProcessorControllerApi imageProcessorControllerApi() {
        return new ImageProcessorControllerApi(apiClient());
    }

    @Bean
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        return apiClient;
    }
}
