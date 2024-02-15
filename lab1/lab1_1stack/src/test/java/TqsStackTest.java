
import org.example.TqsStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TqsStackTest {
    private TqsStack<Integer> subject;

    @BeforeEach
    void setup() {
        subject = new TqsStack<Integer>();
    }

    @Test
    void emptyOnConstruction() {
        assertEquals(0, subject.size());
    }

    @Test
    void sizeZeroOnConstruction() {
        assertEquals(true, subject.isEmpty());
    }

    @Test
    void pushing() {
        int N = 5;
        for (int i = 0; i < N; i++) {
            subject.push(i);
        }

        assertEquals(false, subject.isEmpty());
        assertEquals(N, subject.size());
    }

    @Test
    void pushPop() {
        int val = 10;
        subject.push(val);
        assertEquals(val, subject.pop());
    }

    @Test
    void pushPeek() {
        int val = 10;
        subject.push(val);
        assertEquals(val, subject.peek());
        assertEquals(1, subject.size());
    }

    @Test
    void popTillEmpty() {
        int N = 10;
        for (int i = 0; i < N; i++) {
            subject.push(i);
        }
        for (int i = 0; i < N; i++) {
            subject.pop();
        }
        assertEquals(true, subject.isEmpty());
        assertEquals(0, subject.size());
    }

    @Test
    void popEmpty() {
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            subject.pop();
        });
    }

    @Test
    void peekEmpty() {
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            subject.peek();
        });
    }
}
