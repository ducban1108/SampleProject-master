package ducban.deptrai.comot.khonghai.sampleproject.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ducban.deptrai.comot.khonghai.sampleproject.R;
import ducban.deptrai.comot.khonghai.sampleproject.holder.UserHolder;
import ducban.deptrai.comot.khonghai.sampleproject.model.User;
import ducban.deptrai.comot.khonghai.sampleproject.sqlitedao.UserDAO;

public class NguoiDungAdapter extends RecyclerView.Adapter<UserHolder>{

    private Context context;
    private  List<User> userList;
    private UserDAO userDAO;


    public NguoiDungAdapter(Context context, List<User> userList, UserDAO userDAO) {
        this.context = context;
        this.userList = userList;
        this.userDAO = userDAO;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.card_view,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, final int position) {
        User user= userList.get(position);
        holder.txtName.setText(user.getName());
        holder.txtSodienthoai.setText(user.getPhonenumber());
        holder.imageView.setImageResource(user.getPhotouser());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDAO.deleteUser(userList.get(position).getUsername());
                userList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setTitle(userList.get(position).getName());

                dialog.setContentView(R.layout.dialog_edit_user);

                EditText edtPassWord;
                EditText edtConfirmPassword;
                final TextView edtName;
                final TextView edtPhone;

                edtPassWord = dialog.findViewById(R.id.edtPassWord);
                edtConfirmPassword = dialog.findViewById(R.id.edtConfirmPassword);
                edtName = dialog.findViewById(R.id.edtName);
                edtPhone = dialog.findViewById(R.id.edtPhone);

                edtName.setText(userList.get(position).getName());
                edtPhone.setText(userList.get(position).getPhonenumber());


                dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        User user = new User();
                        user.setUsername(userList.get(position).getUsername());
                        user.setName(edtName.getText().toString().trim());
                        user.setPhonenumber(edtPhone.getText().toString().trim());

                        userDAO.updateUser(user);

                        // cap nhat thay doi len giao dien
                        userList.get(position).setName(edtName.getText().toString().trim());
                        userList.get(position).setPhonenumber(edtPhone.getText().toString().trim());
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
        return userList.size();
    }


}


