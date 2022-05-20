package uz.pdp.cardservice.controller;

//Asilbek Fayzullayev 18.05.2022 16:00   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cardservice.common.ApiResponse;
import uz.pdp.cardservice.model.Card;
import uz.pdp.cardservice.service.CardService;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping
    public ResponseEntity<?> saveCard(@RequestBody Card card) {
        ApiResponse apiResponse = cardService.addToCard(card);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse.getObject());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCard(@PathVariable Integer userId){
            return cardService.getCardByUserId(userId);
    }
}
