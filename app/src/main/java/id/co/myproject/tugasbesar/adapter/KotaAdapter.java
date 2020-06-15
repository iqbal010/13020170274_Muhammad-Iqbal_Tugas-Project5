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
import id.co.myproject.tugasbesar.databinding.ItemKotaBinding;
import id.co.myproject.tugasbesar.model.Kota;
import id.co.myproject.tugasbesar.view.KotaBudayaFragment;

public class KotaAdapter extends RecyclerView.Adapter<KotaAdapter.ViewHolder> {
    Context context;
    List<Kota> kotaList = new ArrayList<>();

    public KotaAdapter(Context context) {
        this.context = context;
    }

    public void setDetailBudayaList(List<Kota> kotaList) {
        this.kotaList.clear();
        this.kotaList.addAll(kotaList);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public KotaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKotaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_kota, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KotaAdapter.ViewHolder holder, int position) {
        holder.binding.setKota(kotaList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KotaBudayaFragment kotaBudayaFragment = new KotaBudayaFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id_kota", kotaList.get(position).getIdKota());
                bundle.putString("title", kotaList.get(position).getNamaKota());
                kotaBudayaFragment.setArguments(bundle);
                FrameLayout parenLayout = ((MainActivity)holder.binding.getRoot().getContext()).binding.frameMain;
                FragmentTransaction transaction = ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(parenLayout.getId(), kotaBudayaFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return kotaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemKotaBinding binding;
        public ViewHolder(@NonNull ItemKotaBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
