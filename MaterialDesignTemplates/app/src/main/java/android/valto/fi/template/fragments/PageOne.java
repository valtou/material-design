package android.valto.fi.template.fragments;

import android.valto.fi.template.R;
import android.valto.fi.template.adapters.PageThreeAdapter;
import android.valto.fi.template.utils.DummyData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;

public class PageOne extends Fragment {
    private List<String> items;

    public PageOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page_one, container, false);

        items = new ArrayList<>();
        populateList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PageThreeAdapter pageThreeAdapter = new PageThreeAdapter(DummyData.exoPlanets, DummyData.cardImages, DummyData.description, getActivity());
        SlideInBottomAnimationAdapter slideInBottomAnimationAdapter = new SlideInBottomAnimationAdapter(pageThreeAdapter);
        recyclerView.setAdapter(slideInBottomAnimationAdapter);
        return view;
    }

    private void populateList() {
        for (int i = 1; i < 100; i++) {
            items.add("Planet " + i);
        }

    }

}
