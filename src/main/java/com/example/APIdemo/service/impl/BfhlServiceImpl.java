package com.example.APIdemo.service.impl;

import com.example.APIdemo.dto.RequestDTO;
import com.example.APIdemo.dto.ResponseDTO;
import com.example.APIdemo.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "ishan_raut_20102005";
    private static final String EMAIL = "ishanraut231286@acropolis.in";
    private static final String ROLL_NUMBER = "0827RL231035";

    @Override
    public ResponseDTO process(RequestDTO request) {
        if (request == null || request.getData() == null) {
            throw new IllegalArgumentException("Request data cannot be null");
        }

        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long numericSum = 0;
        StringBuilder allAlphabets = new StringBuilder();

        for (String item : data) {
            if (item == null || item.isEmpty()) continue;

            if (isNumeric(item)) {
                long val = Long.parseLong(item);
                numericSum += val;
                if (val % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                alphabets.add(item.toUpperCase());
                allAlphabets.append(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(allAlphabets.toString());

        return ResponseDTO.builder()
                .success(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(numericSum))
                .concatString(concatString)
                .build();
    }

    private boolean isNumeric(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAlphabetic(String s) {
        return s.chars().allMatch(Character::isLetter);
    }

    /**
     * Concatenate all alphabetical characters, reverse, then apply alternating caps
     * (first char uppercase, second lowercase, third uppercase, ...)
     */
    public String buildConcatString(String allAlphabets) {
        if (allAlphabets == null || allAlphabets.isEmpty()) return "";

        // reverse
        String reversed = new StringBuilder(allAlphabets).reverse().toString();

        // alternating caps: index 0 -> upper, index 1 -> lower, index 2 -> upper ...
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
