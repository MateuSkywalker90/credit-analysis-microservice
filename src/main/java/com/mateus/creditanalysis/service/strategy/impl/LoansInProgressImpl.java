package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposal;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LoansInProgressImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposal proposal) {
        return otherLoansInProgress() ? 0 : 80;
    }

    // Simulates a lookup of the customer's active loans
    private boolean otherLoansInProgress() {
        return new Random().nextBoolean();
    }
}
