package com.mateus.creditanalysis.listener;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.service.CreditAnalysisService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PendingAnalysisListener {

    private final CreditAnalysisService creditAnalysisService;

    public PendingAnalysisListener(CreditAnalysisService creditAnalysisService) {
        this.creditAnalysisService = creditAnalysisService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void proposalUnderAnalysis(Proposals proposals) {
        creditAnalysisService.analysis(proposals);
    }
}
