package org.mik.first.domain;

public enum JobType {
    SELL(1),
    BUY(-1);

    public final int multiplier;

    JobType(int multiplier) {
        this.multiplier=multiplier;
    }
}
