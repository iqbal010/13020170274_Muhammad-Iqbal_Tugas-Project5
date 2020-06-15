package id.co.myproject.tugasbesar.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.databinding.ItemPopulerBinding;
import id.co.myproject.tugasbesar.model.DetailBudaya;
import id.co.myproject.tugasbesar.view.DetailActivity;

public class PopulerAdapter extends RecyclerView.Adapter<PopulerAdapter.ViewHolder> {
    Context context;
    List<DetailBudaya> detailBudayaList = new ArrayList<>();

    public PopulerAdapter(Context context) {
        this.context = context;
    }

    public void setDetailBudayaList(List<DetailBudaya> detailBudayaList) {
        this.detailBudayaList.clear();
        this.detailBudayaList.addAll(detailBudayaList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PopulerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPopulerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_populer, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopulerAdapter.ViewHolder holder, int position) {
        holder.binding.setBudaya(detailBudayaList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id_detail", detailBudayaList.get(position).getIdDetail());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                        holder.binding.ivBudaya, "image_budaya");
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(detailBudayaList.size() >= 5) {
            return 5;
        }else {
            return detailBudayaList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemPopulerBinding binding;
        public ViewHolder(@NonNull ItemPopulerBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
