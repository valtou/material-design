package android.valto.fi.template.adapters;

import android.valto.fi.template.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class CollapseToToolbarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List<String> items;

    public CollapseToToolbarAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListViewHolder listViewHolder = (ListViewHolder) holder;
        //Setting text to the list item
        listViewHolder.mListItem.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private final TextView mListItem;

        public ListViewHolder(View itemView) {
            super(itemView);
            mListItem = (TextView) itemView.findViewById(R.id.text_item);

        }
    }
}
