package com.agrogan.tuagrogan.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.agrogan.tuagrogan.ApplicationObject;
import com.agrogan.tuagrogan.fragment.CategoryFragment;
import com.agrogan.tuagrogan.model.Category;
import com.agrogan.tuagrogan.model.Enterprise;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LogicStudio on 22/10/2015.
 */
public class MCFragmentPagerAdapter extends FragmentPagerAdapter {


    private static ApplicationObject application;
    private static Enterprise enterprise;
    private Context _context;


    int PAGE_COUNT;
    private List<String> tabTitles = new ArrayList<>();



    public MCFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public MCFragmentPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        _context = c;
        application =(ApplicationObject) _context.getApplicationContext();
        enterprise = application.getEnterprise();
        if(application.getEnterprise().categories.size() > 0) {
            for (Category cat : application.getEnterprise().categories) {
                tabTitles.add(cat.categoria);
            }
        }
        PAGE_COUNT = application.getEnterprise().categories.size();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        List<Category>  categories = enterprise.categories;
        if(categories.size() > 0){
            f = new CategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("category",categories.get(position));
            f.setArguments(bundle);
        }
        return f;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
