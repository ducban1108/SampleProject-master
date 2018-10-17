package ducban.deptrai.comot.khonghai.sampleproject.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.BookActivity;
import ducban.deptrai.comot.khonghai.sampleproject.R;
import ducban.deptrai.comot.khonghai.sampleproject.database.DatabaseHelper;
import ducban.deptrai.comot.khonghai.sampleproject.holder.HolderBook;
import ducban.deptrai.comot.khonghai.sampleproject.model.Book;

import static ducban.deptrai.comot.khonghai.sampleproject.Constant.B_COLUMN_IDBOOK;

public class BookAdapter extends RecyclerView.Adapter<HolderBook> {
    private ArrayList<Book> bookArrayList;
    private BookActivity context;
    private DatabaseHelper helper;

    public BookAdapter(ArrayList<Book> bookArrayList, BookActivity context) {
        this.bookArrayList = bookArrayList;
        this.context = context;
    }

    @Override
    public HolderBook onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_book,parent,false);
        return new HolderBook(view);
    }

    @Override
    public void onBindViewHolder(HolderBook holder, final int position) {
        helper=new DatabaseHelper(context);
        Cursor cursor=helper.getData("SELECT * FROM HoaDonChiTiet");
        final List<String> strings=new ArrayList<>();
        strings.clear();
        final Book book=bookArrayList.get(position);
        if (cursor!=null && cursor.moveToFirst()){
            do {
                String masach=cursor.getString(cursor.getColumnIndex(B_COLUMN_IDBOOK));
                strings.add(masach);
            }while (cursor.moveToNext());

        }

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean po = false;
                for (int i = 0; i < strings.size(); i++) {
                    if (bookArrayList.get(position).getMasach().equalsIgnoreCase(strings.get(i))) {
                        Toast.makeText(context, "Không thể xóa sách này", Toast.LENGTH_SHORT).show();
                        po = true;
                        break;
                    }
                }

                if (!po) {
                    context.deleteBook(book.getMasach(), position);
                }
            }
        });




        holder.txtNameBook.setText(book.getLoaisach());
        holder.txtPOS.setText(book.getSoluong()+"");

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateBook(book.getMasach(),book.getLoaisach(),book.getTacgia(),book.getNxb(),Float.parseFloat(book.getGia()),Integer.parseInt(book.getSoluong()));
            }
        });
    }



    @Override
    public int getItemCount() {
        return 0;
    }
}
