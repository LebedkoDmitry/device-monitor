package org.lebedko.device.monitor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import org.lebedko.device.monitor.R;
import org.lebedko.device.monitor.dto.Accident;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<Accident> articlesList = new ArrayList<>();

    private final static int ACCIDENT = 1;

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public TextView name, context, accidentType, accident;
        public ImageView imageView;

        public ArticleViewHolder(View view) {
            super(view);
            this.view = view;
            name = (TextView) view.findViewById(R.id.deviceName);
            context = (TextView) view.findViewById(R.id.receiveTime);
            accidentType = (TextView) view.findViewById(R.id.accidentType);
            accident = (TextView) view.findViewById(R.id.accident);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }

    public class AdvertiseViewHolder extends ArticleViewHolder {
        public AdvertiseViewHolder(View view) {
            super(view);
        }
    }

    public void setData(List<Accident> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);

        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Accident article = articlesList.get(position);
        holder.name.setText(article.getDeviceName());
        holder.context.setText(article.getReceiveTime());
        holder.accidentType.setText(article.getAccidentType());
        holder.accident.setText(article.getAccident());
        Context appContext = holder.view.getContext();
        Picasso.with(appContext).load(
                appContext.getResources().getString(R.string.host)
                        + ':'
                        + appContext.getResources().getInteger(R.integer.port)
                        + appContext.getResources().getString(R.string.resources)
                        + article.getAccidentType()
                        + ".png")
                .into(holder.imageView);
    }

    @Override
    public int getItemViewType(int position) {
        return ACCIDENT;
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}
