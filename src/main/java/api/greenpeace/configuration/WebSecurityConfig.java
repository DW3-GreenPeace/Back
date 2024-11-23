package api.greenpeace.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebMvc
public class WebSecurityConfig implements WebMvcConfigurer {
	
    //LIBERA O CORS
    public void addCorsMappings(CorsRegistry registry) {//addCorsMappings() - é um metodo do WebMvcConfigurer que implementamos nessa classe nós acessamos ele e utilizamos para mexer em coisas em relção ao cors...
    	
    	registry.addMapping("/**") //libera o uso de todas as rotas (depois de barra todo mundo "/**")
    	.allowedOrigins("*"); //libera acesso de todas as origens
    	
    }
	
	/* OQUE É UM @Bean ?
	 * Um Bean no Spring é basicamente um componente da sua aplicação que 
	 * o Spring gerencia e pode injetar automaticamente onde ele for necessário. 
	 * */
	
    // Define o PasswordEncoder como um Bean
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desabilitar CSRF (geralmente necessário para APIs)
        http.csrf().disable()

            // Liberar o acesso a todas as requisições HTTP
            .authorizeHttpRequests()
                .anyRequest().permitAll() // Permite todas as requisições sem autenticação

            // Não há necessidade de configurar login e logout se você não precisar de autenticação
            .and()
            .formLogin().disable()  // Desabilitar a configuração de login por formulário
            .logout().disable();    // Desabilitar a configuração de logout

        return http.build();
    }
    
}
