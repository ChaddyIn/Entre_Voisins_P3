package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private OnClickNeighbourListener mOnClickNeighbourListener;
    private Boolean boolForTab;





    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, OnClickNeighbourListener onClickNeighbourListener, Boolean boolForTab ) {
        this.boolForTab = boolForTab;
        mNeighbours = items;
        this.mOnClickNeighbourListener = onClickNeighbourListener;
    }

    public Boolean getBoolForTab(){
        return boolForTab;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view, mOnClickNeighbourListener);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        if(boolForTab == false){
            holder.mDeleteButton.setVisibility(View.GONE);
        }

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override



            public void onClick(View v) {


                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));


            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        OnClickNeighbourListener onClickNeighbourListener;



        public ViewHolder(View view, OnClickNeighbourListener onClickNeighbourListener) {
            super(view);
            ButterKnife.bind(this, view );

            view.setOnClickListener(this);
            this.onClickNeighbourListener = onClickNeighbourListener;
        }


        @Override
        public void onClick(View view) {
            onClickNeighbourListener.onClickNeighbourClick(getAdapterPosition());

        }
    }

    public interface OnClickNeighbourListener{
        void onClickNeighbourClick (int position);

    }
}
