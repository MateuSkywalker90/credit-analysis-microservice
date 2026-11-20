package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.constant.ConstantMessage;
import com.mateus.creditanalysis.domain.Proposal;
import com.mateus.creditanalysis.exceptions.StrategyException;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component
public class BadCreditImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposal proposal) {
        if (badCredit()) {
            throw new StrategyException(String.format(ConstantMessage.BAD_CREDIT, proposal.getUser().getFirstName()));
        }
        return 100;
    }

    // Simulates a call to an external credit bureau
    private boolean badCredit() {
        return new Random().nextBoolean();
    }
}
