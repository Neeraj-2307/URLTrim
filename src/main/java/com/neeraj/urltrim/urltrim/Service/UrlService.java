package com.neeraj.urltrim.urltrim.Service;

import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import com.neeraj.urltrim.urltrim.Repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String saveUrl(String url) {
        if(isValidURL(url)) {
            String trimmedUrl = trimUrl(url);
            Date currDate = new Date();
            UrlEntity urlEntity = UrlEntity.builder()
                    .url(url)
                    .modifiedUrl(trimmedUrl)
                    .hitCount(0)
                    .creationTime(currDate)
                    .urlTTL(currDate)
                    .build();
            urlEntity.setUrl(url);
            urlRepository.save(urlEntity);
        }
        return "URL successfully saved to db";
    }

    private boolean isValidURL(String url) {
        //TODO: Implement this
        return true;
    }

    private String trimUrl(String url) {
        return "/123";
    }

    public boolean uriExists(String currentUri) {
        return urlRepository.existsBymodifiedUrl(currentUri);
    }

    public String getOriginalUrl(String currenturi) {
        return urlRepository.findBymodifiedUrl(currenturi).getUrl();
    }
}
