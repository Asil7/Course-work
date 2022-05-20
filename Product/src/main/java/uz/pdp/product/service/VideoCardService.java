package uz.pdp.product.service;

//Asilbek Fayzullayev 17.05.2022 16:06   

import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import uz.pdp.product.common.ApiResponse;
import uz.pdp.product.model.VideoCard;
import uz.pdp.product.repository.VideoCardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VideoCardService {
    @Autowired
    VideoCardRepository videoCardRepository;

    public ApiResponse getAllVideoCard() {
        List<VideoCard> all = videoCardRepository.findAll();
        if (all.size() == 0) {
            return new ApiResponse("List is empty", false);
        }
        return new ApiResponse("Success", true);
    }

    public ApiResponse getVideoCardById(Integer id) {
        Optional<VideoCard> byId = videoCardRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("VideoCard not found", false);
        }
        return new ApiResponse("Success", true, byId);
    }

    public ApiResponse addVideoCard(VideoCard videoCard) {
        try {
            VideoCard save = videoCardRepository.save(videoCard);
            return new ApiResponse("Successfully saved", true, save);
        } catch (Exception e) {
            return new ApiResponse("Maybe video card already exist", false);
        }
    }

    public ApiResponse editVideoCard(Integer id, VideoCard videoCard) {
        Optional<VideoCard> byId = videoCardRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Video card not found", false);
        }
        try {
            VideoCard newVideoCard = byId.get();
            newVideoCard.setName(videoCard.getName());
            newVideoCard.setMemoryType(videoCard.getMemoryType());
            newVideoCard.setAmountOfMemory(videoCard.getAmountOfMemory());
            VideoCard save = videoCardRepository.save(newVideoCard);
            return new ApiResponse("Successfully edited", true, save);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse deleteVideoCard(Integer id) {
        try {
            videoCardRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Not found", false);
        }
    }


}
