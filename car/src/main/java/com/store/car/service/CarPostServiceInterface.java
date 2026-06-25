package com.store.car.service;

import com.store.car.dto.CarPostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarPostServiceInterface
{
    void newPostDetails( CarPostDTO carPostDTO );
    List<CarPostDTO> getCarSales();
    void changeCarSale( CarPostDTO carPostDTO, Long id );
    void removeCarSale( Long id );
}
