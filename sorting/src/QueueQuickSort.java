import ADT.Queue;
import Exceptions.QueueEmptyException;
import Exceptions.QueueFullException;
import listbased.LinkedQueue;

import java.util.Comparator;

public class QueueQuickSort
{
    // Quick-sort contents of a queue.
    public static <K> void quickSort(Queue<K> S, Comparator<K> comp) throws QueueEmptyException, QueueFullException
    {
        int n = S.size();
        if (n < 2) return; // queue is trivially sorted
        // divide
        K pivot = S.first(); // using ﬁrst as arbitrary pivot
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        while (!S.isEmpty())
        { // divide original into L, E, and G
            K element = S.dequeue();
            int c = comp.compare(element, pivot);
            if (c < 0) // element is less than pivot
                L.enqueue(element);
            else if (c == 0) // element is equal to pivot
                E.enqueue(element);
            else // element is greater than pivot
            G.enqueue(element);
        }
        // conquer
        quickSort(L, comp); // sort elements less than pivot
        quickSort(G, comp); // sort elements greater than pivot
        // concatenate results
        while (!L.isEmpty())
            S.enqueue(L.dequeue());
        while (!E.isEmpty())
            S.enqueue(E.dequeue());
        while (!G.isEmpty())
            S.enqueue(G.dequeue());
    }
}
