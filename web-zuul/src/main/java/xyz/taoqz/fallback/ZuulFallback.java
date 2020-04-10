package xyz.taoqz.fallback;

import com.netflix.zuul.context.RequestContext;
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
 * 当服务未启动或者请求超时会执行
 * @author :almostTao
 * @date :Created in 2019/12/20 11:17
 */
@Component
public class ZuulFallback implements FallbackProvider {
    
    // 表明是为哪个微服务提供回退，*表示为所有微服务提供回退
    @Override
    public String getRoute() {
        System.out.println("11111111");
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        System.out.println("路由名称"+route);
        System.out.println(cause.getMessage());
        return this.response(HttpStatus.OK);
    }

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
                RequestContext currentContext = RequestContext.getCurrentContext();
                System.out.println(status);
                String json = "{\"msg\": \"服务不可用，请稍后再试。\"}";
                return new ByteArrayInputStream(json.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }

        };
    }

}
