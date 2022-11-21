package com.example.cardviewactivity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardviewactivity.MessageActivity;
import com.example.cardviewactivity.Model.User;
import com.example.cardviewactivity.R;

import java.util.List;

public class Accept_Patient_RequestAdapter extends RecyclerView.Adapter<Accept_Patient_RequestAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    public Accept_Patient_RequestAdapter(Context mContext,List<User> mUsers)
    {
        this.mUsers=mUsers;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public Accept_Patient_RequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new Accept_Patient_RequestAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Accept_Patient_RequestAdapter.ViewHolder holder, int position) {
        final User user=mUsers.get(position);
        holder.username.setText(user.getUsername());
        holder.profile_image.setImageResource(R.mipmap.ic_launcher);
           /* if(user.getImageURL().equals("default"))
            {
                holder.profile_image.setImageResource(R.mipmap.ic_launcher);
            }
            else
            {
                Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
            }*/
          /* if(ischat)
           {
               if(user.getStatus().equals("online"))
               {
                   holder.img_on.setVisibility(View.VISIBLE);
                   holder.img_off.setVisibility(View.GONE);
               }
               else {
                   holder.img_on.setVisibility(View.GONE);
                   holder.img_off.setVisibility(View.VISIBLE);
               }
           }
           else {
               holder.img_on.setVisibility(View.GONE);
               holder.img_off.setVisibility(View.GONE);
           }*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MessageActivity.class);
                intent.putExtra("visit_user_id",user.getId());
                intent.putExtra("profile_name",user.getUsername());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView username;
        public ImageView profile_image;
        private ImageView img_on;
        private ImageView img_off;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            profile_image=itemView.findViewById(R.id.profile_image);
            img_on=itemView.findViewById(R.id.img_on);
            img_off=itemView.findViewById(R.id.img_off);
        }
    }
}

