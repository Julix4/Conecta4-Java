package edu.uoc.pac1;

public class PAC1Ex3 {

    public static int MAX_ROWS = 6;
    public static int MAX_COLUMNS = 7;

    // Verifica si la columna es válida
    public static boolean isValidColumn(char[][] board, int column) {
        return column >= 0 && column < MAX_COLUMNS && board[0][column] == ' ';
    }

    // Itera sobre board e imprime en el formato deseado
    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println(); // Salto de línea
        }
    }

    // Verifica si hay 4 fichas del mismo color en línea horizontal
    public static boolean checkWinHorizontal(char[][] board, char color) {
        // Itera sobre las filas
        for (int i = 0; i < board.length; i++) {
            int count = 0;
            // Itera sobre las columnas
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == color) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 0; // Reinicia el contador
                }
            }
        }
        return false;
    }

    // Verifica si hay 4 fichas del mismo color en línea vertical
    public static boolean checkWinVertical(char[][] board, char color) {
        // Itera sobre las filas
        for (int i = 0; i < board[0].length; i++) {
            int count = 0;
            // Itera sobre las columnas
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == color) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 0; // Reinicia el contador
                }
            }
        }
        return false;
    }

    // Verifica si hay 4 fichas del mismo color en diagonal ascendente y descendente
    public static boolean checkWinDiagonal(char[][] board, char color) {
        // Itera sobre las filas
        for (int i = 0; i < board.length; i++) {
            // Itera sobre las columnas
            for (int j = 0; j < board[i].length; j++) {
                // Verifica diagonal ascendente
                if (i - 3 >= 0 && j + 3 < board[i].length) {
                    if (board[i][j] == color &&
                            board[i - 1][j + 1] == color &&
                            board[i - 2][j + 2] == color &&
                            board[i - 3][j + 3] == color) {
                        return true;
                    }
                }
                // Verifica diagonal descendente
                if (i + 3 < board.length && j + 3 < board[i].length) {
                    if (board[i][j] == color &&
                            board[i + 1][j + 1] == color &&
                            board[i + 2][j + 2] == color &&
                            board[i + 3][j + 3] == color) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Retorna true si hay 4 fichas del mismo color en línea horizontal, vertical diagonal
    public static boolean checkWin(char[][] board, char color) {
        return checkWinHorizontal(board, color) || checkWinVertical(board, color) || checkWinDiagonal(board, color);
    }

    // Verifica si hay espacio disponible en la columna asignada y coloca la ficha en la posición correspondiente. Además avisa si hay ganador.
    public static char[][] dropPiece(char[][] board, int column, char color) {
        if (!isValidColumn(board, column)) {
            System.out.println("This column is invalid or full.");
            return board;
        }

        // Recorre la columna desde abajo hacia arriba
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = color;
                break;
            }
        }

        printBoard(board);

        // Verifica si hay ganador
        if (checkWin(board, color)) {
            System.out.println("Color " + color + " wins!");
        }
        return board;
    }

}
