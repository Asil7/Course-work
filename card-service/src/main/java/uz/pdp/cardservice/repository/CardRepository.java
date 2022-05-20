package uz.pdp.cardservice.repository;

//Asilbek Fayzullayev 18.05.2022 15:58   

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cardservice.model.Card;
import uz.pdp.cardservice.model.Status;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Card findAllByUserIdAndStatus(Integer userId, Status status);
    Card findByUserId(Integer userId);

    List<Card> findAllByUserId(Integer userId);


}
