package com.diginamic.digihello.controleurs;

import com.diginamic.digihello.domain.UserAccount;
import com.diginamic.digihello.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.GrantedAuthority;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @GetMapping("/index")
    public ModelAndView getIndex() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> model = new HashMap<>();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            String roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));
            model.put("username", username);
            model.put("roles", roles);
        }
        return new ModelAndView("ville/index", model);
    }
}