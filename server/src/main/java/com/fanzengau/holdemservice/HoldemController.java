package com.fanzengau.holdemservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fanzengau.holdem.Holdem;

@RestController
public class HoldemController {

    @GetMapping("/")
    public String index() {
        Holdem holdem = new Holdem(2);
        holdem.shuffle();
        var flop = holdem.getFlop();
        System.out.println(flop[0] + "," + flop[1] + "," + flop[2]);
        return "Greetings from Spring Boot!";
    }

}