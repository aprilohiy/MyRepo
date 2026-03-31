package org.zh.boardgameplanner;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.zh.boardgameplanner.GameData.NAME;

/**
 * Sets up filters for the board game data, implementing IPlanner.
 *
 */
public class Planner implements IPlanner {
    /** Set of board games. */
    private Set<BoardGame> games;
    /** Set of all board games. */
    private final Set<BoardGame> allGames;

    /**
     * Sets up filters for the board game data.
     */
    public Planner() {
        // TODO Auto-generated method stub
        this.games = new HashSet<>();
        this.allGames = new HashSet<>();
    }

    /**
     * Sets up filters for the board game data.
     * @param games set of board games
     */
    public Planner(Set<BoardGame> games) {
        // TODO Auto-generated method stub
        this.games = games;
        this.allGames = games;
    }

    /**
     * Assumes the stream is sorted by the name of the board game in ascending order.
     *
     * @param filter The filter to apply to the board games.
     * @return A stream of board games that match the filter.
     * @see #filter(String, GameData, boolean)
     */
    @Override
    public Stream<BoardGame> filter(String filter) {
        // TODO Auto-generated method stub
        return filter(filter, NAME, true);
    }

    /**
     Assumes the results are sorted in ascending order by the passed in text filter.
     *
     * @param filter The filter to apply to the board games.
     * @param sortOn The column to sort the results on.
     * @return A stream of board games that match the filter.
     * @see #filter(String, GameData, boolean)
     */
    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        // TODO Auto-generated method stub
        return filter(filter, sortOn, true);
    }

    /**
     * Filters the board games by the passed in text filter.
     *
     * @param filter The filter to apply to the board games.
     * @param sortOn The column to sort the results on.
     * @param ascending Whether to sort the results in ascending order or descending order.
     * @return A stream of board games that match the filter.
     */
    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        // TODO Auto-generated method stub
        if (!filter.isEmpty()) {
            games = Filter.filteringGame(games.stream(), filter)
                    .collect(Collectors.toSet());
        }
        return games.stream().sorted(Sorts.getSortMethod(sortOn, ascending));
    }

    /**
     * Resets the collection to have no filters applied.
     */
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        games = allGames;
    }

}
