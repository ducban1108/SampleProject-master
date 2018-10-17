package ducban.deptrai.comot.khonghai.sampleproject.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ducban.deptrai.comot.khonghai.sampleproject.R;

import ducban.deptrai.comot.khonghai.sampleproject.R;

public class HolderBill extends RecyclerView.ViewHolder {


    public TextView tvInfo;
    public ImageView btnDel;

    public HolderBill(View itemView) {
        super(itemView);

        tvInfo = itemView.findViewById(R.id.tvInfo);
        btnDel = itemView.findViewById(R.id.btnDel);

    }
}
