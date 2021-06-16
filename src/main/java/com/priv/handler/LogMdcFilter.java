package com.priv.handler;

import com.priv.constant.TraceConstant;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Json
 * @date 2021/6/9 9:35
 */
@WebFilter(urlPatterns = "/*", filterName = "logMdcFilter")
public class LogMdcFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean bInsertMDC = insertMDC();
        try {
            chain.doFilter(request, response);
        } finally {
            if (bInsertMDC) {
                MDC.remove(TraceConstant.MDC_TRACE);
            }
        }
    }

    private boolean insertMDC() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString().replace("-", "");
        MDC.put(TraceConstant.MDC_TRACE, uniqueId);
        return true;
    }

}
