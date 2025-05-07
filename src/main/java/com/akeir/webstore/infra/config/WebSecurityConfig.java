package com.akeir.webstore.infra.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

	@Value("${jwt.public.key}")
	private RSAPublicKey key;
	
	@Value("${jwt.private.key}")
	private RSAPrivateKey priv;
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
    {
        disableCsrf(http);
    	enableAuthentication(http);
        setOAuthResourceServer(http);
        
        return http.build();
    }
    
    /**
     * IMPORTANT
     * Disable cookies since we work with tokens. No need of cookies and prevent csrf attacks
     * 
     * @param HttpSecurity component
     * @throws Exception
     */
    private void disableCsrf(HttpSecurity http) throws Exception
    {
    	http.csrf(csrf -> csrf.disable());
    }
    
    /**
     * Enable path to authentication and lock all other paths
     * 
     * @param HttpSecurity component
     * @throws Exception
     */
    private void enableAuthentication(HttpSecurity http) throws Exception
    {
    	http.authorizeHttpRequests(auth -> auth
    			.requestMatchers("/auth/**").permitAll()
    			.requestMatchers("/*/create").hasAnyAuthority("SCOPE_SYSADMIN")
    			.requestMatchers("/*/edit").hasAnyAuthority("SCOPE_SYSADMIN")
    			.requestMatchers("/*/delete").hasAnyAuthority("SCOPE_SYSADMIN")
    			.anyRequest().authenticated())
    			.httpBasic(Customizer.withDefaults());
    }
    
    /**
     * Finally we will set and enable the auth server to work with JWT
     * 
     * @param HttpSecurity component
     * @throws Exception
     */
    private void setOAuthResourceServer(HttpSecurity http) throws Exception
    {
    	http.oauth2ResourceServer(conf -> conf.jwt(Customizer.withDefaults()));
    }
    
    @Bean
    JwtDecoder jwtDecoder()
    {
    	return NimbusJwtDecoder.withPublicKey(key).build();
    }
    
    @Bean
    JwtEncoder jwtEncoder()
    {
    	var jwk = new RSAKey.Builder(key).privateKey(priv).build();
    	var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    	return new NimbusJwtEncoder(jwks);
    }
    
    @Bean
    PasswordEncoder passwordEncoder()
    {
    	return new BCryptPasswordEncoder();
    }
}
