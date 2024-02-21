package com.mitocode.microservices.authenticationserveroauth2.config;

import com.mitocode.microservices.authenticationserveroauth2.service.CoreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthenticationServiceConfig extends AuthorizationServerConfigurerAdapter {

    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final CustomTokenEnhancer customTokenEnhancer;
    private final CoreUserService coreUserService;

    @Value("${mitocode.security.client:mitocode}")
    private String mitocodeClient;
    @Value("${mitocode.security.secret:mitocode}")
    private String mitocodeSecret;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(mitocodeClient)
                .secret(encoder.encode(mitocodeSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(300)
                .refreshTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(customTokenEnhancer, jwtAccessTokenConverter()));
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(new JwtTokenStore(jwtAccessTokenConverter()))
                .tokenEnhancer(tokenEnhancerChain)
                .userDetailsService(coreUserService)
                .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        System.out.println("KEY");
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(Base64.getEncoder().encodeToString("KEY".getBytes()));
        return tokenConverter;
    }

}
