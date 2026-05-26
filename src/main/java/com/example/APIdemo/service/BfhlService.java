package com.example.APIdemo.service;

import com.example.APIdemo.dto.RequestDTO;
import com.example.APIdemo.dto.ResponseDTO;

public interface BfhlService {
    ResponseDTO process(RequestDTO request);
}
