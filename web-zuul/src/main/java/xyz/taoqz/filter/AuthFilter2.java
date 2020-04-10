package xyz.taoqz.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.taoqz.config.JwtProperties;
import xyz.taoqz.config.PathProperties;
import xyz.taoqz.entity.UserInfo;
import xyz.taoqz.util.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 23:24
 */
@Component
public class AuthFilter2 extends ZuulFilter {

    /**
     * 获取公钥
     */
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private PathProperties pathProperties;

    /**
     * 前置pre/后置post
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序  值越大，越最后执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否要执行核心代码-run方法
     * @return
     *
     * 获取白名单，判断当前的url是否需要执行run方法
     *
     */
    @Override
    public boolean shouldFilter() {
        // 1. 获取当前请求的url
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        StringBuffer url = request.getRequestURL();
        return isAllowPths(url);
    }
    public boolean isAllowPths(StringBuffer url){
        // 1. 定义返回的变量
        // 默认拦截
        boolean flag = true;
        // 2. 获取yml中的集合
        List<String> list = pathProperties.getAllowPaths();
        // 3. 判断url是否在集合中
        for(String s:list){
            // 当yml中的路径在url中找到了，就放行
            if(url.indexOf(s)!=-1){
                flag=false;
                break;
            }
        }
        // 4. 在--返回false
        // 5. 不在-返回true，执行run方法
        return flag;
    }



    /**
     * 核心业务
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //1. 获取用户请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //2. 获取token
        String token = request.getHeader("authorization");
        if(token!=null){
            //3. 解析token--公钥解密
            try {
                UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
                return null;
            } catch (Exception e) {
//                e.printStackTrace();
                // 抛出异常，表示解析失败
//                ctx.setSendZuulResponse(false);
//                ctx.setResponseStatusCode(401);
            }
        }
        //4. token ==null
        ctx.setSendZuulResponse(false);
        //language=JSON
        String json = "{\"msg\": \"没有授权\"}";
        ctx.setResponseBody(json);
        ctx.getResponse().setContentType("application/json;charset=utf-8");
        ctx.setResponseStatusCode(401);

        //6. 判断是否放行
        return null;
    }
}
