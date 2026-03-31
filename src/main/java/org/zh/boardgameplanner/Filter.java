/**
 *Filters the board games by the passed in text filter.
 *
 */
package org.zh.boardgameplanner;

import java.util.List;
import java.util.stream.Stream;

public final class Filter {

    /**
     * Private constructor.
     */
    private Filter() {
    }

    /**
     * Filters the board games by the passed in text filter.
     *
     * @param planner board game stream
     * @param filter string to filter games
     * @return filtered board game stream
     */
    public static Stream<BoardGame> filteringGame(Stream<BoardGame> planner, String filter) {

        if (filter == null || filter.isEmpty()) {
            return planner;
        }

        // replace the space and split the filter by ","
        List<String> filters = List.of(filter.replaceAll(" ", "").split(","));

        // for each filter, get the operator and the game data

        List<BoardGame> filtered = planner.toList();
        GameData column;
        String value = "";

        for (String f : filters) {
            Operations op = Operations.getOperatorFromStr(f);
            if (op != null) {
                String[] parts = f.split(op.getOperator());
                if (parts.length == 2) {
                    try {
                        column = GameData.fromString(parts[0]);
                    } catch (IllegalArgumentException e) {
                        continue;
                    }
                    value = parts[1];
                    if (column == GameData.NAME) {
                        filtered = stringFilter(filtered.stream(), column, op, value).toList();
                    } else {
                        filtered = numberFilter(filtered.stream(), column, op, value).toList();
                    }
                }
            }
        }
        return filtered.stream();
    }

    /**
     * Compare two doubles using operation op.
     * @param a the first number for comparison
     * @param b the second number for comparison
     * @param op the operation
     * @return the compare results
     */
    public static boolean compare(double a, double b, Operations op) {
        switch (op) {
            case EQUALS:
                return a == b;
            case NOT_EQUALS:
                return a != b;
            case GREATER_THAN:
                return a > b;
            case LESS_THAN:
                return a < b;
            case GREATER_THAN_EQUALS:
                return a >= b;
            case LESS_THAN_EQUALS:
                return a <= b;
            default:
                return false;
        }
    }

    /**
     * Filter by numbers.
     * @param filtered the board games stream
     * @param col the column
     * @param op the operation
     * @param v the filter value
     * @return filtered board game stream
     */
    public static Stream<BoardGame> numberFilter(Stream<BoardGame> filtered, GameData col, Operations op, String v) {
        double value = Double.parseDouble(v);
        switch (col) {
            case RATING:
                return filtered.filter(bg -> compare(bg.getRating(), value, op));
            case DIFFICULTY:
                return filtered.filter(bg -> compare(bg.getDifficulty(), value, op));
            case RANK:
                return filtered.filter(bg -> compare(bg.getRank(), value, op));
            case MIN_PLAYERS:
                return filtered.filter(bg -> compare(bg.getMinPlayers(), value, op));
            case MAX_PLAYERS:
                return filtered.filter(bg -> compare(bg.getMaxPlayers(), value, op));
            case MIN_TIME:
                return filtered.filter(bg -> compare(bg.getMinPlayTime(), value, op));
            case MAX_TIME:
                return filtered.filter(bg -> compare(bg.getMaxPlayTime(), value, op));
            case YEAR:
                return filtered.filter(bg -> compare(bg.getYearPublished(), value, op));
            default:
                return filtered;
        }
    }

    /**
     * Filter by string.
     * @param filtered the board games stream
     * @param col the column
     * @param op the operation
     * @param v the filter value
     * @return filtered board game stream
     */
    public static Stream<BoardGame> stringFilter(Stream<BoardGame> filtered, GameData col, Operations op, String v) {
        switch (op) {
            case EQUALS:
                return filtered.filter(
                    bg -> bg.getName().replaceAll("\\s", "").equalsIgnoreCase(v)
                );
            case NOT_EQUALS:
                return filtered.filter(
                    bg -> !bg.getName().replaceAll("\\s", "").equalsIgnoreCase(v)
                );
            case CONTAINS:
                return filtered.filter(
                    bg -> bg.getName().toLowerCase().replaceAll("\\s", "")
                        .contains(v.toLowerCase())
                );
            case GREATER_THAN:
                return filtered.filter(
                    bg -> bg.getName().replaceAll("\\s", "").compareToIgnoreCase(v) > 0
                );
            case GREATER_THAN_EQUALS:
                return filtered.filter(
                    bg -> bg.getName().replaceAll("\\s", "").compareToIgnoreCase(v) >= 0
                );
            case LESS_THAN:
                return filtered.filter(
                    bg -> bg.getName().replaceAll("\\s", "").compareToIgnoreCase(v) < 0
                );
            case LESS_THAN_EQUALS:
                return filtered.filter(
                    bg -> bg.getName().replaceAll("\\s", "").compareToIgnoreCase(v) <= 0
                );
            default:
                return filtered;
        }
    }
}


