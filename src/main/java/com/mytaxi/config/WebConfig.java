package com.mytaxi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static com.mytaxi.constant.Constants.EXTERNAL_URL_RESOURCES;
import static com.mytaxi.constant.Constants.INTERNAL_URL_RESOURCES;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.config
 * USER      : sean
 * DATE      : 04-Thu-Oct-2018
 * TIME      : 03:49
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds resource handlers.
     *
     * @param registry see {@link ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(EXTERNAL_URL_RESOURCES)
                .addResourceLocations(INTERNAL_URL_RESOURCES);
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("templates/404.html").setViewName("404");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates");
        resolver.setSuffix(".html");
        return resolver;
    }
}
