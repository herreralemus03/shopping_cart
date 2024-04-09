package com.hlstudios.payment.configuration;

import com.hlstudios.payment.components.AuthInterceptor;
import com.hlstudios.payment.services.clients.AuthClientService;
import jakarta.inject.Provider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    final Provider<AuthClientService> authClientServiceProvider;

    public WebConfig(Provider<AuthClientService> authClientServiceProvider) {
        this.authClientServiceProvider = authClientServiceProvider;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new AuthInterceptor(authClientServiceProvider.get()))
                .excludePathPatterns("/swagger-ui/**")
                .addPathPatterns("/api/v1/**");
    }
}
