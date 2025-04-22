package com.neeraj.urltrim.urltrim.Controllers;

import com.neeraj.urltrim.urltrim.Repository.UrlRepository;
import com.neeraj.urltrim.urltrim.Service.UrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    private final UrlService urlService;

    public RootController(UrlService urlService) {
        this.urlService = urlService;
    }
    @PostMapping("/trimurl")
    public String trimurl(@RequestBody String url) {
        urlService.saveUrl(url);
        return "Hello WOrld!!";
    }
}
