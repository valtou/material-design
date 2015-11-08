package android.valto.fi.template.adapters;

import android.valto.fi.template.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import java.util.List;
import java.util.Random;


public class RefreshListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> list;
    private int[] images;

    public RefreshListAdapter(List<String> list, int[] images) {
        this.list = list;
        this.images = images;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RefreshListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.refresh_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RefreshListViewHolder viewHolder = (RefreshListViewHolder) holder;
        viewHolder.refreshItem.setText(list.get(position));
        Random random = new Random();
        viewHolder.refreshImage.setImageResource(images[random.nextInt(5)]);

    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    class RefreshListViewHolder extends RecyclerView.ViewHolder {
        private CircularImageView refreshImage;
        private TextView refreshItem;

        public RefreshListViewHolder(View itemView) {
            super(itemView);
            refreshImage = (CircularImageView) itemView.findViewById(R.id.refresh_image);
            refreshItem = (TextView) itemView.findViewById(R.id.refresh_item);
        }
    }
}
