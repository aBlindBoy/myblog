package com.blog.tenyears.utils.pagingPlugin.filter;


import com.blog.tenyears.utils.pagingPlugin.context.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
@Configuration
@WebFilter(urlPatterns="/*")*/
public class PaginationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Integer pageNum = null;
        Integer pageSize = null;

        try {
            String pageNumString = request.getParameter("pageNum");
            String pageSizeString = request.getParameter("pageSize");

            if (StringUtils.isNotBlank(pageNumString)) {
                pageNum = Integer.parseInt(pageNumString);
            }

            if (StringUtils.isNotBlank(pageSizeString)) {
                pageSize = Integer.parseInt(pageSizeString);
            }

            SystemContext.setPageNum(pageNum);
            SystemContext.setPageSize(pageSize);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
                e.printStackTrace ();
        } finally {
            SystemContext.removePageNumHolder();
            SystemContext.removePageSizeHolder();
        }
    }

    @Override
    public void destroy() {

    }
}
