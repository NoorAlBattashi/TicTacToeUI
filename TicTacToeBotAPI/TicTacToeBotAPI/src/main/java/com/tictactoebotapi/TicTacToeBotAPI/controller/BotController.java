package com.tictactoebotapi.TicTacToeBotAPI.controller;

import com.tictactoebotapi.TicTacToeBotAPI.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/bot")
public class BotController {
    @Autowired
    public BotService botService;

    @PostMapping()
    public HashMap<String, Integer> play(
            @RequestBody String[] ticTacToe
    ) {
        HashMap<String, Integer> botMove = new HashMap<>();
        botMove.put("botMove", botService.makeMove(ticTacToe));
        return botMove;
    }

}
