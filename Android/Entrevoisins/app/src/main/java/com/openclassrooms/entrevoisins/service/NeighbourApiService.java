package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my Favorites Neighbours
     * @return {@link List}
     */

    List<Neighbour> getFavNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Add a neighbour favorite
     * @param neighbour
     */
    void addFavoriteNeighbour(Neighbour neighbour);

    /**
     * Delete a neighbour favorite
     * @param neighbour
     */
    void deleteFavoriteNeighbour(Neighbour neighbour);

}
