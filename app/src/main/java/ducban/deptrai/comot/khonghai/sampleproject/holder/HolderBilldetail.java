package ducban.deptrai.comot.khonghai.sampleproject.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ducban.deptrai.comot.khonghai.sampleproject.R;

public class HolderBilldetail extends RecyclerView.ViewHolder {
    public TextView txtMaSach;
    public TextView txtSoLuong;
    public TextView txtGiaBia;
    public TextView txtYhanhTien;
    public ImageView imageView;

    public HolderBilldetail(View itemView) {
        super(itemView);
        txtMaSach = (TextView) itemView.findViewById(R.id.txtMaSach);
        txtSoLuong = (TextView) itemView.findViewById(R.id.txtSoLuong);
        txtGiaBia = (TextView) itemView.findViewById(R.id.txtGiaBia);
        txtYhanhTien = (TextView) itemView.findViewById(R.id.txtYhanhTien);
        imageView=itemView.findViewById(R.id.imgDelete);
    }
}
