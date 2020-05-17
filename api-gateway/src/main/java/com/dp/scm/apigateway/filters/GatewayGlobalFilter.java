package com.dp.scm.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GatewayGlobalFilter extends AbstractGatewayFilterFactory<GatewayGlobalFilter.Config> {

    public GatewayGlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info("inside GatewayGlobalFilter.apply method");

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest().mutate().header("scgw-global-header", Math.random()*10+"").build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config {
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
