package android.valto.fi.template.adapters;

import android.valto.fi.template.R;
import android.valto.fi.template.interfaces.ItemDismissHelper;
import android.valto.fi.template.interfaces.SwipeToDismissViewHolderHelper;
import android.valto.fi.template.utils.DummyData;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class SwipeToDismissAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemDismissHelper {
    private Context context;
    private View view;

    public interface OnStartDragListener {
        void onStartDrag(RecyclerView.ViewHolder viewHolder);
    }

    private static final String[] STRINGS = DummyData.cheese;
    private List<String> names = new ArrayList<>();
    private final OnStartDragListener mDragStartListener;
    private int[] images = DummyData.images;

    public SwipeToDismissAdapter(OnStartDragListener mDragStartListener, Context context) {
        this.mDragStartListener = mDragStartListener;
        this.context = context;
        names.addAll(Arrays.asList(STRINGS));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.refresh_list_item, parent, false);
        view = (LinearLayout) view1.findViewById(R.id.layout);
        return new RefreshListViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        RefreshListViewHolder viewHolder = (RefreshListViewHolder) holder;
        viewHolder.refreshItem.setText(names.get(position));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        Random random = new Random();
        viewHolder.refreshImage.setImageResource(images[random.nextInt(5)]);

        ((RefreshListViewHolder) holder).refreshImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.size() == 0 ? 0 : names.size();
    }

    @Override
    public void onItemDismiss(int position) {
        names.remove(position);

        //when an item is deleted, we show a toast using SnackBar
        Snackbar.make(view, "One item deleted!", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something here
            }
        }).show();
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(names, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    class RefreshListViewHolder extends RecyclerView.ViewHolder implements SwipeToDismissViewHolderHelper {
        private CircularImageView refreshImage;
        private TextView refreshItem;

        public RefreshListViewHolder(View itemView) {
            super(itemView);
            refreshImage = (CircularImageView) itemView.findViewById(R.id.refresh_image);
            refreshItem = (TextView) itemView.findViewById(R.id.refresh_item);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);

        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

}
