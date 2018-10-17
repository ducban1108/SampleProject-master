package ducban.deptrai.comot.khonghai.sampleproject.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ducban.deptrai.comot.khonghai.sampleproject.R;

public class TypeBookHolder extends RecyclerView.ViewHolder {
    public TextView txtMaloai;
    public TextView txtTenloai;
    public ImageView btnDelete;
    public ImageView btnEdit;

    public TypeBookHolder(View itemView) {
        super(itemView);

        txtMaloai = itemView.findViewById(R.id.txtTren);
        txtTenloai = itemView.findViewById(R.id.txtDuoi);
        btnDelete = itemView.findViewById(R.id.deleteUser);
        btnEdit = itemView.findViewById(R.id.editUser);
    }
}
