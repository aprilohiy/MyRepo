package org.zh.boardgameplanner.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.zh.boardgameplanner.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService {
    private IPlanner planner;
    private IGameList gameList;
    private static final String DEFAULT_COLLECTION = "/collection.csv";

    @PostConstruct
    public void init() {
        // 使用你原有的 GamesLoader 加载数据
        Set<BoardGame> allGames = GamesLoader.loadGamesFile(DEFAULT_COLLECTION);
        this.planner = new Planner(allGames);
        this.gameList = new GameList();
    }

    // 处理过滤和排序逻辑 (参考你原有的 processFilter)
    public List<BoardGame> getFilteredGames(String filter, String sortOn, boolean ascending) {
        GameData sortCol = GameData.NAME;
        if (sortOn != null && !sortOn.isEmpty()) {
            try {
                sortCol = GameData.fromString(sortOn);
            } catch (IllegalArgumentException e) {
                sortCol = GameData.NAME;
            }
        }
        return planner.filter(filter, sortCol, ascending).collect(Collectors.toList());
    }

    // 处理 List 添加逻辑 (参考你原有的 processListCommands)
    public List<String> addToList(String command, String currentFilter) {
        gameList.addToList(command, planner.filter(currentFilter));
        return gameList.getGameNames();
    }

    public List<String> getMyList() {
        return gameList.getGameNames();
    }

    public void clearList() {
        gameList.clear();
    }

    public void resetPlanner() {
        planner.reset();
    }

    public void removeOneFromList(String gameName) {
        // 调用你之前写的 GameList 里的 removeFromList 方法
        // 确保你的 gameList 变量已经正确初始化
        if (gameList != null) {
            gameList.removeFromList(gameName);
        }
    }

}