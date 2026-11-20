package com.mateus.creditanalysis.service;

import com.mateus.creditanalysis.domain.Proposal;
import com.mateus.creditanalysis.exceptions.StrategyException;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreditAnalysisServiceTest {

    private static final String EXCHANGE = "concluded-proposal.ex";

    @Mock
    private RabbitMQNotificationService notificationService;

    @Test
    void approvesProposalWhenScoreIsAboveThreshold() {
        Proposal proposal = new Proposal();

        service(p -> 200, p -> 151).analyze(proposal);

        assertThat(proposal.getApproved()).isTrue();
        verify(notificationService).notify(EXCHANGE, proposal);
    }

    @Test
    void deniesProposalWhenScoreIsAtOrBelowThreshold() {
        Proposal proposal = new Proposal();

        service(p -> 200, p -> 150).analyze(proposal);

        assertThat(proposal.getApproved()).isFalse();
        assertThat(proposal.getObservation()).isNull();
        verify(notificationService).notify(EXCHANGE, proposal);
    }

    @Test
    void deniesProposalWithReasonWhenAStrategyRejectsIt() {
        Proposal proposal = new Proposal();

        service(p -> 300, p -> { throw new StrategyException("Customer has a bad credit"); }).analyze(proposal);

        assertThat(proposal.getApproved()).isFalse();
        assertThat(proposal.getObservation()).isEqualTo("Customer has a bad credit");
        verify(notificationService).notify(EXCHANGE, proposal);
    }

    private CreditAnalysisService service(ScoreCalculation... strategies) {
        return new CreditAnalysisService(List.of(strategies), notificationService, EXCHANGE);
    }
}
