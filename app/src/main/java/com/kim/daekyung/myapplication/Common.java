package com.kim.daekyung.myapplication;


public class Common {

    //카드 숫자
    public static final int ACE = 14;
    public static final int KING = 13;
    public static final int QUEEN = 12;
    public static final int JACK = 11;

    //카드 타입
    public static final int TYPE_CLUB = 1;
    public static final int TYPE_SPADE = 2;
    public static final int TYPE_DIAMOND = 3;
    public static final int TYPE_HEART = 4;

    //카드 패 랭크
    public static final int STRAIGHT_FLUSH = 11;
    public static final int BACK_STRAIGHT_FLUSH = 10;
    public static final int FOUR_OF_A_KIND = 9;
    public static final int FULL_HOUSE = 8;
    public static final int FLUSH = 7;
    public static final int STRAIGHT = 6;
    public static final int BACK_STRAIGHT = 5;
    public static final int THREE_OF_A_KIND = 4;
    public static final int TWO_PAIRS = 3;
    public static final int ONE_PAIR = 2;
    public static final int HIGH_CARD = 1;

    //카드패 이름
    public static final String STRAIGHT_FLUSH_TEXT = "Straight Flush";
    public static final String FOUR_OF_A_KIND_TEXT = "Four of a Kind";
    public static final String FULL_HOUSE_TEXT = "Full House";
    public static final String FLUSH_TEXT = "Flush";
    public static final String STRAIGHT_TEXT = "Straight";
    public static final String THREE_OF_A_KIND_TEXT = "Three of a Kind";
    public static final String TWO_PAIRS_TEXT = "Two Pairs";
    public static final String ONE_PAIR_TEXT = "One Pair";
    public static final String HIGH_CARD_TEXT = "High Card";

    //승리 판별
    public static final int FIRST_PLAYER_WIN = 1;
    public static final int SECOND_PLAYER_WIN = 2;
    public static final int DRAW = 3;


}
