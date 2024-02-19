/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = new BoundedSetOfNaturals(2);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testAddElement() {
        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());
    }

    @Test
    public void testAddToFull() {
        assertThrows(IllegalArgumentException.class, () -> setB.add(11));
        assertFalse(setB.contains(11), "add: redundant element was added to a set.");
        assertEquals(6, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddDuplicate() {
        setD.add(10);
        assertThrows(IllegalArgumentException.class, () -> setD.add(10));
    }

    @Test
    public void testAddNotANumber() {
        assertThrows(IllegalArgumentException.class, () -> setA.add(0));
        assertThrows(IllegalArgumentException.class, () -> setA.add(-100));
    }

    @Disabled
    @Test
    public void testIntersection() {
        assertTrue(setB.intersects(setC), "intersection: intersection isn't detected");
        setA.add(70);
        assertFalse(setA.intersects(setC), "intersection: false intersection is detected");
    }

    @Test
    public void testHash() {
        assertNotEquals(setB.hashCode(), setC.hashCode(), "hash: two different sets have the same hash!");
        assertNotEquals(setB.hashCode(), setC.hashCode(), "hash: the same set has a different hash!");
    }

    @Test
    public void testEquals() {
        BoundedSetOfNaturals setE = setA;
        assertTrue(setE.equals(setA));
        assertFalse(setE.equals(null));
        assertFalse(setE.equals(5));
    }

//    @Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }
}
