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

import jp.wasabeef.recyclerview.animators.adapters.SlideInLeftAnimationAdapter;

public class PageThree extends Fragment {

    public PageThree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page_three, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PageThreeAdapter adapter = new PageThreeAdapter(DummyData.exoPlanets, DummyData.cardImages, DummyData.description, getActivity());
        SlideInLeftAnimationAdapter slideInLeftAnimationAdapter = new SlideInLeftAnimationAdapter(adapter);
        recyclerView.setAdapter(slideInLeftAnimationAdapter);
        return view;
    }


}
