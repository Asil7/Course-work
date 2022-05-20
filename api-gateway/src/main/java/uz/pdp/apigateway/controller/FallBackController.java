package uz.pdp.apigateway.controller;

//Asilbek Fayzullayev 20.04.2022 15:56   

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
    @GetMapping("/continentFallBack")
    public String continentFallBack() {
        return "Something wrong with Continent service. " +
                " Please, try again later!";
    }
}
