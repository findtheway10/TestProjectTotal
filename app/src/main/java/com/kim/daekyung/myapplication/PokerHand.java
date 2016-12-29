package com.kim.daekyung.myapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 포커 계산 클래스
 */

public class PokerHand {

    private Context context;

    //생성자
    public PokerHand(Context context) {
        this.context = context;
    }

    private class PokerPlayer {

        private ArrayList<PokerCard> playerPokerCardList = new ArrayList<PokerCard>();
        private int[] cardCountArray = new int[15];


        public PokerPlayer() {

        }

        public ArrayList<PokerCard> getPlayerCardArrayList(){

            return playerPokerCardList;
        }

        public boolean setPlayerCard(String playerCard) {

            StringTokenizer playerTokenizer = new StringTokenizer(playerCard, " ");
            boolean isUsableCard = false;

            //카드 장수가 5장이 아닐경우 애러
            if (playerTokenizer.countTokens() == 5) {

                for (int i = 0; i < 5; i++) {

                    String tempCardString = playerTokenizer.nextToken().toLowerCase();

                    //정규식으로 카드 이외의 문자가 들어온지 검사
                    if (tempCardString.matches("^(a|k|q|j|2|3|4|5|6|7|8|9|10)*(s|c|d|h)$")) {

                        int tempCardNumber;
                        int tempCardType = 0;

                        //알파벳 카드 숫자로 변환
                        if (tempCardString.charAt(0) == 'a') {

                            tempCardNumber = Common.ACE;

                        } else if (tempCardString.charAt(0) == 'k') {

                            tempCardNumber = Common.KING;

                        } else if (tempCardString.charAt(0) == 'q') {

                            tempCardNumber = Common.QUEEN;

                        } else if (tempCardString.charAt(0) == 'j') {

                            tempCardNumber = Common.JACK;

                        } else {

                            //숫자 파싱 10이들어오면 카드가 3자리임으로 예외처리
                            if (tempCardString.length() == 3) {
                                tempCardNumber = 10;
                            } else {

                                tempCardNumber = Character.getNumericValue(tempCardString.charAt(0));
                            }

                        }

                        char tempCardTypeChar;

                        //숫자 파싱 10이들어오면 카드가 3자리임으로 예외처리
                        if (tempCardString.length() == 3) {
                            tempCardTypeChar = tempCardString.charAt(2);
                        } else {
                            tempCardTypeChar = tempCardString.charAt(1);
                        }

                        //알파벳 카드 숫자로 변환
                        if (tempCardTypeChar == 'c') {

                            tempCardType = Common.TYPE_CLUB;

                        } else if (tempCardTypeChar == 's') {

                            tempCardType = Common.TYPE_SPADE;

                        } else if (tempCardTypeChar == 'd') {

                            tempCardType = Common.TYPE_DIAMOND;

                        } else if (tempCardTypeChar == 'h') {

                            tempCardType = Common.TYPE_HEART;

                        } else {

                            //이외의 문자열은 오류이므로 리턴
                            return false;

                        }

                        //배열에 추가
                        playerPokerCardList.add(new PokerCard(tempCardNumber, tempCardType));

                        isUsableCard = true;


                    } else {
                        return false;
                    }

                }


            } else {

                return false;

            }

            //스트레이트 검사하기위해 내림차순 정렬
            Collections.sort(playerPokerCardList, new Comparator<PokerCard>() {
                public int compare(PokerCard obj1, PokerCard obj2) {
                    return (obj1.cardNumber > obj2.cardNumber) ? -1 : (obj1.cardNumber > obj2.cardNumber) ? 1 : 0;
                }
            });


            return isUsableCard;
        }


