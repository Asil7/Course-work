package uz.pdp.product.controller;

//Asilbek Fayzullayev 17.05.2022 16:47   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.callback.ReactiveEntityCallbacks;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.product.common.ApiResponse;
import uz.pdp.product.model.VideoCard;
import uz.pdp.product.service.VideoCardService;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/video-card")
public class VideoCardController {
    @Autowired
    VideoCardService videoCardService;

    @GetMapping
    public HttpEntity<?> getAllVideoCard() {
        ApiResponse allVideoCard = videoCardService.getAllVideoCard();
        return ResponseEntity.status(allVideoCard.isSuccess() ? 200 : 400).body(allVideoCard);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getVideoCardById(@PathVariable Integer id) {
        ApiResponse videoCardById = videoCardService.getVideoCardById(id);
        return ResponseEntity.status(videoCardById.isSuccess() ? 200 : 400).body(videoCardById);
    }

    @PostMapping
    public HttpEntity<?> addVideoCard(@RequestBody VideoCard videoCard) {
        ApiResponse apiResponse = videoCardService.addVideoCard(videoCard);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editVideoCard(@RequestBody VideoCard videoCard,@PathVariable Integer id) {
        ApiResponse apiResponse = videoCardService.editVideoCard(id, videoCard);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteVideoCard(@PathVariable Integer id) {
        ApiResponse apiResponse = videoCardService.deleteVideoCard(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}
