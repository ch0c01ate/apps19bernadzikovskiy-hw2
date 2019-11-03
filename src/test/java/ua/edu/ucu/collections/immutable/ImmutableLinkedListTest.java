package ua.edu.ucu.collections.immutable;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableLinkedList emptyList;
    private ImmutableLinkedList firstList;
    private ImmutableLinkedList secondList;
    private ImmutableList add;


    @Before
    public void setUp() throws Exception {
        firstList = new ImmutableLinkedList(new Object[]{1, 2, 3});
        emptyList = new ImmutableLinkedList(new Object[0]);
        secondList = new ImmutableLinkedList(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    public void testImmutableLinkedListAdd() {
        ImmutableList actual = firstList.add(1);
        Object[] expected = new Object[]{1, 2, 3, 1};
        System.out.println(Arrays.toString(firstList.toArray()));
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(firstList.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testImmutableLinkedListAddIndex() {
        ImmutableList actual = firstList.add(2, 5);
        Object[] expected = new Object[]{1, 2, 5, 3};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(firstList.toArray(), new Object[]{1, 2, 3});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListAddIndexOutOfRange() {
        ImmutableList actual = firstList.add(10, 5);
    }

    @Test
    public void testImmutableLinkedListAddAll() {
        ImmutableList actual = firstList.addAll(new Object[]{10, 11});
        System.out.println(Arrays.toString(actual.toArray()));
        Object[] expected = new Object[]{1, 2, 3, 10, 11};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(firstList.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testImmutableLinkedListAddAllIndex() {
        ImmutableList actual = firstList.addAll(1, new Object[]{11, 12});
        Object[] expected = new Object[]{1, 11, 12, 2, 3};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(firstList.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testImmutableLinkedListAddAllEmpty() {
        ImmutableList actual = emptyList.addAll(new Object[]{10, 11, 12});
        Object[] expected = new Object[]{10, 11, 12};
        assertArrayEquals(actual.toArray(), expected);
    }

    @Test
    public void testImmutableLinkedListAddAllEmptyArray() {
        ImmutableList actual = firstList.addAll(new Object[]{});
        Object[] expected = new Object[]{1, 2, 3};
        assertArrayEquals(actual.toArray(), expected);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListAddAllIndexOutOfRange() {
        firstList.addAll(10, new Object[]{10, 11, 12});
    }

    @Test
    public void testImmutableLinkedListGet() {
        assertEquals(secondList.get(3), 4);
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListGetError() {
        secondList.get(10);
    }

    @Test
    public void testImmutableLinkedListRemove() {
        ImmutableList actual = secondList.remove(2);
        assertArrayEquals(actual.toArray(), new Object[]{1, 2, 4, 5, 6, 7, 8, 9, 10});
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListRemoveSmallIndex() {
        secondList.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListRemoveLargeIndex() {
        secondList.remove(100);
    }

    @Test
    public void testImmutableLinkedListIndexOfExists() {
        int result = secondList.indexOf(2);
        assertEquals(result, 1);
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    public void testImmutableLinkedListIndexOfNotExist() {
        int result = secondList.indexOf(100);
        assertEquals(result, -1);
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    public void testImmutableLinkedListSize() {
        ImmutableLinkedList testList = secondList.remove(4);
        testList = testList.addAll(new Object[]{1, 2, 3});
        testList = testList.removeLast();
        assertEquals(testList.size(), 11);
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    public void testImmutableLinkedListSizeEmpty() {
        assertEquals(emptyList.size(), 0);
    }

    @Test
    public void testImmutableLinkedListClear() {
        ImmutableList result = firstList.clear();
        assertArrayEquals(result.toArray(), new Object[0]);
        assertArrayEquals(firstList.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testImmutableLinkedListClearEmpty() {
        ImmutableList result = emptyList.clear();
        assertArrayEquals(result.toArray(), new Object[0]);
    }

    @Test
    public void testEmptyImmutableLinkedIsEmpty() {
        assertFalse(secondList.isEmpty());
    }

    @Test
    public void testNotEmptyImmutableLinkedIsEmpty() {
        assertTrue(emptyList.isEmpty());
    }


    @Test
    public void testImmutableLinkedListAddFirst() {
        ImmutableLinkedList actual = firstList.addFirst(1);
        Object[] expected = new Object[]{1, 1, 2, 3};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(firstList.toArray(), new Object[]{1, 2, 3});
        assertEquals(firstList.size(), 3);
        assertEquals(actual.size(), 4);
    }

    @Test
    public void testImmutableLinkedListAddFirstEmpty() {
        ImmutableLinkedList actual = emptyList.addFirst(1);
        Object[] expected = new Object[]{1};
        assertArrayEquals(actual.toArray(), expected);
        assertEquals(actual.size(), 1);
    }

    @Test
    public void testImmutableLinkedListAddLastEmpty() {
        ImmutableLinkedList actual = emptyList.addLast(1);
        Object[] expected = new Object[]{1};
        assertArrayEquals(actual.toArray(), expected);
        assertEquals(actual.size(), 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListGetFirstEmpty() {
        emptyList.getFirst();
    }

    @Test
    public void testImmutableLinkedListAddLast() {
        ImmutableLinkedList actual = firstList.addLast(1);
        Object[] expected = new Object[]{1, 2, 3, 1};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(firstList.toArray(), new Object[]{1, 2, 3});
        assertEquals(firstList.size(), 3);
        assertEquals(actual.size(), 4);
    }

    @Test
    public void testImmutableLinkedListRemoveFirst() {
        ImmutableLinkedList actual = secondList.removeFirst();
        assertArrayEquals(actual.toArray(), new Object[]{2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(secondList.size(), 10);
        assertEquals(actual.size(), 9);
    }

    @Test
    public void testImmutableLinkedListRemoveLast() {
        ImmutableLinkedList actual = secondList.removeLast();
        assertArrayEquals(actual.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(secondList.size(), 10);
        assertEquals(actual.size(), 9);
    }


    @Test
    public void testImmutableLinkedListGetFirst() {
        assertEquals(secondList.getFirst(), 1);
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    public void testImmutableLinkedListGetLast() {
        assertEquals(secondList.getLast(), 10);
        assertArrayEquals(secondList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListGetLastError() {
        emptyList.getLast();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListRemoveLastError() {
        emptyList.removeLast();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListGetFirstError() {
        emptyList.getFirst();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testImmutableLinkedListRemoveFirstError() {
        emptyList.removeFirst();
    }
}