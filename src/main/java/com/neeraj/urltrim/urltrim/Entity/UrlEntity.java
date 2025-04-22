package com.neeraj.urltrim.urltrim.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {

    @Id
    private int id;
    private String url;
    private String modifiedUrl;
    private int hitCount;
    private Date creationTime;
    private Date urlTTL;
}
