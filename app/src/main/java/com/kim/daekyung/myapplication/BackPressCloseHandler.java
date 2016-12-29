package com.kim.daekyung.myapplication;

import android.app.Activity;
import android.widget.Toast;



public class BackPressCloseHandler {

    private final static int BACK_KEY_INTERVAL_TIME = 2000;

    //시간체크용 변수
    private long backKeyPressedTime = 0;

    //토스트 제어를 하기위해 객체 선언
    private Toast toast;

    private Activity activity;

    //생성자
    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    //두번 눌럿을 경우 처리
    public void onBackPressed() {
        //처음 눌럿을 경우
        if (System.currentTimeMillis() > backKeyPressedTime + BACK_KEY_INTERVAL_TIME) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        //두번 째 눌럿을 경우
        if (System.currentTimeMillis() <= backKeyPressedTime + BACK_KEY_INTERVAL_TIME) {
            activity.finish();
            toast.cancel();
        }
    }

    //토스트 출력용 메서드
    private void showGuide() {
        toast = Toast.makeText(activity, activity.getResources().getString(R.string.toast_back_key), Toast.LENGTH_SHORT);
        toast.show();
    }
}