package android.valto.fi.template.adapters;

import android.valto.fi.template.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class PageTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<String> list;

    public PageTwoAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_two_single_item, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItemViewHolder listItemViewHolder = (ListItemViewHolder) holder;
        listItemViewHolder.textView.setText(list.get(position));
        listItemViewHolder.imageView.setImageResource(R.drawable.earth);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.card_image);
            ;
        }
    }
}
