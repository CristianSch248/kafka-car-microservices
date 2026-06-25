package com.analytics.data.repository;

import com.analytics.data.entity.BrandAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandAnalyticsRepository
    extends
        JpaRepository<BrandAnalytics, Long>
{
    Optional<BrandAnalytics> findByBrand( String brand );
}