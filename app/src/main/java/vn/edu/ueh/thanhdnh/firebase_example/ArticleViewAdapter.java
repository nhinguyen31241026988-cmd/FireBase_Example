package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private final LayoutInflater mInflater;
    private List<Article> articles;

    public ArticleViewAdapter(Context context, List<Article> articles) {
        this.mInflater = LayoutInflater.from(context);
        this.articles = articles;
    }

    public void update(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.getTxtTitle().setText(article.getTitle());
        holder.getTxtContent().setText(article.getContent());
        holder.getTxtId().setText("ID: " + article.getId());

        Glide.with(holder.itemView.getContext())
                .load(article.getImageUrl())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .centerCrop()
                .into(holder.getImgArticle());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}