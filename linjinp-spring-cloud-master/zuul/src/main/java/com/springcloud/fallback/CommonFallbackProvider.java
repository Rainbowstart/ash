package com.springcloud.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.springcloud.filter.AuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 容错与回退
 * @author: linjinp
 * @create: 2019-09-12 11:27
 **/
@Component
public class CommonFallbackProvider implements FallbackProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter. class);

    /**
     * 需要回退的微服务，"*" 表示所有
     *
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 回退逻辑
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 自定义返回值内容
     * @param status
     * @return
     */
    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                LOGGER.info("服务不可⽤，请稍后再试");
                return new ByteArrayInputStream("服务不可⽤，请稍后再试。".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName(
                        "UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };

    }
}