package com.openclassrooms.entrevoisins.service;



import android.app.Activity;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;


import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }




    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addFavoriteWithSuccess() {

        Neighbour neighbourToAddFavorite = service.getNeighbours().get(0);
        service.addFavoriteNeighbour(neighbourToAddFavorite);
        assertTrue(service.getFavNeighbours().contains(neighbourToAddFavorite));


        }


    @Test
    public void getFavoriteNeighboursWithSuccess() {
        List<Neighbour> favNeighbours = service.getFavNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.FAV_NEIGHBOURS;
        assertThat(favNeighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));


    }

    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        Neighbour favNeighbourTest = new Neighbour(6, "Sylvain", "https://i.pravatar.cc/150?u=a042581f4e29026704c", "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
       service.addFavoriteNeighbour(favNeighbourTest);
        Neighbour favoriteNeighbourToDelete = service.getFavNeighbours().get(0);
        service.deleteFavoriteNeighbour(favoriteNeighbourToDelete);
        assertFalse(service.getFavNeighbours().contains(favoriteNeighbourToDelete));
    }



    @Test
    public void addNewNeighbourWithSuccess() {

        Neighbour newNeighbourToAdd = new Neighbour(15, "Sylvain", "https://i.pravatar.cc/150?u=a042581f4e29026704c", "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.createNeighbour(newNeighbourToAdd);
        assertTrue(service.getNeighbours().contains(newNeighbourToAdd));



    }




}
