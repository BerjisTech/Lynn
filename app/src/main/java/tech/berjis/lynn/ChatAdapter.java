package tech.berjis.lynn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Chat ld = listData.get(position);

        hideViews(ld, holder);

        if (ld.getSender().equals(UID)) {
            loadSenderView(ld, holder);
        }
        if (ld.getReceiver().equals(UID)) {
            loadReceiverView(ld, holder);
        }

        if (ld.getType().equals("photo")) {
            holder.senderImageCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGallery(ld, holder);
                }
            });
            holder.receiverImageCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGallery(ld, holder);
                }
            });
        }

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, holder.mView);
                //inflating menu from xml resource
                popup.inflate(R.menu.chat_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_chat:
                                itemSelection(holder, "delete", position, ld);
                                return true;
                            case R.id.reply_chat:
                                itemSelection(holder, "reply", position, ld);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView receiverImageCard, senderImageCard;
        ImageView receiverChatImage, senderChatImage;
        EmojiTextView receiverChatText, senderChatText;
        TextView receiverChatTime, senderChatTime;
        View mView;
        Context vContext;

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
            vContext = itemView.getContext();
        }
    }

    private void itemSelection(ViewHolder holder, String action, int position, Chat ld) {
        // Retrieving the item

        if (position != RecyclerView.NO_POSITION) {
            if (action.equals("delete")) {
                // Add clicked item to the selected ones
                DMActivity.manageSelection(holder, ld, position, ld.getChat_id());

                // Visually highlighting the ImageView
                holder.mView.setVisibility(View.GONE);
            } else {
                // Remove clicked item from the selected ones
                DMActivity.manageSelection(ld, position, ld.getChat_id());

                // Removing the visual highlights on the ImageView
                DMActivity.replyTo(holder, ld, position, ld.getChat_id());
            }
        }
    }

    /* The method for managing the long click on an image.
    public boolean onLongClick(View view) {

        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            Intent intent = new Intent(mContext, DMActivity.class);
            intent.putExtra("KEY4URL", position);
            mContext.startActivity(intent);
        }

        // return true to indicate that the click was handled (if you return false onClick will be triggered too)
        return true;
    }*/

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

    private void viewGallery(Chat ld, ViewHolder holder) {
        Intent imageIntent = new Intent(mContext, ChatGallery.class);
        Bundle imageBundle = new Bundle();
        imageBundle.putString("chat_id", ld.getChat_id());
        imageBundle.putString("sender", ld.getSender());
        imageBundle.putString("receiver", ld.getReceiver());
        imageIntent.putExtras(imageBundle);
        mContext.startActivity(imageIntent);
    }
}
