package com.kim.daekyung.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SecondTestFragment extends Fragment {

    private EditText inputHtmlEditText;
    private TextView resultReverseTextView;

    public SecondTestFragment() {
    }

    public static SecondTestFragment newInstance() {
        SecondTestFragment fragment = new SecondTestFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_test, container, false);

        inputHtmlEditText = (EditText) view.findViewById(R.id.fragment_second_edit_text);
        resultReverseTextView = (TextView) view.findViewById(R.id.fragment_second_text_view);

        Button reverseHtmlButton = (Button) view.findViewById(R.id.fragment_second_button_execute);

        reverseHtmlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //html 뒤집기용 클래스 생성
                ReverseWithTag reverseWithTag = new ReverseWithTag();

                //값 계산 후 뷰에 반영
                resultReverseTextView.setText(reverseWithTag.reverseHtml(inputHtmlEditText.getText().toString()));

            }
        });

        return view;
    }

}
