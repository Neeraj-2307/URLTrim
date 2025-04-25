package com.neeraj.urltrim.urltrim.Models;

import com.neeraj.urltrim.urltrim.Entity.RequestDetails;
import com.neeraj.urltrim.urltrim.Entity.UrlEntity;
import com.neeraj.urltrim.urltrim.Utils.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericUrlRequestModel {
    private boolean success;
    private List<RequestDetails> requestDetails;
    private ErrorCode errorCode;
}
