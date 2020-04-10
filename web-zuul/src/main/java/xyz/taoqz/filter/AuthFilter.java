package xyz.taoqz.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.taoqz.config.JwtProperties;
import xyz.taoqz.entity.UserInfo;
import xyz.taoqz.util.JwtUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 23:15
 */
//@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 拦截类型：前置pre，后置post，环绕routing
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序：数值越小，越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否执行核心业务run方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 1. 获取request对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 2. 获取url
        StringBuffer url = request.getRequestURL();
        // 3. 判断url是否需要拦截
        if(url.indexOf("auth-service")!=-1||url.indexOf("regist")!=-1||
                url.indexOf("sms")!=-1||url.indexOf("query")!=-1
        ){
            //    3.1 不需要拦截--放行
            System.out.println("放行:"+url);
            return null;
        }
        //    3.2 需要拦截
        // 4. 获取token
        String token = request.getHeader("authorization");
        //    4.1 token 存在解析
        if(token!=null){
            // 5. token解析正确，放行
            try {
                UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
                // 不抛出异常，就是解析成功-放行
                return null;
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }

        //    4.2 如果token不存在--报错
        //    token解析失败--报错
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        return null;
    }
}
