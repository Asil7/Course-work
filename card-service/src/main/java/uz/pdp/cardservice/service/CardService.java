package uz.pdp.cardservice.service;

//Asilbek Fayzullayev 18.05.2022 15:59   

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.cardservice.common.ApiResponse;
import uz.pdp.cardservice.dto.CardDto;
import uz.pdp.cardservice.dto.ProductDto;
import uz.pdp.cardservice.model.Card;
import uz.pdp.cardservice.model.Status;
import uz.pdp.cardservice.repository.CardRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    RestTemplate restTemplate;

    public ApiResponse addToCard(Card card){
        card.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        card.setStatus(Status.NEW);
        String cardUrl1 = "http://user-service/api/user/" + card.getUserId();
        ApiResponse apiResponse1 = restTemplate.getForObject(cardUrl1, ApiResponse.class);
        apiResponse1.getObject();
        card.setQuantity(card.getQuantity());
        String cardUrl = "http://product/api/product/" + card.getProductId();
        ApiResponse apiResponse = restTemplate.getForObject(cardUrl, ApiResponse.class);
        apiResponse.getObject();
        Card save = cardRepository.save(card);
        return new ApiResponse("Successfully saved",true,save);
    }





    public ResponseEntity<?> getCardByUserId(Integer userId){
        List<CardDto> cardDtoList = new ArrayList<>();
        List<Card> all = cardRepository.findAllByUserId(userId);
        for (Card card : all) {
            ProductDto productDto = restTemplate.getForObject(
                    "http://product/api/product/" +
                    card.getProductId(),ProductDto.class);
            CardDto cardDto = new CardDto(
                    card.getId(),
                    card.getProductId(),
                    productDto.getBrand(),
                    card.getQuantity(),
                    productDto.getPrice(),
                    productDto.getAttachmentId());
            cardDtoList.add(cardDto);
        }
         return ResponseEntity.ok(cardDtoList);
    }
}
