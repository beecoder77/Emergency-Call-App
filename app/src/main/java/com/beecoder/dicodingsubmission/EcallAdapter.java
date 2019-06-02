package com.beecoder.dicodingsubmission;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class EcallAdapter extends RecyclerView.Adapter<EcallAdapter.ListViewHolder> {

    Ecall ecall;
    private Context context;
    private List<Ecall> listEcall;

    public EcallAdapter(Context context, List<Ecall> listEcall) {
        Log.i("autolog", "EcallAdapter");
        this.listEcall = listEcall;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i("autolog", "onCreateViewHolder");
        View list = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view, viewGroup, false);
        return new ListViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        Log.i("autolog", "onBindViewHolder");
        final int position = listViewHolder.getAdapterPosition();

        listViewHolder.name.setText(getListEcall().get(i).getNama());
        listViewHolder.detail.setText(getListEcall().get(i).getDetail());

        Glide.with(context)
                .load(getListEcall().get(i).getLink())
                .apply(new RequestOptions().override(100,100))
                .into(listViewHolder.img);

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Detail.class);
                intent.putExtra(Detail.EXTRA_NAME, getListEcall().get(position).getNama());
                intent.putExtra(Detail.EXTRA_DETAIL, getListEcall().get(position).getDetail());
                intent.putExtra(Detail.EXTRA_LINK, getListEcall().get(position).getLink());
                intent.putExtra(Detail.EXTRA_NOMOR, getListEcall().get(position).getNomor());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("autolog", "getItemCount");
        return getListEcall().size();
    }

    public List<Ecall> getListEcall() {
        return listEcall;
    }

    public void setListEcall(List<Ecall> listEcall) {
        this.listEcall = listEcall;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView name, detail;
        ImageView img;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.judul);
            detail = itemView.findViewById(R.id.detail);
            img = itemView.findViewById(R.id.gambar);
        }
    }
}
