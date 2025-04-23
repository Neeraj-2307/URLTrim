package com.neeraj.urltrim.urltrim.Models;

import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import com.neeraj.urltrim.urltrim.Utils.ErrorCode;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponseModel {
    private boolean success;
    private UrlEntity responseEntity;
    private ErrorCode errorCode;
}
