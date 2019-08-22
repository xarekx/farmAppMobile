package com.example.farmapp.itemActivity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.farmapp.R;
import com.example.farmapp.Retrofit.RetrofitClientInstance;
import com.example.farmapp.Retrofit.RetrofitMobData;
import com.example.farmapp.adapters.CustomItemAdapter;
import com.example.farmapp.adapters.GridViewAdapter;
import com.example.farmapp.itemActivity.SharedViewModel;
import com.example.farmapp.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class FirstItemTab extends Fragment {

    View view;
    private ArrayList<String> myItems = new ArrayList<>();
    private ArrayList<Integer> myPhotos = new ArrayList<>();
    private String TAG = "FristItemTab";
    private SharedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String mobInfo = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("mobPosition");
        String mobPosition = mobInfo.substring(1,2);
        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SharedViewModel.class);
        view = inflater.inflate(R.layout.first_item_tab, container, false);
        GridView gv = view.findViewById(R.id.gridViewFragment1);

        getAllItemsByMobId(mobPosition,this.myItems,this.myPhotos, gv);






        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setText(""+parent.getItemAtPosition(position));
                Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), ""+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    public void getAllItemsByMobId(String id, final ArrayList<String> myItems, final ArrayList<Integer> myPhotos, final GridView gv) {

        RetrofitMobData retrofitMobData = RetrofitClientInstance.getRetrofitInstance().create(RetrofitMobData.class);

        Call<List<Item>> call = retrofitMobData.getAllItemsByMobId(id);

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                Log.d(TAG, "onResponse: Success");
                List<Item> resp = response.body();
                for (int i = 0; i < resp.size(); i++) {
                    myItems.add(resp.get(i).getItem_name());

                }
                for(int i=0;i<myItems.size();i++) {
                    myPhotos.add(R.drawable.booro_cz);
                }
                gv.setAdapter(new CustomItemAdapter(Objects.requireNonNull(getActivity()).getApplicationContext(),myPhotos,myItems));
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.w(TAG, "onFailure: ", t);
            }
        });
    }
}
