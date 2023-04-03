package com.tictactoebotapi.TicTacToeBotAPI.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class BotService {
    public static int getBotMove(String[] board) {
        int move = -1; // The bot's move

        // Check if there is a winning move for the bot
        for (int i = 0; i < 9; i++) {
            if (board[i].equals("")) { // Check for empty string instead of null
                board[i] = "O"; // Assume the bot is "O"
                if (checkWin(board, "O")) {
                    move = i;
                    board[i] = ""; // Undo the move
                    return move;
                }
                board[i] = ""; // Undo the move
            }
        }

        // Check if there is a winning move for the player
        for (int i = 0; i < 9; i++) {
            if (board[i].equals("")) { // Check for empty string instead of null
                board[i] = "X"; // Assume the player is "X"
                if (checkWin(board, "X")) {
                    move = i;
                    board[i] = ""; // Undo the move
                    return move;
                }
                board[i] = ""; // Undo the move
            }
        }

        // Make a random move
        Random rand = new Random();
        do {
            move = rand.nextInt(9);
        } while (!board[move].equals(""));
        return move;
    }

    // Helper method to check if a player has won
    public static boolean checkWin(String[] board, String player) {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i].equals(player) && board[i+1].equals(player) && board[i+2].equals(player)) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(player) && board[i+3].equals(player) && board[i+6].equals(player)) {
                return true;
            }
        }
        // Check diagonals
        if (board[0].equals(player) && board[4].equals(player) && board[8].equals(player)) {
            return true;
        }
        if (board[2].equals(player) && board[4].equals(player) && board[6].equals(player)) {
            return true;
        }
        return false;
    }
    }


