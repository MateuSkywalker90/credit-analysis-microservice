package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;

import java.util.Random;

public class LoansInProgressImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposals proposals) {
        return otherLoansInProgress() ? 0 : 80;
    }

    private boolean otherLoansInProgress() {
        return new Random().nextBoolean();
    }
}