        public int getCardRank() {

            int cardRank = Common.HIGH_CARD;

            boolean isFlush = false;
            boolean isStraight = false;
            boolean isBackStraight = false; //제일낮은 스트레이트 체크
            boolean isThreeOfAKind = false;
            int countPair = 0;

            int tempCardType = playerPokerCardList.get(0).cardType; //첫번째 그림
            int tempTopCardNumber = playerPokerCardList.get(0).cardNumber; //최고 높은카드

            //플러시 체크
            if (playerPokerCardList.get(1).cardType == tempCardType
                    && playerPokerCardList.get(2).cardType == tempCardType
                    && playerPokerCardList.get(3).cardType == tempCardType
                    && playerPokerCardList.get(4).cardType == tempCardType) {

                isFlush = true;
            } else {

                isFlush = false;
            }

            //스트레이트 체크
            if ((playerPokerCardList.get(1).cardNumber + 1) == tempTopCardNumber
                    && (playerPokerCardList.get(2).cardNumber + 2) == tempTopCardNumber
                    && (playerPokerCardList.get(3).cardNumber + 3) == tempTopCardNumber
                    && (playerPokerCardList.get(4).cardNumber + 4) == tempTopCardNumber) {


                isStraight = true;
            } else if (playerPokerCardList.get(0).cardNumber == 14) { //백스트레이트 체크

                if (playerPokerCardList.get(1).cardNumber == 5
                        && (playerPokerCardList.get(2).cardNumber + 1) == 5
                        && (playerPokerCardList.get(3).cardNumber + 2) == 5
                        && (playerPokerCardList.get(4).cardNumber + 3) == 5) {

                    isStraight = true;
                    isBackStraight = true;
                }

            } else {

                isStraight = false;
            }

            //스트레이트 플러시 체크
            if (isFlush == true && isStraight == true) {

                //백 스트레이트플러시 체크
                if (isBackStraight == true) {

                    //종료
                    return Common.BACK_STRAIGHT_FLUSH;

                } else {

                    //종료
                    return Common.STRAIGHT_FLUSH;
                }


            } else {

                //갯수세는 배열에 값 추가
                int tempArraySize = playerPokerCardList.size();
                for (int i = 0; i < tempArraySize; i++) {

                    cardCountArray[playerPokerCardList.get(i).cardNumber]++;

                }

                //갯수 관련 카드 등급 검사
                for (int j = 0; j < cardCountArray.length; j++) {


                    //포카드
                    if (cardCountArray[j] == 4) {

                        //종료
                        return Common.FOUR_OF_A_KIND;


                    } else if (cardCountArray[j] == 3) { //트리플

                        //트리플 체크
                        isThreeOfAKind = true;

                    } else if (cardCountArray[j] == 2) { //페어

                        //페어 카운트 증가
                        countPair++;

                    }
                }
            }

            //풀하우스
            if (isThreeOfAKind == true && countPair > 0) {

                //종료
                return Common.FULL_HOUSE;

            } else if (isFlush == true) { //플러시

                //종료
                return Common.FLUSH;

            } else if (isStraight == true) { //스트레이트

                //백스트레이트 체크
                if (isBackStraight == true) {

                    //종료
                    return Common.BACK_STRAIGHT;
                } else {
                    //종료
                    return Common.STRAIGHT;
                }


            } else if (isThreeOfAKind == true) { //트리플

                //종료
                return Common.THREE_OF_A_KIND;

            } else if (countPair >= 2) { //투페어

                return Common.TWO_PAIRS;

            } else if (countPair == 1) { //원페어

                return Common.ONE_PAIR;

            } else { //하이카드

                return Common.HIGH_CARD;

            }

        }


    }

    private class PokerCard {

        private int cardNumber;
        private int cardType;

        public PokerCard(int cardNumber, int cardType) {
            this.cardNumber = cardNumber;
            this.cardType = cardType;
        }
    }

    private String getPokerName(int rank) {


        switch (rank) {

            case Common.STRAIGHT_FLUSH:

                return Common.STRAIGHT_FLUSH_TEXT;

            case Common.BACK_STRAIGHT_FLUSH:

                return Common.STRAIGHT_FLUSH_TEXT;

            case Common.FOUR_OF_A_KIND:

                return Common.FOUR_OF_A_KIND_TEXT;

            case Common.FULL_HOUSE:

                return Common.FULL_HOUSE_TEXT;

            case Common.FLUSH:

                return Common.FLUSH_TEXT;

            case Common.STRAIGHT:

                return Common.STRAIGHT_TEXT;

            case Common.BACK_STRAIGHT:

                return Common.STRAIGHT_TEXT;

            case Common.THREE_OF_A_KIND:

                return Common.THREE_OF_A_KIND_TEXT;

            case Common.TWO_PAIRS:

                return Common.TWO_PAIRS_TEXT;

            case Common.ONE_PAIR:

                return Common.ONE_PAIR_TEXT;

            case Common.HIGH_CARD:

                return Common.HIGH_CARD_TEXT;

            default:

                return Common.HIGH_CARD_TEXT;

        }

    }


