package com.neeraj.urltrim.urltrim.Controllers;

import com.neeraj.urltrim.urltrim.Service.UrlService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RootFilter implements Filter {

    private final UrlService urlService;

    public RootFilter(UrlService urlService) {
        this.urlService = urlService;
    }


    //Handling all the redirection logic, doFilter method listens the incoming server requests
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("Incoming Request: " +
                httpRequest.getMethod() + " " +
                httpRequest.getRequestURI());

        if(httpRequest.getMethod().equals("GET")) {
            String targetUri = httpRequest.getRequestURI();
            //Redirecting if the url is supposed to be redirected
            if(urlService.uriExists(targetUri)) {
                String originalUrl = urlService.getOriginalUrl(targetUri);
                httpResponse.sendRedirect(originalUrl);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
