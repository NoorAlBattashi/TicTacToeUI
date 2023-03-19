package com.tictactoebotapi.TicTacToeBotAPI.service;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class BotService {
    public int makeMove(String[] board) {
        char botSymbol = 'O';
        int move = -1; // the move that the bot will make
        int n = board.length; // the size of the board

        // check if the bot can win on the next move
        for (int i = 0; i < n; i++) {
            if (board[i].charAt(0) == botSymbol) {
                // check row
                int row = i / n;
                int col = i % n;
                boolean canWin = true;
                for (int j = 0; j < n; j++) {
                    if (board[row * n + j].charAt(0) != botSymbol) {
                        canWin = false;
                        break;
                    }
                }
                if (canWin) {
                    move = row * n + col;
                    break;
                }

                // check column
                canWin = true;
                for (int j = 0; j < n; j++) {
                    if (board[j * n + col].charAt(0) != botSymbol) {
                        canWin = false;
                        break;
                    }
                }
                if (canWin) {
                    move = row * n + col;
                    break;
                }

                // check diagonal
                if (row == col) {
                    canWin = true;
                    for (int j = 0; j < n; j++) {
                        if (board[j * n + j].charAt(0) != botSymbol) {
                            canWin = false;
                            break;
                        }
                    }
                    if (canWin) {
                        move = row * n + col;
                        break;
                    }
                }

                // check anti-diagonal
                if (row == n - col - 1) {
                    canWin = true;
                    for (int j = 0; j < n; j++) {
                        if (board[j * n + (n - j - 1)].charAt(0) != botSymbol) {
                            canWin = false;
                            break;
                        }
                    }
                    if (canWin) {
                        move = row * n + col;
                        break;
                    }
                }
            }
        }

        // if the bot can't win on the next move, try to block the opponent
        if (move == -1) {
            char opponentSymbol = botSymbol == 'X' ? 'O' : 'X';
            for (int i = 0; i < n; i++) {
                if (board[i].charAt(0) == opponentSymbol) {
                    // check row
                    int row = i / n;
                    int col = i % n;
                    boolean canBlock = true;
                    for (int j = 0; j < n; j++) {
                        if (board[row * n + j].charAt(0) != opponentSymbol) {
                            canBlock = false;
                            break;
                        }
                    }
                    if (canBlock) {
                        move = row * n + col;
                        break;
                    }

                    // check diagonal
                    if (row == col) {
                        canBlock = true;
                        for (int j = 0; j < n; j++) {
                            if (board[j * n + j].charAt(0) != opponentSymbol) {
                                canBlock = false;
                                break;
                            }
                        }
                        if (canBlock) {
                            move = row * n + col;
                            break;
                        }
                    }

                    // check anti-diagonal
                    if (row == n - col - 1) {
                        canBlock = true;
                        for (int j = 0; j < n; j++) {
                            if (board[j * n + (n - j - 1)].charAt(0) != opponentSymbol) {
                                canBlock = false;
                                break;
                            }
                        }
                        if (canBlock) {
                            move = row * n + col;
                            break;
                        }
                    }
                }
            }
        }

        // if the bot can't win or block, make a random move
        if (move == -1) {
            Random rand = new Random();
            do {
                move = rand.nextInt(n * n);
            } while (board[move].charAt(0) != ' ');
        }

        return move;
    }

    }


