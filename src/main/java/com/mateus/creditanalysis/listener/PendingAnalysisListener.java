package com.mateus.creditanalysis.listener;

import com.mateus.creditanalysis.domain.Proposals;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PendingAnalysisListener {

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void proposalUnderAnalysis(Proposals proposals) {
        
    }
}
