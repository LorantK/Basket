package com.example.kevin.clemapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kevin.clemapp.R;
import com.example.kevin.clemapp.models.Item;
import com.example.kevin.clemapp.viewholder.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 11/06/2016.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private ArrayList<Item> items;
    private SparseBooleanArray selectedItems;

    public ItemAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_list_item, viewGroup, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        Item it = items.get(i);
        itemViewHolder.getT().setText(it.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        } else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Item> getSelectedItems() {

        return items;
    }

}
