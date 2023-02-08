package com.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul 过滤器
 * 授权验证示例
 * @author: linjinp
 * @create: 2019-09-12 09:42
 **/
@Component
public class AuthFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter. class);

    /**
     * 是否开启验证
     * 正常项目里，这种属性应该放配置文件里
     */
    private static final Boolean AUTH = Boolean.TRUE;

    /**
     * 指定过滤器的调用时机
     * pre： 路由之前，如实现认证，记录调试信息等
     * routing: 路由时
     * post: 路由后，比如添加HTTP header
     * error: 发生错误时调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器顺序
     * 比如有两个 pre 的过滤器，可以通过设置数字大小，控制两个过滤器执行先后
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 判断是否启用该过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤的具体逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // 启动验证
        if (AUTH) {
            String token = request.getHeader("Authorization");
            if (token == null) {
                LOGGER.info("该访问未进行授权");
                // 路由失败
                ctx.setSendZuulResponse(false);
                // 返回错误码
                ctx.setResponseStatusCode(401);
            } else {
                LOGGER.info("访问已授权");
                // 验证成功
                ctx.setSendZuulResponse(true);
                ctx.setResponseStatusCode(200);
            }
        } else {
            LOGGER.info("访问成功");
            // 没启用就直接成功
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
        }
        return null;
    }
}
