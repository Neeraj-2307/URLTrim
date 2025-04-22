package com.neeraj.urltrim.urltrim.Service;

import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import com.neeraj.urltrim.urltrim.Repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String saveUrl(String url) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrl(url);
        urlRepository.save(urlEntity);
        return "URL successfully saved to db";
    }
}
