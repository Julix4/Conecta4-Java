package edu.uoc.pac1;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PAC1Ex3Test {

    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @Order(1)
    @DisplayName("isValidColumn")
    public void testIsValidColumn() {
        // 'R': red
        // 'Y': yellow
        // ' ': empty

        char[][] board = {
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.isValidColumn(board, -1));
        assertFalse(PAC1Ex3.isValidColumn(board, 8));

        assertTrue(PAC1Ex3.isValidColumn(board, 0));
        assertFalse(PAC1Ex3.isValidColumn(board, 1));
        assertTrue(PAC1Ex3.isValidColumn(board, 2));
        assertTrue(PAC1Ex3.isValidColumn(board, 3));
        assertTrue(PAC1Ex3.isValidColumn(board, 4));
        assertTrue(PAC1Ex3.isValidColumn(board, 5));
        assertTrue(PAC1Ex3.isValidColumn(board, 6));
        assertFalse(PAC1Ex3.isValidColumn(board, 7));

        board = new char[][]{
                {' ', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {' ', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {' ', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {' ', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {' ', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {' ', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.isValidColumn(board, 0));
        assertFalse(PAC1Ex3.isValidColumn(board, 1));
        assertFalse(PAC1Ex3.isValidColumn(board, 2));
        assertFalse(PAC1Ex3.isValidColumn(board, 3));
        assertFalse(PAC1Ex3.isValidColumn(board, 4));
        assertFalse(PAC1Ex3.isValidColumn(board, 5));
        assertFalse(PAC1Ex3.isValidColumn(board, 6));
        assertFalse(PAC1Ex3.isValidColumn(board, 7));
    }

    @Test
    @Order(2)
    @DisplayName("printBoard")
    public void testPrintBoard() {
        char[][] board = {
                {' ', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {' ', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {' ', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {' ', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {' ', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {' ', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        PAC1Ex3.printBoard(board);

        String expected = "| |Y|Y|R|R|Y|Y|" + System.lineSeparator() +
                "| |R|R|Y|Y|R|R|" + System.lineSeparator() +
                "| |Y|Y|R|R|Y|Y|" + System.lineSeparator() +
                "| |R|R|Y|Y|R|R|" + System.lineSeparator() +
                "| |Y|Y|R|R|Y|Y|" + System.lineSeparator() +
                "| |R|R|Y|Y|R|R|" + System.lineSeparator();

        assertEquals(expected, outContent.toString());
        outContent.reset();
    }

    @Test
    @Order(3)
    @DisplayName("checkWinHorizontal")
    public void testCheckWinHorizontal() {
        // 'R': red
        // 'Y': yellow
        // ' ': empty

        char[][] board = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        // Empty board
        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', 'R', 'R', 'R', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinHorizontal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'R'));
        assertTrue(PAC1Ex3.checkWinHorizontal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'R', 'R', 'R', 'R', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinHorizontal(board, 'Y'));
        assertTrue(PAC1Ex3.checkWinHorizontal(board, 'R'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'Y'));
        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'R'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'R', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWinHorizontal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinHorizontal(board, 'Y'));
    }

    @Test
    @Order(4)
    @DisplayName("checkWinVertical")
    public void testCheckWinVertical() {
        // 'R': red
        // 'Y': yellow
        // ' ': empty

        char[][] board = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        // Empty board
        assertFalse(PAC1Ex3.checkWinVertical(board, 'R'));
        assertFalse(PAC1Ex3.checkWinVertical(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinVertical(board, 'R'));
        assertFalse(PAC1Ex3.checkWinVertical(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinVertical(board, 'R'));
        assertFalse(PAC1Ex3.checkWinVertical(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinVertical(board, 'Y'));
        assertFalse(PAC1Ex3.checkWinVertical(board, 'R'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinVertical(board, 'R'));
        assertTrue(PAC1Ex3.checkWinVertical(board, 'Y'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertFalse(PAC1Ex3.checkWinVertical(board, 'Y'));
        assertFalse(PAC1Ex3.checkWinVertical(board, 'R'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'Y', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'R', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'R', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWinVertical(board, 'R'));
        assertFalse(PAC1Ex3.checkWinVertical(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'Y', 'Y', 'Y', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinVertical(board, 'R'));
        assertFalse(PAC1Ex3.checkWinVertical(board, 'Y'));
    }

    @Test
    @Order(5)
    @DisplayName("checkWinDiagonal")
    public void testCheckWinDiagonal() {
        // 'R': red
        // 'Y': yellow
        // ' ': empty

        char[][] board = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        // Empty board
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', 'Y'},
                {' ', ' ', ' ', ' ', ' ', 'Y', ' '},
                {' ', ' ', ' ', ' ', 'Y', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'Y', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Y', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'Y', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinDiagonal(board, 'Y'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'R'},
                {' ', ' ', ' ', ' ', ' ', 'R', ' '},
                {' ', ' ', ' ', ' ', 'R', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', 'Y', ' ', ' ', ' '},
                {' ', ' ', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWinDiagonal(board, 'Y'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));

        board = new char[][]{
                {'Y', 'R', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'R', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'Y', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'Y', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'R', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWinDiagonal(board, 'Y'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'R', 'Y', 'R', 'R'},
                {'Y', 'Y', 'R', 'R', 'R', 'Y', 'Y'},
                {'R', 'Y', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'Y', 'R', 'Y', 'Y'},
                {'R', 'R', 'Y', 'Y', 'Y', 'R', 'R'},
                {'Y', 'R', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWinDiagonal(board, 'Y'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'Y', 'Y', 'Y', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'R'));
        assertFalse(PAC1Ex3.checkWinDiagonal(board, 'Y'));
    }

    @Test
    @Order(6)
    @DisplayName("checkWin")
    public void testCheckWin() {
        // 'R': red
        // 'Y': yellow
        // ' ': empty

        char[][] board = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        // Unexpected color
        assertFalse(PAC1Ex3.checkWin(board, 'Q'));
        assertFalse(PAC1Ex3.checkWin(board, '1'));

        // Empty board
        assertFalse(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', 'Y'},
                {' ', ' ', ' ', ' ', ' ', 'Y', ' '},
                {' ', ' ', ' ', ' ', 'Y', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', 'R', 'R', 'R', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertFalse(PAC1Ex3.checkWin(board, 'R'));
        assertTrue(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'Y'));
        assertFalse(PAC1Ex3.checkWin(board, 'R'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'Y', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Y', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'Y', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'Y'));
        assertFalse(PAC1Ex3.checkWin(board, 'R'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'R'},
                {' ', ' ', ' ', ' ', ' ', 'R', ' '},
                {' ', ' ', ' ', ' ', 'R', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {' ', ' ', ' ', 'Y', ' ', ' ', ' '},
                {' ', ' ', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'Y'));
        assertFalse(PAC1Ex3.checkWin(board, 'R'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'R', 'R', 'R', 'R', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'Y'));
        assertTrue(PAC1Ex3.checkWin(board, 'R'));

        board = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', ' ', ' ', ' ', ' ', ' '}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertTrue(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertFalse(PAC1Ex3.checkWin(board, 'Y'));
        assertFalse(PAC1Ex3.checkWin(board, 'R'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'R', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'Y', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'R', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'R', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {'Y', 'R', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'R', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'Y', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'Y', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'R', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'Y'));
        assertFalse(PAC1Ex3.checkWin(board, 'R'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'R', 'Y', 'R', 'R'},
                {'Y', 'Y', 'R', 'R', 'R', 'Y', 'Y'},
                {'R', 'Y', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'R'));
        assertFalse(PAC1Ex3.checkWin(board, 'Y'));

        board = new char[][]{
                {'Y', 'Y', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'},
                {'Y', 'Y', 'Y', 'Y', 'R', 'Y', 'Y'},
                {'R', 'R', 'Y', 'Y', 'Y', 'R', 'R'},
                {'Y', 'R', 'Y', 'R', 'R', 'Y', 'Y'},
                {'R', 'R', 'R', 'Y', 'Y', 'R', 'R'}
        };

        assertTrue(PAC1Ex3.checkWin(board, 'Y'));
        assertFalse(PAC1Ex3.checkWin(board, 'R'));
    }

    @Test
    @Order(7)
    @DisplayName("dropPiece")
    public void testDropPiece() {
        // 'R': red
        // 'Y': yellow
        // ' ': empty

        char[][] board = {
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        char[][] expectedBoard = {
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        String expectedOutput = "This column is invalid or full.";

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, -1, 'R'));
        assertEquals(expectedOutput, outContent.toString().trim());
        outContent.reset();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 7, 'R'));
        assertEquals(expectedOutput, outContent.toString().trim());
        outContent.reset();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 1, 'R'));
        assertEquals(expectedOutput, outContent.toString().trim());
        outContent.reset();

        expectedBoard = new char[][]{
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        expectedOutput = "| |Y| | | | | |" + System.lineSeparator() +
                        "| |R|R| | | | |" + System.lineSeparator() +
                        "| |Y|Y| | | | |" + System.lineSeparator() +
                        "| |R|R| | | | |" + System.lineSeparator() +
                        "| |Y|Y| | | | |" + System.lineSeparator() +
                        "|Y|R|R| | | | |" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 0, 'Y'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        expectedBoard = new char[][]{
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        expectedOutput = "| |Y| | | | | |" + System.lineSeparator() +
                        "| |R|R| | | | |" + System.lineSeparator() +
                        "| |Y|Y| | | | |" + System.lineSeparator() +
                        "| |R|R| | | | |" + System.lineSeparator() +
                        "|R|Y|Y| | | | |" + System.lineSeparator() +
                        "|Y|R|R| | | | |" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 0, 'R'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        expectedBoard = new char[][]{
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {' ', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        expectedOutput = "| |Y| | | | | |" + System.lineSeparator() +
                "| |R|R| | | | |" + System.lineSeparator() +
                "| |Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 0, 'Y'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        expectedBoard = new char[][]{
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {' ', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        expectedOutput = "| |Y| | | | | |" + System.lineSeparator() +
                "| |R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 0, 'R'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        expectedBoard = new char[][]{
                {' ', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        expectedOutput = "| |Y| | | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 0, 'Y'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        expectedBoard = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '}
        };

        expectedOutput = "|R|Y| | | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 0, 'R'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        expectedBoard = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', ' ', ' ', ' '}
        };

        expectedOutput = "|R|Y| | | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R|R| | | |" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 3, 'R'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        expectedBoard = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'R', ' ', ' '}
        };

        expectedOutput = "|R|Y| | | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R|R|R| | |" + System.lineSeparator() +
                "Color R wins!" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 4, 'R'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        board = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'Y', ' ', ' '}
        };

        expectedBoard = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', 'Y', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'Y', ' ', ' '}
        };

        expectedOutput = "|R|Y| | | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y| | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y|Y|Y| | |" + System.lineSeparator() +
                "|Y|R|R|R|Y| | |" + System.lineSeparator() +
                "Color Y wins!" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 4, 'Y'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        board = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'Y', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'Y', ' ', ' '}
        };

        expectedBoard = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'Y', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'Y', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'Y', ' ', ' '}
        };

        expectedOutput = "|R|Y| | | | | |" + System.lineSeparator() +
                "|Y|R|R|Y| | | |" + System.lineSeparator() +
                "|R|Y|Y|Y| | | |" + System.lineSeparator() +
                "|Y|R|R|Y| | | |" + System.lineSeparator() +
                "|R|Y|Y|Y| | | |" + System.lineSeparator() +
                "|Y|R|R|R|Y| | |" + System.lineSeparator() +
                "Color Y wins!" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 3, 'Y'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        board = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'Y', 'Y', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'Y', ' ', ' '}
        };

        expectedBoard = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'Y', 'Y', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'Y', ' ', ' '}
        };

        expectedOutput = "|R|Y| | | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|Y|Y| | | |" + System.lineSeparator() +
                "|Y|R|Y|Y| | | |" + System.lineSeparator() +
                "|R|Y|Y|Y| | | |" + System.lineSeparator() +
                "|Y|R|R|R|Y| | |" + System.lineSeparator() +
                "Color Y wins!" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 3, 'Y'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();

        board = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'R', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'Y', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'Y', ' ', ' '}
        };

        expectedBoard = new char[][]{
                {'R', 'Y', ' ', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'R', ' ', ' ', ' ', ' '},
                {'R', 'Y', 'R', ' ', ' ', ' ', ' '},
                {'Y', 'R', 'Y', 'R', ' ', ' ', ' '},
                {'R', 'Y', 'Y', 'Y', ' ', ' ', ' '},
                {'Y', 'R', 'R', 'R', 'Y', ' ', ' '}
        };

        expectedOutput = "|R|Y| | | | | |" + System.lineSeparator() +
                "|Y|R|R| | | | |" + System.lineSeparator() +
                "|R|Y|R| | | | |" + System.lineSeparator() +
                "|Y|R|Y|R| | | |" + System.lineSeparator() +
                "|R|Y|Y|Y| | | |" + System.lineSeparator() +
                "|Y|R|R|R|Y| | |" + System.lineSeparator() +
                "Color R wins!" + System.lineSeparator();

        assertArrayEquals(expectedBoard, PAC1Ex3.dropPiece(board, 3, 'R'));
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();
    }

}
