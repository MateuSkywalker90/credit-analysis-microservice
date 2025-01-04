package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;

public class ShortPaymentTermImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposals proposals) {
        return proposals.getPrazoPagamento() < 120 ? 80 : 0;
    }
}
