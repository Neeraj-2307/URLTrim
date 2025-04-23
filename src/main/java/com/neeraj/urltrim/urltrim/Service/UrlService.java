package com.neeraj.urltrim.urltrim.Service;

import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import com.neeraj.urltrim.urltrim.Models.GenericResponseModel;
import com.neeraj.urltrim.urltrim.Repository.UrlRepository;
import com.neeraj.urltrim.urltrim.Utils.ErrorCode;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public GenericResponseModel saveUrl(String url) {
        if(!(urlExists(url))) {
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
            return GenericResponseModel.builder()
                    .success(true)
                    .responseEntity(urlEntity)
                    .build();
        }
        return GenericResponseModel.builder()
                .errorCode(ErrorCode.URL_ALREADY_EXISTS)
                .build();
    }

    //TODO: create some form of atleast any simple validation
    private boolean urlExists(String url) {
        return urlRepository.existsByurl(url);
    }

    //creating a random trimmed url
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
    public String getOriginalUrl(String uri) {
        return getTrimmedEntity(uri).getUrl();
    }

    public void increaseHitCount(String targetUri) {
        UrlEntity entity = getTrimmedEntity(targetUri);
        entity.setHitCount(entity.getHitCount() + 1);
        urlRepository.save(entity);
    }

    public UrlEntity getTrimmedEntity(String uri) {
        return urlRepository.findBymodifiedUrl(uri);
    }
}
