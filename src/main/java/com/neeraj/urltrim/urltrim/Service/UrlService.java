package com.neeraj.urltrim.urltrim.Service;

import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import com.neeraj.urltrim.urltrim.Repository.UrlRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Random;

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
            return "success";
        }
        return "validation failed";
    }

    private boolean isValidURL(String url) {
        try {
            (new java.net.URL(url)).openStream().close();
            return !(urlExists(url));
        } catch (Exception ignored) { }
        return false;
    }

    private boolean urlExists(String url) {
        return urlRepository.existsByurl(url);
    }

    private String trimUrl(String url) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        return "/" + sb.toString();
    }

    //checking if there is a need to redirection
    public boolean uriExists(String currentUri) {
        return urlRepository.existsBymodifiedUrl(currentUri);
    }

    //Returning the original url for redirection
    public String getOriginalUrl(String currenturi) {
        return urlRepository.findBymodifiedUrl(currenturi).getUrl();
    }
}
