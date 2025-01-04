package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component
public class BadCreditImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposals proposals) {
        if (badCredit()) {
            throw new RuntimeException("Customer has bad credit");
        }
        return 100;
    }

    private boolean badCredit() {
        return new Random().nextBoolean();
    }
}
