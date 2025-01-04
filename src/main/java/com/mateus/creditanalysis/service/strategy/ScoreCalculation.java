package com.mateus.creditanalysis.service.strategy;

import com.mateus.creditanalysis.domain.Proposals;

public interface ScoreCalculation {

    int calculate(Proposals proposals);
}
