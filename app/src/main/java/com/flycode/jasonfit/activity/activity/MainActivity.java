package com.flycode.jasonfit.activity.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.flycode.jasonfit.R;
import com.flycode.jasonfit.activity.adapter.SideMenuAdapter;
import com.flycode.jasonfit.activity.fragment.FoodsFragment;
import com.flycode.jasonfit.activity.fragment.InfoFragment;
import com.flycode.jasonfit.activity.fragment.MealsFragment;
import com.flycode.jasonfit.activity.fragment.StatsFragment;
import com.flycode.jasonfit.activity.fragment.WorkoutsFragment;

public class MainActivity extends AppCompatActivity implements SideMenuAdapter.OnSideMenuClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processDrawer();
    }

    @Override
    public void onSideMenuItemClick(SideMenuAdapter.SideMenuItem sideMenuItem) {
        switch (sideMenuItem) {
            case WORKOUTS:
                WorkoutsFragment workoutsFragment = new WorkoutsFragment();

                FragmentTransaction workoutsTransaction = getFragmentManager().beginTransaction();
                workoutsTransaction.add(R.id.container, workoutsFragment);
                workoutsTransaction.commit();
                break;

            case MEALS:
                MealsFragment mealsFragment = new MealsFragment();

                FragmentTransaction mealsTransaction = getFragmentManager().beginTransaction();
                mealsTransaction.add(R.id.container, mealsFragment);
                mealsTransaction.commit();
                break;

            case STATS:
                StatsFragment statsFragment = new StatsFragment();

                FragmentTransaction statsTransaction = getFragmentManager().beginTransaction();
                statsTransaction.add(R.id.container, statsFragment);
                statsTransaction.commit();
                break;

            case FOODS:
                FoodsFragment foodsFragment = new FoodsFragment();

                FragmentTransaction foodsTransaction = getFragmentManager().beginTransaction();
                foodsTransaction.add(R.id.container, foodsFragment);
                foodsTransaction.commit();
                break;

            case SETTINGS:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;

            case INFO:
                InfoFragment infoFragment = new InfoFragment();

                FragmentTransaction infoTransaction = getFragmentManager().beginTransaction();
                infoTransaction.add(R.id.container, infoFragment);
                infoTransaction.commit();
                break;
        }
    }

    private void processDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.parent_drawer);
        RecyclerView drawerRecycler = (RecyclerView) findViewById(R.id.drawer_recycler);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        drawerRecycler.setAdapter(new SideMenuAdapter(this));

        drawerRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}