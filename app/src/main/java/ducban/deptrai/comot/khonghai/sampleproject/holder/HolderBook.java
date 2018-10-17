package ducban.deptrai.comot.khonghai.sampleproject.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ducban.deptrai.comot.khonghai.sampleproject.R;

public class HolderBook extends RecyclerView.ViewHolder{
    public ImageView imgView;
    public TextView txtNameBook;
    public TextView txtPOS;
    public ImageView imgEdit;
    public ImageView imgDelete;



    public HolderBook(View itemView) {
        super(itemView);
        imgView = (ImageView) itemView.findViewById(R.id.imgView);
        txtNameBook = (TextView) itemView.findViewById(R.id.txtNameBook);
        txtPOS = (TextView) itemView.findViewById(R.id.txtPOS);
        imgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);
        imgDelete = (ImageView) itemView.findViewById(R.id.imgDelete);
    }
}
