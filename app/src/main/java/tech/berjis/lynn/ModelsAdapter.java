package tech.berjis.lynn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

import java.util.List;

public class ModelsAdapter extends RecyclerView.Adapter<ModelsAdapter.ViewHolder> {

    private List<Models> listData;
    private Context mContext;
    private String view_type;

    ModelsAdapter(Context mContext, List<Models> listData, String view_type) {
        this.mContext = mContext;
        this.listData = listData;
        this.view_type = view_type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(view_type.equals("feed")){
            view = LayoutInflater.from(mContext).inflate(R.layout.models, parent, false);
        }
        if (view_type.equals("search")){
            view = LayoutInflater.from(mContext).inflate(R.layout.search, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Models ld = listData.get(position);

        if (!ld.getUser_image().equals("")) {
            Picasso.get().load(ld.getUser_image()).into(holder.modelImage);
        }
        holder.modelName.setText(ld.getUser_name());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modelIntent = new Intent(mContext, FeedActivity.class);
                Bundle modelBundle = new Bundle();
                modelBundle.putString("model", ld.getUser_id());
                modelIntent.putExtras(modelBundle);
                mContext.startActivity(modelIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        EmojiTextView modelName;
        ImageView modelImage;
        View mView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            modelName = itemView.findViewById(R.id.modelName);
            modelImage = itemView.findViewById(R.id.modelImage);
            mView = itemView;
        }
    }
}
