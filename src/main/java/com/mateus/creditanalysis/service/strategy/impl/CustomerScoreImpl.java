package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;

import java.util.Random;

public class CustomerScoreImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposals proposals) {
        int score = score();

        if (score <= 300) {
            throw new RuntimeException("Poor score");
        } else if (score <= 450) {
            return 150;
        } else if (score <= 600) {
            return 180;
        } else {
            return 220;
        }

    }

    private int score() {
        return new Random().nextInt(0, 1000);
    }
}
