package com.agrogan.tuagrogan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.agrogan.tuagrogan.adapter.RVPromoListAdapter;
import com.agrogan.tuagrogan.model.Category;
import com.agrogan.tuagrogan.model.Enterprise;
import com.agrogan.tuagrogan.model.Item;
import com.agrogan.tuagrogan.viewmodel.ListViewItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //DrawerLayout drawerLayout;
    NavigationView navigationView;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;

    RecyclerView mRecyclerItem;
    RVPromoListAdapter adaptItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ApplicationObject application =(ApplicationObject) getApplicationContext();
        ArrayList<ListViewItem> data = printItem(application.getEnterprise());

        mRecyclerItem = (RecyclerView) findViewById(R.id.rvItem);
        mRecyclerItem.setHasFixedSize(true);
        mRecyclerItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adaptItem = new RVPromoListAdapter(data, getApplicationContext());
        mRecyclerItem.setAdapter(adaptItem);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_view_list_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                Drawer.closeDrawers();
                Handler h = new Handler();

                switch (menuItem.getItemId()) {

                    case R.id.inicio:

                        return true;

                    case R.id.category:
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 250);

                        return true;

                    case R.id.info:
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                        }, 250);

                        return true;

                    default:
                        return true;

                }

            }


        });



        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);

        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }


            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public ArrayList<ListViewItem> printItem(Enterprise ent)
    {
        ArrayList<ListViewItem> list = new ArrayList<>();
        for(Item item : ent.mainData){
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
