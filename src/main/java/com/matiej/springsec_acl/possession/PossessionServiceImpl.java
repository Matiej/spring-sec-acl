package com.matiej.springsec_acl.possession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PossessionServiceImpl implements PossessionService {
    private final PossessionRepository possessionRepository;

    @Override
    public PossessionEntity findOne(Long id) {
        return possessionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No possession find ID: " + id));
    }
}
