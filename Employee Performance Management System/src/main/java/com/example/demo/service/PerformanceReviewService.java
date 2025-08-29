package com.example.demo.service;

import com.example.demo.entity.PerformanceReview;
import com.example.demo.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceReviewService implements PerformanceReviewServiceInterface {
    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;

    @Override
    public List<PerformanceReview> getLastThreePerformanceReviewByEmployeeId(Long employeeId)
    {
        return performanceReviewRepository.findTop3ByEmployeeIdOrderByReviewDateDesc(employeeId);
    }
}

