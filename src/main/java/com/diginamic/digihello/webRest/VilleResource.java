package com.diginamic.digihello.webRest;

import com.diginamic.digihello.domain.Ville;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleResource {

    @GetMapping
    public List<Ville> getVilles() {
        return new ArrayList<>(Arrays.asList(new Ville("Paris", 2200000), new Ville("New York", 8600000), new Ville("Tokyo", 13960000)));
    }
}