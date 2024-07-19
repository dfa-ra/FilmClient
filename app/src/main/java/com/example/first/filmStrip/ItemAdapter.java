package com.example.first.filmStrip;

import android.annotation.SuppressLint;
import android.net.IpSecManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first.R;
import com.example.first.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private List<FilmItem> items = new ArrayList<>();
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemBinding.bind(itemView);
        }

        public void bind(FilmItem item){
            binding.textView.setText(item.nameRu);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItems(FilmItem item){
        Log.i("Adapter", "add new");
        items.add(item);
        notifyDataSetChanged();
    }
}

