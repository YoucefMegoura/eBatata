package dz.youcefmegoura.ebatata.ebatata.controllers;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.models.Transaction;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class AdapterTransaction extends RecyclerView.Adapter<AdapterTransaction.ViewHolder> {

    private static final String TAG = "AdapterTransaction";
    private ArrayList<Transaction> transactionsList;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        listener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView textViewDate;
        public TextView textViewADonne;
        public TextView textViewAPris;

        public ViewHolder(View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.date_template_transaction);
            textViewADonne = itemView.findViewById(R.id.a_donne_template_transaction);
            textViewAPris = itemView.findViewById(R.id.a_pris_template_transaction);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public AdapterTransaction(ArrayList<Transaction> transactions) {
        transactionsList = transactions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_transaction, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction currentTransaction = transactionsList.get(position);



        holder.textViewDate.setText(currentTransaction.getDate());


        holder.textViewAPris.setText(String.valueOf(currentTransaction.getA_pris()));
        holder.textViewADonne.setText(String.valueOf(currentTransaction.getA_donne()));

    }

    @Override
    public int getItemCount() {
        //TODO:: return 0;
        return transactionsList.size();
    }

    /*public void filtredList(ArrayList<Transaction> filtredlist) {
        transactionsList = filtredlist;
        notifyDataSetChanged();
    }*/


}
