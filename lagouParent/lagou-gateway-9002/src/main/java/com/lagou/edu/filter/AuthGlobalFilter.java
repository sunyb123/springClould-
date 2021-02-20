package com.lagou.edu.filter;

import com.lagou.edu.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = null;
        List<String> tokens = exchange.getRequest().getHeaders().get("token");
        if(tokens!=null){
            token = tokens.get(0);
        }
        if(token!=null){
            String email = userFeignClient.info(token);
            if(email!=null&&email.length()>0){
                ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
                response.setStatusCode(HttpStatus.SEE_OTHER);
                response.getHeaders().set("Location", "localhost:80/static/welcome.html");
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
