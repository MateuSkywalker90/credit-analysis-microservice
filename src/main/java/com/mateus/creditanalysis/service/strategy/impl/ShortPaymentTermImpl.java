package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposal;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.stereotype.Component;

@Component
public class ShortPaymentTermImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposal proposal) {
        return proposal.getPaymentTerm() < 120 ? 80 : 0;
    }
}
