package com.analytics.data.repository;

import com.analytics.data.entity.CarModelPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarPriceAnalyticsRepository
    extends
        JpaRepository<CarModelPrice, Long>
{
}
