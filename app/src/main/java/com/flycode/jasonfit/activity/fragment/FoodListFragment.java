package com.flycode.jasonfit.activity.fragment;

import android.app.Fragment;
import android.content.Context;
import android.database.DatabaseUtils;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.activeandroid.query.Select;
import com.flycode.jasonfit.R;
import com.flycode.jasonfit.activity.adapter.FoodListAdapter;
import com.flycode.jasonfit.activity.model.Food;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Schumakher on 3/7/17.
 */

public class FoodListFragment extends Fragment implements FoodListAdapter.OnFoodItemClickListener {
    @BindView(R.id.food) RecyclerView foodRecyclerView;

    @BindView(R.id.search_edit_text) EditText searchEditText;

    private Unbinder unbinder;

    private FoodListAdapter foodListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View foodListView = inflater.inflate(R.layout.fragment_foods, container, false);

        unbinder = ButterKnife.bind(this, foodListView);

        List<Food> foodList = new Select().from(Food.class).execute();

        foodListAdapter = new FoodListAdapter(foodList, this);

        foodRecyclerView.setAdapter(foodListAdapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodRecyclerView.addItemDecoration(new DividerDecoration(getActivity()));

        searchEditText.addTextChangedListener(searchTextWatcher);

        return foodListView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    public void onFoodItemClick(Food food) {

    }

    private class DividerDecoration extends RecyclerView.ItemDecoration {
        private Drawable divider;

        DividerDecoration(Context context) {
            divider = context.getResources().getDrawable(R.drawable.horizontal_divider);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + divider.getIntrinsicHeight();
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }


    TextWatcher searchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String searchQuery =  charSequence.toString();
            searchQuery = "%" + searchQuery + "%";

            List<Food> foodList = new Select()
                        .from(Food.class)
                        .where("food LIKE ?", searchQuery)
                        .orderBy("food ASC")
                        .execute();

            foodListAdapter.setItems(foodList);

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}
















