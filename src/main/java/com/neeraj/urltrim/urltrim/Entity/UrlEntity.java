package com.neeraj.urltrim.urltrim.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


//Basic url entity
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String modifiedUrl;
    private int hitCount;
    private Date creationTime;
    private Date urlTTL;
}
