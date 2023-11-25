package com.matiej.springsec_acl.possession;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PossessionServiceImpl implements PossessionService {
    private final PossessionRepository possessionRepository;

    @Override
    public PossessionEntity findOne(Long id) {
        return possessionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No possession find ID: " + id));
    }

    @Override
    public PossessionEntity savePossession(PossessionEntity possession) {
        return possessionRepository.save(possession);
    }

}
