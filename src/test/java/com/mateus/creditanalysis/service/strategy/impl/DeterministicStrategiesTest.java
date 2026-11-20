package com.mateus.creditanalysis.service.strategy.impl;

import com.mateus.creditanalysis.domain.Proposal;
import com.mateus.creditanalysis.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeterministicStrategiesTest {

    @Test
    void incomeGreaterThanRequestedAmountScores100() {
        assertThat(new SalaryGreaterThanLoanImpl().calculate(proposal(5000.0, 4000.0, 24))).isEqualTo(100);
    }

    @Test
    void incomeNotGreaterThanRequestedAmountScoresZero() {
        assertThat(new SalaryGreaterThanLoanImpl().calculate(proposal(5000.0, 5000.0, 24))).isZero();
    }

    @Test
    void paymentTermUnder120MonthsScores80() {
        assertThat(new ShortPaymentTermImpl().calculate(proposal(5000.0, 4000.0, 119))).isEqualTo(80);
    }

    @Test
    void paymentTermOf120MonthsOrMoreScoresZero() {
        assertThat(new ShortPaymentTermImpl().calculate(proposal(5000.0, 4000.0, 120))).isZero();
    }

    private Proposal proposal(double income, double requestedAmount, int paymentTerm) {
        User user = new User();
        user.setFirstName("Maria");
        user.setIncome(income);

        Proposal proposal = new Proposal();
        proposal.setUser(user);
        proposal.setRequestedAmount(requestedAmount);
        proposal.setPaymentTerm(paymentTerm);
        return proposal;
    }
}
