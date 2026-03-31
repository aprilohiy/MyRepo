package org.zh.boardgameplanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A list of games selected by the user, implementing IGameList.
 *
 */
public class GameList implements IGameList {
    /** Set of board games. */
    private Set<BoardGame> gameList;

    /**
     * Constructor for the GameList.
     */
    public GameList() {
//        throw new UnsupportedOperationException("Unimplemented constructor 'GameList'");
        gameList = new TreeSet<>(
            (a, b) -> a.getName().compareToIgnoreCase(b.getName())
        );
    }

    /**
     * Gets the game names of a list.
     *
     * @return the list of game names in ascending order ignoring case.
     */
    @Override
    public List<String> getGameNames() {
        // TODO Auto-generated method stub
        return gameList.stream().map(BoardGame::getName).toList();
    }

    /**
     * Removes all games in the list (clears it out completely).
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        gameList.clear();
    }

    /**
     * Counts/returns the number of games in the list.
     *
     * @return the number of games in the list.
     */
    @Override
    public int count() {
        // TODO Auto-generated method stub
        return gameList.size();
    }

    /**
     * Saves the list of games to a file in the same order as getGameNames.
     *
     * @param filename The name of the file to save the list to.
     */
    @Override
    public void saveGame(String filename) {
        // TODO Auto-generated method stub
        try {
            Files.write(
                Path.of(filename),
                gameList.stream().map(BoardGame::toString).collect(Collectors.toList()));
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Adds a game or games to the list.
     *
     * @param str      the string to parse and add games to the list.
     * @param filtered the filtered list to use as a basis for adding.
     * @throws IllegalArgumentException if the string is not valid.
     */
    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        List<BoardGame> filteredList = filtered
            .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
            .toList();

        if (str.equals(ADD_ALL)) {
            gameList.addAll(filteredList);
            return;
        }

        for (BoardGame game : filteredList) {
            if (game.getName().equalsIgnoreCase(str.trim())) {
                gameList.add(game);
                return;
            }
        }

        int a = 0;
        int b = 0;

        if (str.contains("-")) {
            String[] parts = str.split("-");
            if (parts.length == 2) {
                a = Integer.parseInt(parts[0].trim());
                b = Integer.parseInt(parts[1].trim());
                b = Math.min(filteredList.size(), b);
            }
        } else {
            a = Integer.parseInt(str.trim());
            b = a;
        }

        if (a <= 0 || a > b || b > filteredList.size()) {
            throw new IllegalArgumentException("Ranges out of bounds");
        }

        for (int i = a; i <= b; i++) {
            gameList.add(filteredList.get(i - 1));
        }
    }

    /**
     * Removes a game or games from the list.
     *
     * @param str The string to parse and remove games from the list.
     * @throws IllegalArgumentException If the string is not valid.
     */
    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        // remove a specified name
        if (!gameList.removeIf(bg -> bg.getName().equalsIgnoreCase(str.trim()))) {
            // remove a number or a range
            if (str.equals(ADD_ALL)) {
                gameList.clear();
                return;
            }

            List<BoardGame> tempList = new ArrayList<>(gameList);
            int a = 0;
            int b = 0;

            if (str.contains("-")) {
                String[] parts = str.split("-");
                if (parts.length == 2) {
                    a = Integer.parseInt(parts[0].trim());
                    b = Integer.parseInt(parts[1].trim());
                }
            } else {
                a = Integer.parseInt(str.trim());
                b = a;
            }

            if (a <= 0 || a > b || b > gameList.size()) {
                throw new IllegalArgumentException("Ranges out of bounds");
            }

            for (int i = a; i <= b; i++) {
                gameList.remove(tempList.get(i - 1));
            }
        }
    }
}
