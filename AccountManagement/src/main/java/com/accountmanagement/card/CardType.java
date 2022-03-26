package com.accountmanagement.card;

public enum CardType {
    VIRTUAL("Virtual"),
    PHYSICAL("Physical");

    private final String cardType;

    CardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }
}
