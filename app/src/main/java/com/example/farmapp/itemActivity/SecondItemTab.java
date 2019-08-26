package com.example.farmapp.itemActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.farmapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;

public class SecondItemTab extends Fragment {

    private SharedViewModel viewModel;
    ArrayList<String> arrayList = new ArrayList<>();
    TableLayout tableLayout;

    TreeMap<String,Integer> treeMapItem = new TreeMap<>();
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_item_tab,container,false);
        tableLayout = view.findViewById(R.id.table_layout);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


            viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SharedViewModel.class);
            viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    arrayList.add(s);
                    treeMapItem = valueOfHashmap(arrayList);
                    tableLayout.removeAllViews();

                    for(HashMap.Entry<String,Integer> entry : treeMapItem.entrySet()) {

                        TableRow tr = new TableRow(getActivity());

                        tr.setLayoutParams(getLayoutParams());
                        tr.setWeightSum(2);
                        tr.addView(getTextViewParam(entry.getKey(),0));
                        tr.addView(getTextViewParam(""+entry.getValue(),1));

                        tableLayout.addView(tr);
                    }
                }


            });

        }

        private TreeMap<String,Integer> valueOfHashmap(ArrayList<String> arrayItem) {
            TreeMap<String,Integer> counts = new TreeMap<>();

            for(String str : arrayItem ) {
                if(counts.containsKey(str)) {
                    counts.put(str,counts.get(str)+1);
                } else {
                    counts.put(str,1);
                }
            }
            return counts;
        }

        private TableRow.LayoutParams getLayoutParams() {
            return new TableRow.LayoutParams(
                    0,
                    TableRow.LayoutParams.MATCH_PARENT,1f);
        }

        private TextView getTextViewParam(String value,int flag) {
            TextView textView = new TextView(getActivity());
            if(flag == 1) {
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(getLayoutParams());
                textView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            } else {
                textView.setGravity(Gravity.NO_GRAVITY);
                textView.setLayoutParams(getLayoutParams());
                textView.setBackgroundColor(getResources().getColor(R.color.itemColor));
            }
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            textView.setPadding(8,8,8,8);
            textView.setText(value);

            return textView;
        }


}
