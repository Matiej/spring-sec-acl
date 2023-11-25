package com.matiej.springsec_acl.security;

import com.matiej.springsec_acl.global.IEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionService {
    private final MutableAclService aclService;

    public void addPermissionForUSer(IEntity targetObj, Permission permission, String username) {
        Sid sid = new PrincipalSid(username);
        addPermissionForSid(targetObj, permission, sid);

    }

    private void addPermissionForSid(IEntity targetObj, Permission permission, Sid sid) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                ObjectIdentity objectIdentity = new ObjectIdentityImpl(targetObj.getClass(), targetObj.getId());
                MutableAcl acl = null;
                try {
                    acl = (MutableAcl) aclService.readAclById(objectIdentity);
                } catch (NotFoundException e) {
                    acl = aclService.createAcl(objectIdentity);
                }
                acl.insertAce(acl.getEntries().size(), permission, sid, true);
                aclService.updateAcl(acl);
            }
        });
    }
}
