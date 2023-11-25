package com.matiej.springsec_acl.possession;

import com.matiej.springsec_acl.security.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/possessions")
@RequiredArgsConstructor
public class PossessionController {
    private final PossessionService possessionService;
    private final PermissionService permissionService;

    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ') or hasPermission(returnObject, 'ADMINISTRATION')")
    public PossessionEntity findOne(@PathVariable("id") final Long id) {
        PossessionEntity possession = possessionService.findOne(id);
        return possession;
    }

    @PostMapping
    public ModelAndView create(@Valid PossessionEntity possession, Authentication authentication) {
        PossessionEntity newPoss = possessionService.savePossession(possession);
        permissionService.addPermissionForUSer(newPoss, BasePermission.ADMINISTRATION, authentication.getName());

        return new ModelAndView("redirect:/user?message-Possession created with ID: " + newPoss.getId());
    }

}
