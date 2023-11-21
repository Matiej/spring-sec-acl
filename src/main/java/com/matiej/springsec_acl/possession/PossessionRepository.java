package com.matiej.springsec_acl.possession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PossessionRepository extends JpaRepository<PossessionEntity, Long> {
}
