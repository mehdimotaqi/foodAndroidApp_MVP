package com.example.foodappmpv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodappmpv.R;
import com.example.foodappmpv.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewMealByCategory extends RecyclerView.Adapter<RecyclerViewMealByCategory.RecyclerViewHolder> {

    private List<Meals.Meal> meals;
    private Context context;
    private static ClickListener OnItemClickListener;


    public RecyclerViewMealByCategory(Context context, List<Meals.Meal> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal, parent, false);
        return new RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMealByCategory.RecyclerViewHolder holder, int position) {
            String strMealThumb = meals.get(position).getStrMealThumb();
        Picasso.get().load(strMealThumb).into(holder.mealThumb);

        String strMealName = meals.get(position).getStrMeal();
        holder.mealName.setText(strMealName);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.mealThumb)
        ImageView mealThumb;
        @BindView(R.id.mealName)
        TextView mealName;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            OnItemClickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener onItemClickListener) {
        OnItemClickListener = onItemClickListener;
    }

    public interface ClickListener{
        void onClick(View view, int position);
    }
}
