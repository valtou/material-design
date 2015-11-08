package android.valto.fi.template.adapters;

import android.valto.fi.template.R;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class PageThreeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private String[] cardTitles;
    private int[] cardImages;
    private String[] cardDescs;

    public PageThreeAdapter(String[] cardTitles, int[] cardImages, String[] cardDescs, Context context) {
        this.cardTitles = cardTitles;
        this.cardDescs = cardDescs;
        this.cardImages = cardImages;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_three_single_item, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ListItemViewHolder listItemViewHolder = (ListItemViewHolder) holder;
        listItemViewHolder.cardTitle.setText(cardTitles[position]);
        listItemViewHolder.cardDescription.setText(cardDescs[position]);
        // listItemViewHolder.cardImage.setImageResource(cardImages[position]);
        listItemViewHolder.cardTitle.setTypeface(Typeface.createFromAsset(context.getAssets(), "circular_light.otf"));
        listItemViewHolder.cardDescription.setTypeface(Typeface.createFromAsset(context.getAssets(), "circular_light.otf"));

        Random random = new Random();
        listItemViewHolder.cardImage.setImageResource(cardImages[random.nextInt(3)]);
        listItemViewHolder.shareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();
            }
        });
        listItemViewHolder.favoriteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItemViewHolder.favoriteCard.setImageResource(R.drawable.ic_favorite_black_24dp);
               // Toast.makeText(context, "Favorited!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardTitles.length == 0 ? 0 : cardTitles.length;
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView cardTitle;
        private final TextView cardDescription;
        private final ImageView cardImage;
        private final ImageView shareCard;
        private final ImageView favoriteCard;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            cardTitle = (TextView) itemView.findViewById(R.id.card_title);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
            cardDescription = (TextView) itemView.findViewById(R.id.card_description);
            shareCard = (ImageView) itemView.findViewById(R.id.share_image);
            favoriteCard = (ImageView) itemView.findViewById(R.id.favorite_image);
        }
    }
}
