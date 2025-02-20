package com.maptist.mappride.mappride.place;

import com.maptist.mappride.mappride.place.dto.PlaceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;



    public List<PlaceResponseDto> findPlacesByCategory(Long categoryId) {
        return placeRepository.findPlacesByCategoryId(categoryId);
    }


}
