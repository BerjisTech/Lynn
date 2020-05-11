package tech.berjis.lynn;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> implements View.OnCreateContextMenuListener {

    private Context mContext;
    private List<Chat> listData;
    private FirebaseAuth mAuth;
    private String UID;

    ChatAdapter(Context mContext, List<Chat> listData) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mAuth = FirebaseAuth.getInstance();
        UID = mAuth.getCurrentUser().getUid();

        View view = LayoutInflater.from(mContext).inflate(R.layout.dm, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat ld = listData.get(position);

        hideViews(ld, holder);

        holder.mView.setOnCreateContextMenuListener(this);

        if (ld.getSender().equals(UID)) {
            loadSenderView(ld, holder);
        }
        if (ld.getReceiver().equals(UID)) {
            loadReceiverView(ld, holder);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, v.getId(), 0, "Delete")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        menu.add(0, v.getId(), 0, "Reply")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView receiverImageCard, senderImageCard;
        ImageView receiverChatImage, senderChatImage;
        EmojiTextView receiverChatText, senderChatText;
        TextView receiverChatTime, senderChatTime;
        View mView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverImageCard = itemView.findViewById(R.id.receiverImageCard);
            receiverChatImage = itemView.findViewById(R.id.receiverChatImage);
            receiverChatText = itemView.findViewById(R.id.receiverChatText);
            receiverChatTime = itemView.findViewById(R.id.receiverChatTime);
            senderImageCard = itemView.findViewById(R.id.senderImageCard);
            senderChatImage = itemView.findViewById(R.id.senderChatImage);
            senderChatText = itemView.findViewById(R.id.senderChatText);
            senderChatTime = itemView.findViewById(R.id.senderChatTime);
            mView = itemView;
        }
    }

    private void hideViews(Chat ld, ViewHolder holder) {
        holder.receiverImageCard.setVisibility(View.GONE);
        holder.receiverChatImage.setVisibility(View.GONE);
        holder.receiverChatText.setVisibility(View.GONE);
        holder.receiverChatTime.setVisibility(View.GONE);
        holder.senderImageCard.setVisibility(View.GONE);
        holder.senderChatImage.setVisibility(View.GONE);
        holder.senderChatText.setVisibility(View.GONE);
        holder.senderChatTime.setVisibility(View.GONE);
    }

    private void loadSenderView(Chat ld, ViewHolder holder) {
        holder.senderChatTime.setVisibility(View.VISIBLE);

        long time = ld.getTime() * 1000;
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());
        String ago = prettyTime.format(new Date(time));

        holder.senderChatTime.setText(ago);

        if (ld.getType().equals("text")) {
            loadSenderText(ld, holder);
        }
        if (ld.getType().equals("photo")) {
            loadSenderImage(ld, holder);
        }
    }

    private void loadReceiverView(Chat ld, ViewHolder holder) {
        holder.receiverChatTime.setVisibility(View.VISIBLE);

        long time = ld.getTime() * 1000;
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());
        String ago = prettyTime.format(new Date(time));

        holder.receiverChatTime.setText(ago);

        if (ld.getType().equals("text")) {
            loadReceiverText(ld, holder);
        }
        if (ld.getType().equals("photo")) {
            loadReceiverImage(ld, holder);
        }
    }

    private void loadSenderText(Chat ld, ViewHolder holder) {
        holder.senderChatText.setVisibility(View.VISIBLE);
        holder.senderChatText.setText(ld.getText());
    }

    private void loadSenderImage(Chat ld, ViewHolder holder) {
        holder.senderImageCard.setVisibility(View.VISIBLE);
        holder.senderChatImage.setVisibility(View.VISIBLE);
        Picasso.get().load(ld.getText()).into(holder.senderChatImage);
    }

    private void loadReceiverText(Chat ld, ViewHolder holder) {
        holder.receiverChatText.setVisibility(View.VISIBLE);
        holder.receiverChatText.setText(ld.getText());
    }

    private void loadReceiverImage(Chat ld, ViewHolder holder) {
        holder.receiverImageCard.setVisibility(View.VISIBLE);
        holder.receiverChatImage.setVisibility(View.VISIBLE);
        Picasso.get().load(ld.getText()).into(holder.receiverChatImage);
    }
}
