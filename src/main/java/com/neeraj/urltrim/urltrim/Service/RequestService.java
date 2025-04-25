package com.neeraj.urltrim.urltrim.Service;

import com.neeraj.urltrim.urltrim.Entity.RequestDetails;
import com.neeraj.urltrim.urltrim.Repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void saveRequest(RequestDetails requestDetails) {
        requestRepository.save(requestDetails);
    }
}
