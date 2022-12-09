package de.dhbw.ka.se.fibo.ui.home;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.dhbw.ka.se.fibo.Helpers;
import de.dhbw.ka.se.fibo.R;
import de.dhbw.ka.se.fibo.models.Cashflow;
import de.dhbw.ka.se.fibo.models.CashflowType;

public class ListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Cashflow> cashflowArrayList;
    private Context context;

    public ListAdapter(Context context, ArrayList<Cashflow> cashflowArrayList) {
        setHasStableIds(true);
        this.cashflowArrayList = cashflowArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new RecyclerViewHolder(view);
    }

    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Cashflow cashflow = this.getItem(position);
        CashflowType cashflowType = cashflow.getType();
        BigDecimal overallValue = cashflow.getOverallValue();
        holder.cardTitle.setText(cashflow.getPlace().getName());
        holder.cardTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        holder.imageView.setText(String.valueOf(context.getResources().getText(cashflow.getCategory().getName())).substring(0, 1));
        holder.cashFlowValue.setText(cashflowType.getSign() + Helpers.formatBigDecimalCurrency(overallValue));
        holder.cashFlowValue.setTextColor(context.getResources().getColor(cashflowType.getColor()));
        context.getResources().getText(cashflow.getCategory().getName()).charAt(0);
        holder.cashFlowValue.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Format formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
        holder.date.setText(formatter.format(cashflow.getTimestamp()));
        holder.materialCard.setOnClickListener(view -> {
            openDetailsPage((MaterialCardView) view, cashflow, position);
        });

    }

    //Todo implement method
    private void openDetailsPage(MaterialCardView materialCardView, Cashflow cashflow, int position) {
        Toast.makeText(context, position + ". " + cashflow.getPlace().getName() + " " + cashflow.getType().getSign() + Helpers.formatBigDecimalCurrency(cashflow.getOverallValue()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Cashflow getItem(int position) {

        return cashflowArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return cashflowArrayList.size();
    }
}
