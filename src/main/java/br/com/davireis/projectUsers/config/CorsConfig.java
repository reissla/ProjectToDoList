package br.com.davireis.projectUsers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Esta classe é responsável pela conexao entre o  front e back ent
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") //Defina a origem permitida para o cliente React
                .allowedMethods("GET", "POST", "DELETE")// Defina os métods HTTP permitidos
                .allowedHeaders("*") // Defina os cabeççalhos permitidos
                .allowCredentials(true);
    }
}
