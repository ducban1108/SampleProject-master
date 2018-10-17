package ducban.deptrai.comot.khonghai.sampleproject.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ducban.deptrai.comot.khonghai.sampleproject.R;

public class UserHolder extends RecyclerView.ViewHolder {
    public final TextView txtName, txtSodienthoai;
    public final ImageView btnDelete;
    public ImageView btnEdit;
    public ImageView imageView;
    
    public UserHolder(View itemView) {
        super(itemView);

        btnDelete = itemView.findViewById(R.id.deleteUser);
        txtName = itemView.findViewById(R.id.txtTren);
        txtSodienthoai = itemView.findViewById(R.id.txtDuoi);
        btnEdit = itemView.findViewById(R.id.editUser);
        imageView = itemView.findViewById(R.id.imgUser);
    }
}
