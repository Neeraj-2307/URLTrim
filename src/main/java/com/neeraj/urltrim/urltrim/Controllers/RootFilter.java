package com.neeraj.urltrim.urltrim.Controllers;

import com.neeraj.urltrim.urltrim.Entity.RequestDetails;
import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import com.neeraj.urltrim.urltrim.Service.RequestService;
import com.neeraj.urltrim.urltrim.Service.UrlService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class RootFilter implements Filter {

    private final UrlService urlService;
    private final RequestService requestService;

    public RootFilter(UrlService urlService, RequestService requestService) {
        this.urlService = urlService;
        this.requestService = requestService;
    }


    //Handling all the redirection logic, doFilter method listens the incoming server requests
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(httpRequest.getMethod().equals("GET")) {
            String targetUri = httpRequest.getRequestURI();
            //Redirecting if the url is supposed to be redirected
            if(urlService.uriExists(targetUri)) {
                UrlEntity urlEntity = urlService.getTrimmedEntity(targetUri);
                urlService.increaseHitCount(targetUri);
                String originalUrl = urlService.getOriginalUrl(targetUri);
                RequestDetails requestDetails = RequestDetails.builder()
                        .remoteAddr(httpRequest.getRemoteAddr())
                        .remoteHost(httpRequest.getRemoteHost())
                        .remotePort(httpRequest.getRemotePort())
                        .serverName(httpRequest.getServerName())
                        .serverPort(httpRequest.getServerPort())
                        .creationTime(new Date())
                        .urlEntity(urlEntity)
                        .build();
                requestService.saveRequest(requestDetails);
                httpResponse.sendRedirect(originalUrl);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
