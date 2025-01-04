package com.mateus.creditanalysis.service;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.exceptions.StrategyException;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAnalysisService {

    @Autowired
    private List<ScoreCalculation> scoreCalculationList;

    @Autowired
    private RabbitMQNotificationService rabbitMQNotificationService;

    public void analysis(Proposals proposals) {
        try {
            boolean approved = scoreCalculationList.stream()
                    .mapToInt(impl -> impl.calculate(proposals)).sum() > 350;
            proposals.setAprovada(approved);
        } catch (StrategyException ex) {
            proposals.setAprovada(false);
        }
    }

}
