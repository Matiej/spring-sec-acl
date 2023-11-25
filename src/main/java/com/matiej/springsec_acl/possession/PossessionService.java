package com.matiej.springsec_acl.possession;

public interface PossessionService {
    PossessionEntity findOne(Long id);
    PossessionEntity savePossession(PossessionEntity possession);
}
