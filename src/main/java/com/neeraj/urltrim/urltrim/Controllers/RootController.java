package com.neeraj.urltrim.urltrim.Controllers;

import com.neeraj.urltrim.urltrim.Models.GenericRequestModel;
import com.neeraj.urltrim.urltrim.Models.GenericResponseModel;
import com.neeraj.urltrim.urltrim.Service.UrlService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
public class RootController {

    private final UrlService urlService;

    public RootController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping("/trim_url")
    public GenericResponseModel trimurl(@RequestBody GenericRequestModel urlEntity) {
        return urlService.saveUrl(urlEntity.getUrl());
    }

    @GetMapping("/fetch_url")
    public GenericResponseModel getEntity(@RequestParam String url) {
        return GenericResponseModel.builder()
                .success(true)
                .responseEntity(urlService.getTrimmedEntity(url))
                .build();
    }

    //Method scheduled ot run at every day to delete all the expired links
    @Scheduled(cron = "0 0 8 * * *", zone = "Asia/Kolkata")
    public void deleteExpiredLinks() {
        urlService.deleteExpiredLinks();
    }
}