    //랭크가 같을 경우 상새 패 비교
    private int checkDraw(ArrayList<PokerCard> firstPlayerCard, ArrayList<PokerCard> secondPlayerCard){

        int tempArrayListSize = firstPlayerCard.size();

        for(int i=0; i < tempArrayListSize; i++){

            //첫번째 카드 (탑카드) 부터 숫자 비교
            if(firstPlayerCard.get(i).cardNumber == secondPlayerCard.get(i).cardNumber){

                //비긴경우 계속 다음카드 비교

            }else if(firstPlayerCard.get(i).cardNumber > secondPlayerCard.get(i).cardNumber){

                //첫번째 유저 승리
                return Common.FIRST_PLAYER_WIN;

            }else {

                //두번째유저 승리
                return Common.SECOND_PLAYER_WIN;
            }

        }

        //반복문 빠져나온경우 비김
        return Common.DRAW;
    }

    //포커 실행
    public String executePoker(String firstPlayerCards, String secondPlayerCards) {

        //결과 문자열
        String resultString = "";

        //첫번째 플레이어 생성
        PokerPlayer firstPokerPlayer = new PokerPlayer();

        //두번째 플레이어 생성
        PokerPlayer secondPokerPlayer = new PokerPlayer();

        //유효성검사용 변수
        boolean isUsableCardFirstPlayer = false;
        boolean isUsableCardSecondPlayer = false;

        //첫번째 플레이어 카드 할당
        isUsableCardFirstPlayer = firstPokerPlayer.setPlayerCard(firstPlayerCards);

        //두번째 플레이어 카드 할당
        isUsableCardSecondPlayer = secondPokerPlayer.setPlayerCard(secondPlayerCards);

        int firstPlayerRank;
        int secondPlayerRank;

        if (isUsableCardFirstPlayer == true && isUsableCardSecondPlayer == true) {

            firstPlayerRank = firstPokerPlayer.getCardRank();
            secondPlayerRank = secondPokerPlayer.getCardRank();

            String winner;

            if (firstPlayerRank == secondPlayerRank) {

                int checkWinner =
                checkDraw(firstPokerPlayer.getPlayerCardArrayList(), secondPokerPlayer.getPlayerCardArrayList());

                if(checkWinner == Common.FIRST_PLAYER_WIN){
                    //첫번째 플레이어 승리
                    winner = context.getString(R.string.fragment_third_winner)
                            + context.getString(R.string.fragment_third_text_view_player_1);
                }else if(checkWinner == Common.SECOND_PLAYER_WIN){
                    //두번째 플레이어 승리
                    winner = context.getString(R.string.fragment_third_winner)
                            + context.getString(R.string.fragment_third_text_view_player_2);
                }else {
                    //비겼음
                    winner = context.getString(R.string.fragment_third_draw);
                }


            } else if (firstPlayerRank > secondPlayerRank) {

                //첫번째 플레이어 승리
                winner = context.getString(R.string.fragment_third_winner)
                        + context.getString(R.string.fragment_third_text_view_player_1);
            } else {

                //두번째 플레이어 승리
                winner = context.getString(R.string.fragment_third_winner)
                        + context.getString(R.string.fragment_third_text_view_player_2);

            }


            resultString = context.getString(R.string.fragment_third_text_view_player_1) + "\n"
                    + firstPlayerCards + "\n"
                    + getPokerName(firstPlayerRank) + "\n \n"

                    + context.getString(R.string.fragment_third_text_view_player_2) + "\n"
                    + secondPlayerCards + "\n"
                    + getPokerName(secondPlayerRank) + "\n\n"

                    + winner;

        } else {

            resultString = context.getString(R.string.fragment_third_wrong_card);

        }

        return resultString;
    }

}
