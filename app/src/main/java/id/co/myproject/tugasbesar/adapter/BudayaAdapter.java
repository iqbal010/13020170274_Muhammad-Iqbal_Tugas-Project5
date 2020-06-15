package id.co.myproject.tugasbesar.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import id.co.myproject.tugasbesar.MainActivity;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.databinding.ItemBudayaBinding;
import id.co.myproject.tugasbesar.model.Budaya;
import id.co.myproject.tugasbesar.view.BudayaFragment;

public class BudayaAdapter extends RecyclerView.Adapter<BudayaAdapter.ViewHolder> {
    Context context;
    List<Budaya> budayaList = new ArrayList<>();

    public BudayaAdapter(Context context) {
        this.context = context;
    }

    public void setBudayaList(List<Budaya> budayaList) {
        this.budayaList.clear();
        this.budayaList.addAll(budayaList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BudayaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBudayaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_budaya, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BudayaAdapter.ViewHolder holder, int position) {
        holder.binding.setBudaya(budayaList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudayaFragment budayaFragment = new BudayaFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id_budaya", budayaList.get(position).getIdBudaya());
                bundle.putString("title", budayaList.get(position).getNamaBudaya());
                budayaFragment.setArguments(bundle);
                FrameLayout parenLayout = ((MainActivity)holder.binding.getRoot().getContext()).binding.frameMain;
                FragmentTransaction transaction = ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(parenLayout.getId(), budayaFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return budayaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemBudayaBinding binding;
        public ViewHolder(@NonNull ItemBudayaBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
