package uz.pdp.cardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cardservice.model.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Integer> {
}
