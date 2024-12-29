package com.he.train.gateway.config;

import cn.hutool.http.HttpStatus;
import com.he.train.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoginMemberFilter implements GlobalFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LoginMemberFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if (path.contains("/admin")
                || path.contains("/test")
                || path.contains("/member/member/login")
                || path.contains("/member/member/send-code")) {
            LOG.info("no token validation required: {}", path);
            return chain.filter(exchange);
        } else {
            LOG.info("token validation required: {}", path);
        }
        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("token: {}", token);

        if (token == null || token.isEmpty()) {
            LOG.info("token is empty");
            exchange.getResponse().setRawStatusCode(HttpStatus.HTTP_UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        boolean validate = JwtUtil.validate(token);
        if (validate) {
            LOG.info("token is valid");
            return chain.filter(exchange);
        } else {
            LOG.info("token is invalid");
            exchange.getResponse().setRawStatusCode(HttpStatus.HTTP_UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    /**
     * 优先级设置  值越小  优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
