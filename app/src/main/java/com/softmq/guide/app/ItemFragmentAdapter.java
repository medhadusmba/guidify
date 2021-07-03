package com.softmq.guide.app;

import android.app.Activity;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.softmq.guide.app.items.AppItems;
import com.softmq.guide.app.items.ItemList;
import com.softmq.guide.app.items.ShowedItemList;

public class ItemFragmentAdapter extends FragmentStateAdapter {

    @NonNull
    private final Activity activity;
    private final ItemList items;
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public ItemFragmentAdapter(@NonNull AppCompatActivity activity, ItemList items) {
        super(activity);
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ItemFragment.newInstance(position);
    }
}