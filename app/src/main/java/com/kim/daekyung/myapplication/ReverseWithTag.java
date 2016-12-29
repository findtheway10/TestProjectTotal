package com.kim.daekyung.myapplication;

/**
 * Html 태그를 제외하고 뒤집는 클레스
 */

public class ReverseWithTag {

    //생성자
    public ReverseWithTag() {
    }


    public String reverseHtml(String inputString){

        //결과 출력용 String
        String resultString = "";

        //임시값 저장용 변수 String
        String tempSaveString = "";

        //태그값을 저장중 인지 판별하는 flag
        boolean isSaveTag = false;

        //문자열에서 한 글자씩 접근
        for (int i = 0; i < inputString.length(); i++) {

            //태그 시작 괄호 비교
            if(inputString.charAt(i) == '<'){

                isSaveTag = true;

                //태그 시작 앞에 일반 문자열 뒤집고 앞에 추가 후 전체 결과에 반영
                resultString = reverseString(tempSaveString) + resultString;

                //임시 변수 초기화
                tempSaveString = "<";


            }else if(inputString.charAt(i) == '>'){//끝나는 괄호 비교

                //마지막 괄호 추가
                tempSaveString = tempSaveString + ">";

                //결과에 반영
                resultString = tempSaveString + resultString;

                //임시 변수 초기화
                tempSaveString = "";

                //플래그 초기화
                isSaveTag = false;

            }else { //태그가 아님

                //임시 문자열에 뒤에 추가
                tempSaveString = tempSaveString + inputString.charAt(i);

            }


        }

        //플레그로 마지막 문자열 추가
        if (isSaveTag == false){

            resultString = reverseString(tempSaveString) + resultString;

        }

        return resultString;
    }

    public static String reverseString(String s) {
        return ( new StringBuffer(s) ).reverse().toString();
    }


}
