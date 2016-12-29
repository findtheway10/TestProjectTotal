package com.kim.daekyung.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class FirstTestFragment extends Fragment {

    private RecyclerView firstFragmentRecyclerView;
    private EditText firstFragmentEditText;
    private FirstFragmentRecyclerViewAdapter firstFragmentRecyclerViewAdapter;

    private LinearLayoutManager linearLayoutManager;

    private ArrayList<String> firstFragmentDataArrayList = new ArrayList<String>();

    public FirstTestFragment() {

    }

    public static FirstTestFragment newInstance() {
        FirstTestFragment fragment = new FirstTestFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first_test, container, false);

        Button firstFragmentButtonInput = (Button) view.findViewById(R.id.fragment_first_button_input);
        Button firstFragmentButtonClear = (Button) view.findViewById(R.id.fragment_first_button_clear);
        Button firstFragmentButtonExecute = (Button) view.findViewById(R.id.fragment_first_button_execute);

        firstFragmentEditText = (EditText) view.findViewById(R.id.fragment_first_edit_text);
        firstFragmentRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_first_recycler_view);


        firstFragmentRecyclerViewAdapter = new FirstFragmentRecyclerViewAdapter(
                getActivity(), firstFragmentDataArrayList, R.layout.item_first_fragment_recycler_view);

        firstFragmentRecyclerView.setAdapter(firstFragmentRecyclerViewAdapter);

        //매니져 생성
        linearLayoutManager = new LinearLayoutManager(getActivity());

        //orientation 설정
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //매니져 set
        firstFragmentRecyclerView.setLayoutManager(linearLayoutManager);

        firstFragmentButtonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstFragmentDataArrayList.add(firstFragmentEditText.getText().toString());
                firstFragmentEditText.setText("");
                firstFragmentRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        firstFragmentButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstFragmentDataArrayList.clear();
                firstFragmentRecyclerViewAdapter.notifyDataSetChanged();
            }
        });


        firstFragmentButtonExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //메세지 계산 클래스 생성
                LikesMessage likesMessage = new LikesMessage();

                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());

                //메세지 계산 후 출력
                alert_confirm.setMessage(likesMessage.getLikesMessageResult(firstFragmentDataArrayList));

                alert_confirm.setPositiveButton(getString(R.string.dialog_button_ok), null);
                AlertDialog alert = alert_confirm.create();
                alert.setTitle(getString(R.string.title_like_message));
                alert.show();


            }
        });



        return view;
    }

    public class FirstFragmentRecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<String> stringDataArrayList;
        private Context mContext;
        private int layoutResource;

        public FirstFragmentRecyclerViewAdapter(Context context, ArrayList<String> arrayList, int layout) {
            stringDataArrayList = arrayList;
            mContext = context;
            layoutResource = layout;

        }

        //각 아이템을 표현할 위젯들(홀더)을 생성
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).
                    inflate(layoutResource, parent, false);

            FirstFragmentRecyclerViewAdapter.ViewHolder viewHolder
                    = new FirstFragmentRecyclerViewAdapter.ViewHolder(itemView);

            viewHolder.itemView.setTag(viewHolder);


            return viewHolder;

        }


        @Override
        public void onBindViewHolder(
                RecyclerView.ViewHolder holder, final int position) {

            ViewHolder viewHolder;

            viewHolder = (ViewHolder) holder;

            viewHolder.firstFragmentItemTextViewMessage.setText(stringDataArrayList.get(position));


        }

        @Override
        public int getItemCount() {
            return stringDataArrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView firstFragmentItemTextViewMessage;

            public ViewHolder(View itemView) {
                super(itemView);

                firstFragmentItemTextViewMessage
                        = (TextView) itemView.findViewById(R.id.first_fragment_recycler_view_item_text_view);

            }
        }
    }
}
