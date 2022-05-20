package uz.pdp.product.service;

//Asilbek Fayzullayev 17.05.2022 15:39   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.product.common.ApiResponse;
import uz.pdp.product.model.Cpu;
import uz.pdp.product.repository.CpuRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CpuServise {
    @Autowired
    CpuRepository cpuRepository;

    public ApiResponse getAllCpu() {
        List<Cpu> all = cpuRepository.findAll();
        if (all.size() == 0) {
            return new ApiResponse("List empty", false);
        }
        return new ApiResponse("Success", true, all);
    }

    public ApiResponse getCpuById(Integer id) {
        Optional<Cpu> optionalCpu = cpuRepository.findById(id);
        if (!optionalCpu.isPresent()) {
            return new ApiResponse("Cpu not found", false);
        }
        return new ApiResponse("Success", true, optionalCpu);
    }

    public ApiResponse addCpu(Cpu cpu) {
        try {
            Cpu save = cpuRepository.save(cpu);
            return new ApiResponse("Successfully added", true, save);
        } catch (Exception e) {
            return new ApiResponse("Maybe this cpu already exist", false);
        }
    }

    public ApiResponse editCpu(Cpu cpu, Integer id) {
        Optional<Cpu> optionalCpu = cpuRepository.findById(id);
        if (!optionalCpu.isPresent()) {
            return new ApiResponse("Cpu not found", false);
        }
        try {
            Cpu editCpu = optionalCpu.get();
            editCpu.setName(cpu.getName());
            editCpu.setFrequency(cpu.getFrequency());
            Cpu save = cpuRepository.save(editCpu);
            return new ApiResponse("Successfully edited", true, save);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse deleteCpu(Integer id) {
        try {
            cpuRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Cpu not found", false);
        }
    }
}
