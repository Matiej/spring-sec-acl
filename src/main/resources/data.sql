CREATE TABLE IF NOT EXISTS acl_sid (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    principal BOOLEAN NOT NULL,
    sid VARCHAR(100) NOT NULL,
    CONSTRAINT unique_acl_sid UNIQUE(sid, principal)
);

CREATE TABLE IF NOT EXISTS acl_class (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    class VARCHAR(100) NOT NULL,
    CONSTRAINT uk_acl_class UNIQUE(class)
);

CREATE TABLE IF NOT EXISTS acl_object_identity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    object_id_class BIGINT NOT NULL,
    object_id_identity BIGINT NOT NULL,
    parent_object BIGINT,
    owner_sid BIGINT,
    entries_inheriting BOOLEAN NOT NULL,
    CONSTRAINT uk_acl_object_identity UNIQUE (object_id_class, object_id_identity),
    CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
);

CREATE TABLE IF NOT EXISTS acl_entry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    acl_object_identity BIGINT NOT NULL,
    ace_order INTEGER NOT NULL,
    sid BIGINT NOT NULL,
    mask INTEGER NOT NULL,
    granting BOOLEAN NOT NULL,
    audit_success BOOLEAN NOT NULL,
    audit_failure BOOLEAN NOT NULL,
    CONSTRAINT unique_acl_entry UNIQUE (acl_object_identity, ace_order),
    CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
);
--
--INSERT INTO  users (id, email, password)
--VALUES
--(8, 'maciek@email.com', '$2a$12$x.An3EW2HNUe9FF4ziEmR.mHJOaRpkW0blF9HiB.q/hkK.9R1xnru'),
--(9, 'beata@email.com', '$2a$12$x.An3EW2HNUe9FF4ziEmR.mHJOaRpkW0blF9HiB.q/hkK.9R1xnru');
----
--INSERT INTO Possession (id, name, owner_id)
--VALUES
--(1, 'Maciek Possession', 8),
--(2, 'Common Possession', 8),
--(3, 'Beata Possession', 9);
--
--INSERT INTO acl_sid (id, principal, sid)
--VALUES
--(1, 1, 'maciek@email.com'),
--(2, 1, 'beata@email.com');
--
--INSERT INTO acl_class (id, class)
--VALUES
--(1, 'com.matiej.springsecstudy.acl.domain.Possession');
--
--INSERT INTO acl_object_identity
--(id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
--VALUES
--(1, 1, 1, NULL, 1, 1), -- Maciek Possession object identity
--(2, 1, 2, NULL, 1, 1), -- Common Possession object identity
--(3, 1, 3, NULL, 1, 1); -- Beata Possession object identity
--
--INSERT INTO acl_entry
--(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
--VALUES
--(1, 1, 0, 1, 16, 1, 0, 0), -- maciek@email.com has Admin permission for Possession 1
--(2, 2, 0, 1, 16, 1, 0, 0), -- maciek@email.com has Admin permission for Common Possession 2
--(3, 2, 1, 2, 1, 1, 0, 0),  -- beata@email.com has Read permission for Common Possession 2
--(4, 3, 0, 2, 16, 1, 0, 0); -- beata@email.com has Admin permission for Eric Possession 3