/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.t2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author miguel
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("MainPage");
        registry.addViewController("/main_page_user").setViewName("main_page_user");
        registry.addViewController("/main_page_admin").setViewName("main_page_admin");
        registry.addViewController("/MainPage").setViewName("MainPage");
        registry.addViewController("/login").setViewName("login");
    }
}
