package com.neeraj.urltrim.urltrim.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    private String serverName;
    private int serverPort;
    private  String remoteAddr;
    private String remoteHost;
    private int remotePort;
    private Date creationTime;

    @ManyToOne
    @JoinColumn(name = "urlId", nullable = false)
    private UrlEntity urlEntity;
}
