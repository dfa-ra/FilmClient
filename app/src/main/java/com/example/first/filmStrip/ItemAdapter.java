package com.example.first.filmStrip;

import android.annotation.SuppressLint;
import android.net.IpSecManager;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first.Fragments.PopularFragment;
import com.example.first.R;
import com.example.first.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    public static final String Tag = "ItemAdapterTag";
    private List<FilmItem> items = new ArrayList<>();
    private AdapterListener adapterListener;

    public ItemAdapter(AdapterListener adapterListener){
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.bind(items.get(position), adapterListener);
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

        @SuppressLint("ClickableViewAccessibility")
        public void bind(FilmItem item, AdapterListener adapterListener){
            binding.nameView.setText(item.nameRu);
            binding.ratingKinoPoisk.setText(item.ratingKinopoisk);
            binding.ratingIMDB.setText(item.ratingImdb);
            if (!item.genres.isEmpty()) {
                binding.textGenre.setText(item.genres.get(0).genre);
            }
            binding.filmLayout.setOnClickListener(view -> adapterListener.onClick(item));
            binding.filmLayout.setOnLongClickListener(view -> adapterListener.longOnClick(item));
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItems(FilmItem item){
        Log.i(Tag, this + "add new");
        items.add(item);
        notifyDataSetChanged();
    }
}

