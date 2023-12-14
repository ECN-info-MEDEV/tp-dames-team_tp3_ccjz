/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package jeudedames;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author chloe
 */
public class JeuDeDamesTest {
    
    public JeuDeDamesTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of makeMove method, of class JeuDeDames.
     */
    @Test
    public void testMakeMove() {
        System.out.println("makeMove");
        int fromRow =2;
        int fromCol = 1;
        int toRow = 3;
        int toCol = 0;
        JeuDeDames instance = new JeuDeDames();
        instance.printBoard(instance.getBoard());
        System.out.println("New board : ");
        instance.makeMove(fromRow, fromCol, toRow, toCol);
        instance.printBoard(instance.getBoard());
    }

    /**
     * Test of getCurrentPlayer method, of class JeuDeDames.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        JeuDeDames instance = new JeuDeDames();
        int expResult = 0;
        int result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoard method, of class JeuDeDames.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        JeuDeDames instance = new JeuDeDames();
        int[][] expResult = null;
        int[][] result = instance.getBoard();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class JeuDeDames.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JeuDeDames.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
