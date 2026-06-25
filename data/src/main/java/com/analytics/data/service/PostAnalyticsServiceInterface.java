package com.analytics.data.service;

import com.analytics.data.dto.CarPostDTO;
import org.springframework.stereotype.Service;

@Service
public interface PostAnalyticsServiceInterface
{
    void saveDataAnalytics( CarPostDTO carPostDTO );
}