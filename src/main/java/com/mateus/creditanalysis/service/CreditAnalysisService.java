package com.mateus.creditanalysis.service;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.exceptions.StrategyException;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CreditAnalysisService {

    private List<ScoreCalculation> scoreCalculationList;

    private RabbitMQNotificationService rabbitMQNotificationService;

    @Value("${rabbitmq.concludedproposal.exchange}")
    private String exchangeConcludedProposal;

    public void analysis(Proposals proposals) {
        try {
            int score = scoreCalculationList.stream().mapToInt(impl -> impl.calculate(proposals)).sum();
            proposals.setAprovada(score > 350);
        } catch (StrategyException ex) {
            proposals.setAprovada(false);
            proposals.setObservacao(ex.getMessage());
        }
        rabbitMQNotificationService.notify(exchangeConcludedProposal, proposals);
    }

}
