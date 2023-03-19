package com.tictactoebotapi.TicTacToeBotAPI.model;

public class Bot {
    private static Bot classInstance = null;
    private Bot(){}
    public static Bot getInstance(){
        if(classInstance == null){
            classInstance = new Bot();
        }
        return Bot.classInstance;
    }
}
