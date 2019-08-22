package com.example.farmapp.itemActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.farmapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SecondItemTab extends Fragment {

    private SharedViewModel viewModel;
    private TextView textView;
    LinearLayout linearLayout;
    ArrayList<String> arrayList = new ArrayList<>();

    HashMap<String,Integer> hashmapItem = new HashMap<>();
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_item_tab,container,false);
        linearLayout = view.findViewById(R.id.linear_item_layout);

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
                    valueOfHashmap(arrayList);
                    linearLayout.removeAllViews();
                    for(int i=0;i<arrayList.size();i++) {
                        final TextView nTextView = new TextView(getActivity());
                        nTextView.setText(arrayList.get(i));
                        nTextView.setTextSize(16);
                        nTextView.setTextColor(Color.WHITE);
                        nTextView.setPadding(4,4,4,4);


                        linearLayout.addView(nTextView);
                    }
                }


            });

        }

        private void valueOfHashmap(ArrayList<String> arrayItem) {
            HashMap<String,Integer> counts = new HashMap<>();

            for(String str : arrayItem ) {
                if(counts.containsKey(str)) {
                    counts.put(str,counts.get(str)+1);
                } else {
                    counts.put(str,1);
                }

            }
            for (Map.Entry<String,Integer> entry : counts.entrySet()) {
                System.out.println(entry.getKey() +  ":" + entry.getValue());
            }

        }
}
