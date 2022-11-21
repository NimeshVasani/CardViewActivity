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
import com.example.cardviewactivity.Model.HomeServiceUser;
import com.example.cardviewactivity.R;

import java.util.List;

public class HomeServiceAdapter extends RecyclerView.Adapter<HomeServiceAdapter.ViewHolder> {

    private Context mContext;
    private List<HomeServiceUser> mUsers;

    public HomeServiceAdapter() {
    }

    public HomeServiceAdapter(Context mContext, List<HomeServiceUser> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HomeServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.homeservice_layout, parent, false);
        return new HomeServiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeServiceAdapter.ViewHolder holder, int position) {
        final HomeServiceUser user = mUsers.get(position);
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
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent=new Intent(mContext, MessageActivity.class);
        intent.putExtra("userid",user.getId());
        mContext.startActivity(intent);
        }
        });*/
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView degree;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            degree=itemView.findViewById(R.id.degree);

        }

    }
}
