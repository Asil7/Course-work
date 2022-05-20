package uz.pdp.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.product.model.Cpu;

public interface CpuRepository extends JpaRepository<Cpu,Integer> {
}
