package com.analytics.data.service;

import com.analytics.data.dto.CarPostDTO;
import com.analytics.data.entity.BrandAnalytics;
import com.analytics.data.entity.CarModelAnalytics;
import com.analytics.data.entity.CarModelPrice;
import com.analytics.data.repository.BrandAnalyticsRepository;
import com.analytics.data.repository.CarModelAnalyticsRepository;
import com.analytics.data.repository.CarPriceAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostAnalyticsService
    implements
        PostAnalyticsServiceInterface
{
    @Autowired
    private BrandAnalyticsRepository brandAnalyticsRepository;

    @Autowired
    private CarModelAnalyticsRepository carModelAnalyticsRepository;

    @Autowired
    private CarPriceAnalyticsRepository carPriceAnalyticsRepository;

    @Override
    public void saveDataAnalytics( CarPostDTO carPostDTO )
    {
        saveBrandAnalytics( carPostDTO.getBrand() );
        saveCarModelAnalytics( carPostDTO.getModel() );
        saveCarModelPriceAnalytics( carPostDTO.getModel(), carPostDTO.getPrice() );
    }

    private void saveCarModelAnalytics( String model )
    {
        CarModelAnalytics carModelAnalytics = new CarModelAnalytics();

        carModelAnalyticsRepository.findByModel( model )
                                   .ifPresentOrElse( item ->
        {
            item.setPosts( item.getPosts() + 1 );
            carModelAnalyticsRepository.save( item );
        },

        () ->
        {
            carModelAnalytics.setModel( model );
            carModelAnalytics.setPosts( 1L );
            carModelAnalyticsRepository.save( carModelAnalytics );
        } );
    }

    private void saveCarModelPriceAnalytics( String model, Double price )
    {
        CarModelPrice carModelPrice = new CarModelPrice();

        carModelPrice.setModel( model );
        carModelPrice.setPrice( price );

        carPriceAnalyticsRepository.save( carModelPrice );
    }

    private void saveBrandAnalytics(String brand)
    {
        BrandAnalytics brandAnalytics = new BrandAnalytics();

        brandAnalyticsRepository.findByBrand( brand )
                                .ifPresentOrElse( item ->
        {
            item.setPosts( item.getPosts() + 1 );
            brandAnalyticsRepository.save( item );
        },

        () ->
        {
            brandAnalytics.setBrand( brand );
            brandAnalytics.setPosts( 1L );
            brandAnalyticsRepository.save( brandAnalytics );
        } );
    }
}
