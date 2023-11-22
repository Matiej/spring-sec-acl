package com.matiej.springsec_acl.possession;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/possessions")
@RequiredArgsConstructor
public class PossessionController {
    private final PossessionService possessionService;

    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ') or hasPermission(returnObject, 'ADMINISTRATION')")
    public PossessionEntity findOne(@PathVariable("id") final Long id) {
        return possessionService.findOne(id);
    }

}
