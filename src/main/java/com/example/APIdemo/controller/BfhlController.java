package com.example.APIdemo.controller;

import com.example.APIdemo.dto.RequestDTO;
import com.example.APIdemo.dto.ResponseDTO;
import com.example.APIdemo.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> process(@RequestBody RequestDTO request) {
        ResponseDTO response = bfhlService.process(request);
        return ResponseEntity.ok(response);
    }
}
