package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.io.Serializable;
import java.util.List;


public class NeighbourFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.OnClickNeighbourListener, Serializable {




    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;


    Boolean forFav = true;

    public static final String AVATAR_NEIGHBOUR = "AVATAR_DETAIL_NEIGHBOUR";
    public static final String DETAIL_TEXT_NEIGHBOUR = "DETAIL_TEXT_NEIGHBOUR";
    public static final String DETAIL_PRENOM = "DETAIL_PRENOM";
    public static final String DETAIL_ADRESSE = "DETAIL_ADRESSE";
    public static final String DETAIL_TEL = "DETAIL_TEL";
    public static final String DETAIL_FACEBOOK = "DETAIL_FACEBOOK";
    public static final String IS_FAV_BOOLEAN = "IS_FAV_BOOLEAN";
    public static final String CURRENT_OBJECT = "CURRENT_OBJECT";




    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */


    public static NeighbourFragment newInstance(Boolean forFav) {

        Bundle args = new Bundle();
        args.putBoolean("FORFAV", forFav);


        NeighbourFragment fragment = new NeighbourFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();




        final Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey("FORFAV")) {
            // Set a default or error as you see fit
        } else {
            forFav = arguments.getBoolean("FORFAV");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */

    private void initList() {


        if (forFav) {

            mNeighbours = mApiService.getNeighbours();


        } else {
            mNeighbours = mApiService.getFavNeighbours();

        }
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, this));


    }


    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    @Override
    public void onClickNeighbourClick(int position) {

        Intent intent = new Intent(getContext(), DetailNeighbourActivity.class);


        intent.putExtra(DETAIL_TEXT_NEIGHBOUR, mNeighbours.get(position).getAboutMe());
        intent.putExtra(AVATAR_NEIGHBOUR, mNeighbours.get(position).getAvatarUrl());
        intent.putExtra(DETAIL_PRENOM, mNeighbours.get(position).getName());
        intent.putExtra(DETAIL_ADRESSE, mNeighbours.get(position).getAddress());
        intent.putExtra(DETAIL_TEL, mNeighbours.get(position).getPhoneNumber());
        intent.putExtra(IS_FAV_BOOLEAN, mNeighbours.get(position).getIsFav());
        intent.putExtra(CURRENT_OBJECT, mNeighbours.get(position));

        startActivity(intent);

    }

}
