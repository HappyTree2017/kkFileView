package cn.keking.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * @author yudian-it
 * @date 2017/11/30
 */
public class ChinesePathFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(request.getScheme()).append("s://").append(request.getServerName());
        if (80 != request.getServerPort() && 443 != request.getServerPort()) {
            pathBuilder.append(":").append(request.getServerPort());
        }
        pathBuilder.append(((HttpServletRequest)request).getContextPath()).append("/");
        request.setAttribute("baseUrl", pathBuilder.toString());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
