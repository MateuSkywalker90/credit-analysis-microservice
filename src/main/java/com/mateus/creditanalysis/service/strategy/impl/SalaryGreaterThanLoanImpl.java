package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposal;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;
import org.springframework.stereotype.Component;

@Component
public class SalaryGreaterThanLoanImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposal proposal) {
        return incomeGreaterThanRequestedAmount(proposal) ? 100 : 0;
    }

    private boolean incomeGreaterThanRequestedAmount(Proposal proposal) {
        return proposal.getUser().getIncome() > proposal.getRequestedAmount();
    }
}
