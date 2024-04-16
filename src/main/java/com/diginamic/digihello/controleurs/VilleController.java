package com.diginamic.digihello.controleurs;

import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VilleController {

    @Autowired
    private VilleService villeService;

    @GetMapping("/listeVilles")
    public ModelAndView showTownList() {
        List<Ville> villes = villeService.getAllVilles();
        Map<String, Object> model = new HashMap<>();
        model.put("villes", villes);
        return new ModelAndView("ville/listeVilles", model);
    }
}