package com.mateus.creditanalysis.service;

import com.mateus.creditanalysis.domain.Proposal;
import com.mateus.creditanalysis.exceptions.StrategyException;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAnalysisService {

    static final int APPROVAL_THRESHOLD = 350;

    private final List<ScoreCalculation> scoreCalculationList;

    private final RabbitMQNotificationService rabbitMQNotificationService;

    private final String exchangeConcludedProposal;

    public CreditAnalysisService(List<ScoreCalculation> scoreCalculationList,
                                 RabbitMQNotificationService rabbitMQNotificationService,
                                 @Value("${rabbitmq.concludedproposal.exchange}") String exchangeConcludedProposal) {
        this.scoreCalculationList = scoreCalculationList;
        this.rabbitMQNotificationService = rabbitMQNotificationService;
        this.exchangeConcludedProposal = exchangeConcludedProposal;
    }

    public void analyze(Proposal proposal) {
        try {
            int score = scoreCalculationList.stream().mapToInt(impl -> impl.calculate(proposal)).sum();
            proposal.setApproved(score > APPROVAL_THRESHOLD);
        } catch (StrategyException ex) {
            proposal.setApproved(false);
            proposal.setObservation(ex.getMessage());
        }
        rabbitMQNotificationService.notify(exchangeConcludedProposal, proposal);
    }
}
