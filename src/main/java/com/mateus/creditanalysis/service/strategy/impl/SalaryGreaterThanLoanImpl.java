package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposals;
import com.mateus.creditanalysis.service.strategy.ScoreCalculation;

public class SalaryGreaterThanLoanImpl implements ScoreCalculation {

    @Override
    public int calculate(Proposals proposals) {
        return salaryGreaterThanLoan(proposals) ? 100 : 0;
    }

    private boolean salaryGreaterThanLoan(Proposals proposals) {
        return proposals.getUsuario().getRenda() > proposals.getValorSolicitado();
    }
}
