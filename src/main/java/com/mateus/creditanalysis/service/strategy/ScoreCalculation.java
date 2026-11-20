package com.mateus.creditanalysis.service.strategy;

import com.mateus.creditanalysis.domain.Proposal;

public interface ScoreCalculation {

    int calculate(Proposal proposal);
}
