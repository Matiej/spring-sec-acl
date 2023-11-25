package com.matiej.springsec_acl.possession;

import com.matiej.springsec_acl.global.IEntity;
import com.matiej.springsec_acl.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "possession")
public class PossessionEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;
}
