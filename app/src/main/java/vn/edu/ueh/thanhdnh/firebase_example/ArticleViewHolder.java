package vn.edu.ueh.thanhdnh.firebase_example;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private final ImageView imgArticle;
    private final TextView txtTitle, txtContent, txtId;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        imgArticle = itemView.findViewById(R.id.img_article);
        txtTitle = itemView.findViewById(R.id.txt_title);
        txtContent = itemView.findViewById(R.id.txt_content);
        txtId = itemView.findViewById(R.id.txt_id);
    }

    public ImageView getImgArticle() {
        return imgArticle;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtContent() {
        return txtContent;
    }

    public TextView getTxtId() {
        return txtId;
    }
}