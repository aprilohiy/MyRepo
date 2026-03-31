package org.zh.boardgameplanner;

import java.util.Comparator;

/**
 * The way to sort board games.
 *
 */
public final class Sorts {
    /**
     * Private constructor.
     */
    private Sorts() {
    }

    /**
     * Get the method to sort board games.
     * @param attribute to sort
     * @param ascending if sort by ascending order
     * @return sort method
     */
    public static Comparator<BoardGame> getSortMethod(GameData attribute, boolean ascending) {
        switch (attribute) {
            case NAME:
                return ascending ? new NameAscending() : new NameDescending();
            case RATING:
                return ascending ? new RatingAscending() : new RatingDescending();
            case DIFFICULTY:
                return ascending ? new DifficultyAscending() : new DifficultyDescending();
            case RANK:
                return ascending ? new RankAscending() : new RankDescending();
            case MIN_PLAYERS:
                return ascending ? new MinPlayersAscending() : new MinPlayersDescending();
            case MAX_PLAYERS:
                return ascending ? new MaxPlayersAscending() : new MaxPlayersDescending();
            case MIN_TIME:
                return ascending ? new MinTimeAscending() : new MinTimeDescending();
            case MAX_TIME:
                return ascending ? new MaxTimeAscending() : new MaxTimeDescending();
            case YEAR:
                return ascending ? new YearAscending() : new YearDescending();
            default:
                return new NameAscending();
        }
    }

    /**
     * Comparator for BoardGame that sorts by name in ascending order.
     */
    public static class NameAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return g1.getName().compareToIgnoreCase(g2.getName());
        }
    }

    /**
     * Comparator for BoardGame that sorts by name in descending order.
     */
    public static class NameDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return g2.getName().compareToIgnoreCase(g1.getName());
        }
    }

    /**
     * Comparator for BoardGame that sorts by rating in ascending order.
     */
    public static class RatingAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g1.getRating(), g2.getRating());
        }
    }

    /**
     * Comparator for BoardGame that sorts by rating in descending order.
     */
    public static class RatingDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g2.getRating(), g1.getRating());
        }
    }

    /**
     * Comparator for BoardGame that sorts by difficulty in ascending order.
     */
    public static class DifficultyAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g1.getDifficulty(), g2.getDifficulty());
        }
    }

    /**
     * Comparator for BoardGame that sorts by difficulty in descending order.
     */
    public static class DifficultyDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g2.getDifficulty(), g1.getDifficulty());
        }
    }

    /**
     * Comparator for BoardGame that sorts by rank in ascending order.
     */
    public static class RankAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g1.getRank(), g2.getRank());
        }
    }

    /**
     * Comparator for BoardGame that sorts by rank in descending order.
     */
    public static class RankDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g2.getRank(), g1.getRank());
        }
    }

    /**
     * Comparator for BoardGame that sorts by min players in ascending order.
     */
    public static class MinPlayersAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g1.getMinPlayers(), g2.getMinPlayers());
        }
    }

    /**
     * Comparator for BoardGame that sorts by min players in descending order.
     */
    public static class MinPlayersDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g2.getMinPlayers(), g1.getMinPlayers());
        }
    }

    /**
     * Comparator for BoardGame that sorts by max players in ascending order.
     */
    public static class MaxPlayersAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g1.getMaxPlayers(), g2.getMaxPlayers());
        }
    }

    /**
     * Comparator for BoardGame that sorts by max players in descending order.
     */
    public static class MaxPlayersDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g2.getMaxPlayers(), g1.getMaxPlayers());
        }
    }

    /**
     * Comparator for BoardGame that sorts by min time in ascending order.
     */
    public static class MinTimeAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g1.getMinPlayTime(), g2.getMinPlayTime());
        }
    }

    /**
     * Comparator for BoardGame that sorts by min time in descending order.
     */
    public static class MinTimeDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g2.getMinPlayTime(), g1.getMinPlayTime());
        }
    }

    /**
     * Comparator for BoardGame that sorts by max time in ascending order.
     */
    public static class MaxTimeAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g1.getMaxPlayTime(), g2.getMaxPlayTime());
        }
    }

    /**
     * Comparator for BoardGame that sorts by max time in descending order.
     */
    public static class MaxTimeDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g2.getMaxPlayTime(), g1.getMaxPlayTime());
        }
    }

    /**
     * Comparator for BoardGame that sorts by year published in ascending order.
     */
    public static class YearAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g1.getYearPublished(), g2.getYearPublished());
        }
    }

    /**
     * Comparator for BoardGame that sorts by year published in descending order.
     */
    public static class YearDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame g1, BoardGame g2) {
            return Double.compare(g2.getYearPublished(), g1.getYearPublished());
        }
    }


}
