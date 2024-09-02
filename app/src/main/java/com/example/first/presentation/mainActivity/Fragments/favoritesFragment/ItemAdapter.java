package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first.R;
import com.example.first.databinding.ListItemBinding;
import com.example.first.domain.models.ShortFilmModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    public static final String Tag = "ItemAdapterTag";
    private List<ShortFilmModel> items = new ArrayList<>();
    private AdapterListener adapterListener;

    public ItemAdapter(AdapterListener adapterListener){
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemAdapter.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemHolder holder, int position) {
        holder.bind(items.get(position), adapterListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ListItemBinding binding;

        private int height;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemBinding.bind(itemView);
        }

        private int getMeasuredHeight(View view) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(widthMeasureSpec, heightMeasureSpec);
            return view.getMeasuredHeight();
        }

        @SuppressLint("ClickableViewAccessibility")
        public void bind(ShortFilmModel item, AdapterListener adapterListener){
            binding.posterPreview.setImageBitmap(item.posterPreview);
            binding.nameView.setText(item.nameRu);
            binding.ratingKinoPoisk.setText(item.ratingKinopoisk);
            binding.ratingIMDB.setText(item.ratingImdb);
            binding.textGenre.setText(item.genre);
            binding.actionsLayout.setVisibility(View.GONE);
            binding.line.setVisibility(View.VISIBLE);


            if (item.isReadable) {
                binding.selectedItemImage.setImageDrawable(ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.galochka_true));
            } else {
                binding.selectedItemImage.setImageDrawable(ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.galochka));
            }

            height = getMeasuredHeight(binding.actionsLayout);

            binding.filmLayout.setOnLongClickListener(v -> {
                try {
                    if (binding.actionsLayout.getVisibility() == View.VISIBLE) {
                        ValueAnimator heightAnimator = ValueAnimator.ofInt(height, 0);
                        heightAnimator.setDuration(300);
                        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int value = (int) animation.getAnimatedValue();
                                ViewGroup.LayoutParams params = binding.actionsLayout.getLayoutParams();
                                params.height = value;
                                binding.actionsLayout.setLayoutParams(params);
                            }
                        });
                        heightAnimator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                binding.actionsLayout.setVisibility(View.GONE);
                            }
                        });
                        heightAnimator.start();
                        binding.line.setVisibility(View.GONE);
                    } else {
                        binding.actionsLayout.setVisibility(View.VISIBLE);

                        ValueAnimator heightAnimator = ValueAnimator.ofInt(0, height);
                        heightAnimator.setDuration(300); // Длительность анимации в миллисекундах
                        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int value = (int) animation.getAnimatedValue();
                                ViewGroup.LayoutParams params = binding.actionsLayout.getLayoutParams();
                                params.height = value;
                                binding.actionsLayout.setLayoutParams(params);
                            }
                        });
                        heightAnimator.start();
                        binding.line.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    Log.e("aa66", Objects.requireNonNull(e.getMessage()));
                }
                return true;
            });

            binding.buttonDelete.setOnClickListener(view -> {
                adapterListener.deleteFilm(item);
            });

            binding.buttonWatch.setOnClickListener(view -> {
                boolean flag = !item.isReadable;
                adapterListener.updateIsReadable(item.kinopoiskId, flag);
            });

            binding.buttonEdit.setOnClickListener(view -> {

            });

            binding.filmLayout.setOnClickListener(view -> {
                adapterListener.onClick(item);
            });

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItem(ShortFilmModel item){
        Log.i(Tag, this + "add new");
        items.add(item);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<ShortFilmModel> items){

        Log.d("msRepositoryImplTag", " setItems(List<ShortFilmModel> items)");
        Log.d("msRepositoryImplTag", items.toString());

        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}