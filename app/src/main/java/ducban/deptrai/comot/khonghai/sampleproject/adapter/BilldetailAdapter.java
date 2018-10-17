package ducban.deptrai.comot.khonghai.sampleproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ducban.deptrai.comot.khonghai.sampleproject.BillCTActivity;
import ducban.deptrai.comot.khonghai.sampleproject.R;
import ducban.deptrai.comot.khonghai.sampleproject.holder.HolderBilldetail;
import ducban.deptrai.comot.khonghai.sampleproject.model.Billdetails;
import ducban.deptrai.comot.khonghai.sampleproject.model.Book;

public class BilldetailAdapter extends RecyclerView.Adapter<HolderBilldetail> {
    private ArrayList<Billdetails> billdetails;
    private ArrayList<Book> bookArrayList;
    private BillCTActivity context;
    private int i;

    public BilldetailAdapter(ArrayList<Billdetails> billdetails, ArrayList<Book> bookArrayList, BillCTActivity context, int i) {
        this.billdetails = billdetails;
        this.bookArrayList = bookArrayList;
        this.context = context;
        this.i = i;
    }

    @Override
    public HolderBilldetail onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardview_billdetail,parent,false);
        return new HolderBilldetail(view);
    }

    @Override
    public void onBindViewHolder(HolderBilldetail holder, final int position) {
        final Billdetails bill=billdetails.get(position);
        int pos=-1;
        for (int i = 0; i < bookArrayList .size(); i++){
            if (!bookArrayList.isEmpty()){
                Book book=bookArrayList.get(i);
                if (book.getMasach().equalsIgnoreCase(bill.getIdbook())){
                    pos = i;
                    break;
                }
            }
            else {
                holder.txtSoLuong.setText("Số lượng:?");
                holder.txtMaSach.setText("Mã sách:Không tồn tại");
                holder.txtGiaBia.setText("Giá bìa:?");
                holder.txtYhanhTien.setText("Thành tiền:?");
                return;
            }
        }

        Book book=bookArrayList.get(pos);
        holder.txtSoLuong.setText("Số lượng:"+bill.getSoluong());
        holder.txtMaSach.setText("Mã sách:"+bill.getIdbook());
        holder.txtGiaBia.setText("Giá bìa:"+book.getGia()+"\tVND");
        holder.txtYhanhTien.setText("Thành tiền:"+bill.getSoluong()*Float.parseFloat(book.getGia())+"\tVND");

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(bill.getId(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return billdetails.size();
    }
}
