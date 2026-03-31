package org.zh.boardgameplanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zh.boardgameplanner.service.GameService; // 确保路径正确

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private GameService gameService;

    /**
     * 获取过滤后的游戏列表
     * 修正点：defaultValue 改为 "NAME"，因为你的 GameData.fromString 可能是区分大小写的
     */
    @GetMapping("/games")
    public List<BoardGame> getGames(
        @RequestParam(required = false, defaultValue = "") String filter,
        @RequestParam(required = false, defaultValue = "NAME") String sortOn,
        @RequestParam(required = false, defaultValue = "true") boolean asc) {
        return gameService.getFilteredGames(filter, sortOn, asc);
    }

    /**
     * 将查询结果中的某些游戏添加到我的 List
     * 修正点：filter 设置为可选 (required = false)，因为单个添加时可能没有过滤条件
     */
    @PostMapping("/list/add")
    public List<String> addToList(
        @RequestParam String cmd,
        @RequestParam(required = false, defaultValue = "") String filter) {
        return gameService.addToList(cmd, filter);
    }

    /**
     * 查看我当前的 List
     */
    @GetMapping("/list")
    public List<String> getMyList() {
        return gameService.getMyList();
    }

    /**
     * 重置过滤器
     */
    @PostMapping("/reset")
    public String reset() {
        gameService.resetPlanner();
        return "Planner reset successfully";
    }

    /**
     * 新增：清空清单接口 (对应 HTML 中的清空功能)
     */
    @PostMapping("/list/clear")
    public List<String> clearList() {
        gameService.clearList();
        return gameService.getMyList();
    }

    @PostMapping("/list/remove")
    public List<String> removeOne(@RequestParam String cmd) {
        // 调用 Service 层，Service 层再调用你的 gameList.removeFromList(cmd)
        gameService.removeOneFromList(cmd);
        return gameService.getMyList(); // 返回删除后的新清单给前端渲染
    }
}