package ducban.deptrai.comot.khonghai.sampleproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.BillActivity;
import ducban.deptrai.comot.khonghai.sampleproject.R;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.holder.HolderBill;
import ducban.deptrai.comot.khonghai.sampleproject.model.Bill;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.BillDAO;

public class HoaDonAdapter extends RecyclerView.Adapter<HolderBill> {

    private BillActivity context;
    private List<Bill> arrayList;

    public HoaDonAdapter(BillActivity context, List<Bill> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HolderBill onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_bill, parent, false);
        return new HolderBill(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBill holder, final int position) {
        final Bill bill = arrayList.get(position);

        String date = new Date(bill.date).toString();

        holder.tvInfo.setText(bill.id + "\n" + date);

        // su kien XOA Bill
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BillDAO billDAO = new BillDAO(new DatabaseHelper(context));
                billDAO.delBill(bill.id);
                arrayList.remove(position);
                // cap nhat lai giao dien
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(bill.id, position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

