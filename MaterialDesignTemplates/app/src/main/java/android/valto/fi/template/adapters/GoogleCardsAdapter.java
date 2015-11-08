package android.valto.fi.template.adapters;

import android.valto.fi.template.R;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GoogleCardsAdapter extends RecyclerView.Adapter<GoogleCardsAdapter.GoogleCardViewHolder> {
    private String[] titles;
    private Context context;

    public GoogleCardsAdapter(String[] titles, Context context) {
        this.titles = titles;
        this.context = context;
    }

    @Override
    public GoogleCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoogleCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.google_card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(GoogleCardViewHolder holder, int position) {
        holder.cardTitle.setText(titles[position]);

        /*
        We have used different font here. Fonts are placed in the assets folder and accessed as below
         */
        holder.cardTitle.setTypeface(Typeface.createFromAsset(context.getAssets(), "circular_light.otf"));
        holder.cardDescription.setTypeface(Typeface.createFromAsset(context.getAssets(), "circular_light.otf"));
        holder.cardShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();
            }
        });
        holder.cardReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Examine", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length == 0 ? 0 : titles.length;
    }

    class GoogleCardViewHolder extends RecyclerView.ViewHolder {
        final private TextView cardTitle;
        final private TextView cardDescription;
        final private Button cardShare;
        final private Button cardReserve;

        public GoogleCardViewHolder(View itemView) {
            super(itemView);
            cardTitle = (TextView) itemView.findViewById(R.id.card_title);
            cardDescription = (TextView) itemView.findViewById(R.id.card_description);
            cardShare = (Button) itemView.findViewById(R.id.share_button);
            cardReserve = (Button) itemView.findViewById(R.id.reserve_button);
        }
    }
}
