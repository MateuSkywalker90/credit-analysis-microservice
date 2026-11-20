package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.constant.ConstantMessage;
import com.mateus.creditanalysis.domain.Proposal;
import com.mateus.creditanalysis.exceptions.StrategyException;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(2)
@Component
public class CustomerScoreImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposal proposal) {
        int score = score();

        if (score < 300) {
            throw new StrategyException(String.format(ConstantMessage.POOR_SCORE, proposal.getUser().getFirstName()));
        } else if (score <= 450) {
            return 150;
        } else if (score <= 600) {
            return 180;
        } else {
            return 220;
        }
    }

    // Simulates a call to an external credit score provider
    private int score() {
        return new Random().nextInt(0, 1000);
    }
}
