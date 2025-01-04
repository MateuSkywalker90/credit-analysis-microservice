package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.constant.ConstantMessage;
import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.exceptions.StrategyException;
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
            throw new StrategyException(String.format(ConstantMessage.BAD_CREDIT, proposals.getUsuario().getNome()));
        }
        return 100;
    }

    private boolean badCredit() {
        return new Random().nextBoolean();
    }
}
