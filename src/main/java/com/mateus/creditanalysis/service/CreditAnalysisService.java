package com.mateus.creditanalysis.service;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAnalysisService {

    @Autowired
    private List<ScoreCalculation> scoreCalculationList;

    public void analysis(Proposals proposals) {
        int score = scoreCalculationList.stream().mapToInt(impl -> impl.calculate(proposals)).sum();
    }
}
