package dz.youcefmegoura.ebatata.ebatata.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.models.Client;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class AdapterClient extends RecyclerView.Adapter<AdapterClient.ViewHolder> {

    private ArrayList<Client> clientsList;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //public ImageView imageView;
        public TextView textViewNom;
        public TextView textViewNumero;

        public ViewHolder(View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.image_template_client);
            textViewNom = itemView.findViewById(R.id.nom_template_client);
            textViewNumero = itemView.findViewById(R.id.num_template_client);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
    public AdapterClient(ArrayList<Client> clients){
        clientsList = clients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_client, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Client currentClient = clientsList.get(position);

        holder.textViewNom.setText(currentClient.getNom());
        if (currentClient.getNum2().equals(null) || currentClient.getNum2().equals(""))
            holder.textViewNumero.setText(currentClient.getNum1());
        else
            holder.textViewNumero.setText(currentClient.getNum1() + " - " + currentClient.getNum2());

    }

    @Override
    public int getItemCount() {
        return clientsList.size();
    }
    public void filtredList(ArrayList<Client> filtredlist){
        clientsList = filtredlist;
        notifyDataSetChanged();
    }


}
