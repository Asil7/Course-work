package uz.pdp.product.controller;

//Asilbek Fayzullayev 17.05.2022 16:38   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.product.common.ApiResponse;
import uz.pdp.product.model.Cpu;
import uz.pdp.product.service.CpuServise;

@RestController
@RequestMapping("/cpu")
public class CpuController {
    @Autowired
    CpuServise cpuServise;


    @GetMapping
    public HttpEntity<?> getAllCpu() {
        ApiResponse allCpu = cpuServise.getAllCpu();
        return ResponseEntity.status(allCpu.isSuccess() ? 200 : 400).body(allCpu);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCpuById(@PathVariable Integer id) {
        ApiResponse cpuById = cpuServise.getCpuById(id);
        return ResponseEntity.status(cpuById.isSuccess() ? 200 : 400).body(cpuById);
    }

    @PostMapping
    public HttpEntity<?> addCpu(@RequestBody Cpu cpu) {
        ApiResponse apiResponse = cpuServise.addCpu(cpu);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCpu(@PathVariable Integer id, @RequestBody Cpu cpu) {
        ApiResponse apiResponse = cpuServise.editCpu(cpu, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCpu(@PathVariable Integer id) {
        ApiResponse apiResponse = cpuServise.deleteCpu(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}
