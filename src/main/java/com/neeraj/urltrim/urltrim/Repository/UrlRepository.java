package com.neeraj.urltrim.urltrim.Repository;

import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UrlRepository extends JpaRepository <UrlEntity, Integer> {

    //checking if we already have this trimmed url
    boolean existsBymodifiedUrl(String modifiedUrl);

    //returning the entity in case we have this particular trimmed url
    UrlEntity findBymodifiedUrl(String modifiedUrl);

    //Checking if the url is already stored
    boolean existsByurl(String url);

    //returning the entity in case we have already stored this url
    UrlEntity findByurl(String url);

    @Transactional
    void deleteByurlTTLBefore(Date date);
}
