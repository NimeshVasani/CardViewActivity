package com.example.cardviewactivity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardviewactivity.DoctorVisit.DoctorVisiteDialogBoxActivity;
import com.example.cardviewactivity.Model.DoctorVisitUser;
import com.example.cardviewactivity.R;

import java.util.List;

public class DoctorUserAdapter extends RecyclerView.Adapter<DoctorUserAdapter.ViewHolder> {

    private Context mContext;
    private List<DoctorVisitUser> mUsers;

    public DoctorUserAdapter() {
    }

    public DoctorUserAdapter(Context mContext, List<DoctorVisitUser> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.doctor_layout, parent, false);
        return new DoctorUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DoctorVisitUser user = mUsers.get(position);
        holder.name.setText("  " + user.getName());
        holder.degree.setText("("+user.getDegree()+")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext, DoctorVisiteDialogBoxActivity.class);
                i.putExtra("name",user.getName());
                i.putExtra("expertization", user.getExpertization());
                i.putExtra("degree",user.getDegree());
                i.putExtra("experience",user.getExperiance());
                i.putExtra("workplace",user.getWorkplace());
                i.putExtra("time",user.getTime());
                i.putExtra("mobilenumber",user.getNumber());

                mContext.startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
           /* if(user.getImageURL().equals("default"))
            {
                holder.profile_image.setImageResource(R.mipmap.ic_launcher);
            }
            else
            {
                Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
            }*/

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,degree;
        androidx.cardview.widget.CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            cardView=itemView.findViewById(R.id.cardview);
            degree=itemView.findViewById(R.id.degree);
        }

    }
}
