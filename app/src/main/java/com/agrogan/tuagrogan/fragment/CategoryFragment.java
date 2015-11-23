package com.agrogan.tuagrogan.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agrogan.tuagrogan.R;
import com.agrogan.tuagrogan.adapter.RVPromoListAdapter;
import com.agrogan.tuagrogan.model.Category;
import com.agrogan.tuagrogan.model.Item;
import com.agrogan.tuagrogan.viewmodel.ListViewItem;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {


    private CoordinatorLayout coordinator;
    FragmentActivity fragment= null;
    Category category= null;

    RecyclerView mRecyclerItem;
    RVPromoListAdapter adaptItem;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }

    public CategoryFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_category, container, false);
        fragment = getActivity();
        Bundle bundle = getArguments();
        Category cat = (Category) bundle.getSerializable("category");
        ArrayList<ListViewItem> data = printItem(cat);

        mRecyclerItem = (RecyclerView) view.findViewById(R.id.rvItem);
        mRecyclerItem.setHasFixedSize(true);
        mRecyclerItem.setLayoutManager(new LinearLayoutManager(fragment));
        adaptItem = new RVPromoListAdapter(data, fragment);
        mRecyclerItem.setAdapter(adaptItem);
        return  view;
    }

    public ArrayList<ListViewItem> printItem(Category cat)
    {
        ArrayList<ListViewItem> list = new ArrayList<>();
        for(Item item : cat.itemDisplay){
            ListViewItem itemView = new ListViewItem();
            itemView.identifier = Integer.parseInt(item.code);
            itemView.text = item.descripcion;
            itemView.subText = item.descripcionLarga;
            itemView.image = item.image;
            list.add(itemView);
        }
        return  list;

    }

}
