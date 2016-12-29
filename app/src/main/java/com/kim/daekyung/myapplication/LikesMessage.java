package com.kim.daekyung.myapplication;

import java.util.ArrayList;

/**
 * 문자열을 받아 결과를 출력하는 클레스
 */

public class LikesMessage {

    //생성자
    public LikesMessage() {
    }

    //문자 배열 입력시 결과 출력 함수
    public String getLikesMessageResult(ArrayList<String> dataArrayList){

        //기본값
        String resultMessage = "no one likes this";

        //값이 1개 일 경우
        if(dataArrayList.size() == 1){

            resultMessage = dataArrayList.get(0) + " likes this";

        }else if(dataArrayList.size() == 2) { //값이 2개인 경우

            resultMessage = dataArrayList.get(0) + " and " + dataArrayList.get(1) + " likes this";

        }else if(dataArrayList.size() == 3){ //값이 3개인 경우

            resultMessage = dataArrayList.get(0) +", " + dataArrayList.get(1) +
                    " and " + dataArrayList.get(2) + " likes this";
        }else if(dataArrayList.size() > 3) { //값이 3개 이상인 경우


            resultMessage = dataArrayList.get(0) +", " + dataArrayList.get(1) +
                    " and " + (dataArrayList.size() - 2) + " other likes this";

        }

        return resultMessage;
    }
}
