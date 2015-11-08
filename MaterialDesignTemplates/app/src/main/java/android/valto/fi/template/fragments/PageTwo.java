package android.valto.fi.template.fragments;

import android.valto.fi.template.R;
import android.valto.fi.template.adapters.PageTwoAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.SlideInRightAnimationAdapter;

public class PageTwo extends Fragment {
    private List<String> list;

    public PageTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page_two, container, false);

        list = new ArrayList<>();
        populateList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        PageTwoAdapter adapter = new PageTwoAdapter(list);
        SlideInRightAnimationAdapter slideInRightAnimationAdapter = new SlideInRightAnimationAdapter(adapter);
        recyclerView.setAdapter(slideInRightAnimationAdapter);
        return view;
    }

    private void populateList() {
        for (int i = 1; i < 100; i++) {
            list.add("Planet " + i);
        }
    }


}
