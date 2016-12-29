package com.kim.daekyung.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ThirdTestFragment extends Fragment {

    private EditText firstPlayerEditText;
    private EditText secondPlayerEditText;
    private TextView pokerResultTextView;

    public ThirdTestFragment() {

    }

    public static ThirdTestFragment newInstance() {
        ThirdTestFragment fragment = new ThirdTestFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_test_fragmnet, container, false);

        firstPlayerEditText = (EditText) view.findViewById(R.id.fragment_third_edit_text_player_1);
        secondPlayerEditText = (EditText) view.findViewById(R.id.fragment_third_edit_text_player_2);
        pokerResultTextView = (TextView) view.findViewById(R.id.fragment_third_text_view_result);

        Button porkerExecuteButton = (Button) view.findViewById(R.id.fragment_third_button_execute);


        porkerExecuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                executePoker(firstPlayerEditText.getText().toString(), secondPlayerEditText.getText().toString());


            }
        });

        return view;
    }

    private void executePoker(String firstPlayer, String secondPlayer) {

        PokerHand pokerhand = new PokerHand(getActivity());

        pokerResultTextView.setText(pokerhand.executePoker(firstPlayer, secondPlayer));




    }

}
