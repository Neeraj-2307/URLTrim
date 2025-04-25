package com.neeraj.urltrim.urltrim.Repository;

import com.neeraj.urltrim.urltrim.Entity.RequestDetails;
import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestDetails, Integer> {
    List<RequestDetails> findByurlEntity(UrlEntity urlEntity);
}
