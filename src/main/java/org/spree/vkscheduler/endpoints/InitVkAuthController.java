package org.spree.vkscheduler.endpoints;

import org.spree.vkscheduler.authentication.VkAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitVkAuthController {

    @Autowired
    private VkAuthentication authentication;

    public InitVkAuthController(VkAuthentication authentication) {
        this.authentication = authentication;
    }

    @RequestMapping("/init")
    public String index(@RequestParam("code") String code) {
        if (code == "123") return "success full";
        authentication.init(code);
        return "Success";
    }
}
