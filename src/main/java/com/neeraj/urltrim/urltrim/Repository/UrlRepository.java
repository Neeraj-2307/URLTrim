package com.neeraj.urltrim.urltrim.Repository;

import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository <UrlEntity, Integer> {
}
