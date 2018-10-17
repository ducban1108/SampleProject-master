package ducban.deptrai.comot.khonghai.sampleproject.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.R;
import ducban.deptrai.comot.khonghai.sampleproject.holder.UserHolder;
import ducban.deptrai.comot.khonghai.sampleproject.model.Book;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.BookDAO;

public class SachAdapter extends RecyclerView.Adapter<UserHolder>{

    private Context context;
    private List<Book> arrayList;
    private BookDAO bookDAO;

    public SachAdapter(Context context, List<Book> arrayList, BookDAO bookDAO){
        this.context = context;
        this.arrayList = arrayList;
        this.bookDAO = bookDAO;

    }
    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, final int position) {
        Book book= arrayList.get(position);
        holder.txtName.setText(book.getTensach());
        holder.txtSodienthoai.setText(book.getSoluong());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookDAO.deleteBook(arrayList.get(position).getTensach());
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setTitle(arrayList.get(position).getTensach());

                dialog.setContentView(R.layout.dialog_edit_user);

                EditText edtPassWord;
                EditText edtConfirmPassword;
                final EditText edtName;
                final EditText edtPhone;


                edtName = dialog.findViewById(R.id.edtName);
                edtPhone = dialog.findViewById(R.id.edtPhone);

                edtName.setText(arrayList.get(position).getTensach());
                edtPhone.setText(arrayList.get(position).getSoluong());


                dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Book book1 = new Book();

                        book1.setTensach(edtName.getText().toString().trim());
                        book1.setSoluong(edtPhone.getText().toString().trim());

                        bookDAO.updateBook(book1);

                        // cap nhat thay doi len giao dien
                        arrayList.get(position).setTensach(edtName.getText().toString().trim());
                        arrayList.get(position).setSoluong(edtPhone.getText().toString().trim());
                        notifyDataSetChanged();

                        Toast.makeText(context,context.getString(R.string.notify_save_successful),Toast.LENGTH_SHORT).show();
                        dialog.dismiss();


                    }
                });
                dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
