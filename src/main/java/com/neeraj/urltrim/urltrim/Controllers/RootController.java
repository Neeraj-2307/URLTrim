package com.neeraj.urltrim.urltrim.Controllers;

import com.neeraj.urltrim.urltrim.Models.GenericRequestModel;
import com.neeraj.urltrim.urltrim.Models.GenericResponseModel;
import com.neeraj.urltrim.urltrim.Models.GenericUrlRequestModel;
import com.neeraj.urltrim.urltrim.Service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
public class RootController {

    private final UrlService urlService;

    public RootController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping("/trim_url")
    @Tag(name = "Trim Url")
    @Operation(description = "Responsible for actually trimming the url and returning the url details")
    public GenericResponseModel trimurl(@RequestBody GenericRequestModel urlEntity) {
        return urlService.saveUrl(urlEntity.getUrl());
    }

    @GetMapping("/fetch_url")
    @Tag(name = "Existing Url")
    @Operation(description = "Responsible for returning the url details as the url is already trimmed")
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

    @GetMapping("/totalUrls")
    @Tag(name = "Existing Url")
    @Operation(description = "To get the total urls stored")
    public long getTotalEntries() {
        return urlService.totalEntries();
    }

    @GetMapping("/fetch_url_requests")
    @Tag(name = "Existing Url")
    @Operation(description = "To get the details of all the requests")
    public GenericUrlRequestModel getRequestEntity(@RequestParam String url) {
        return GenericUrlRequestModel.builder()
                .success(true)
                .requestDetails(urlService.getRequestDetails(url))
                .build();
    }
}
