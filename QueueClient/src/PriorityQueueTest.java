import ADT.PriorityQueue;
import arraybased.HeapPriorityQueue;
import listbased.SortedPriorityQueue;
import listbased.UnsortedPriorityQueue;

public class PriorityQueueTest
{
    public static void main(String[] args)
    {
        //UnsortedPriorityQueue<Integer, String> queue = new UnsortedPriorityQueue<>();
        //SortedPriorityQueue<Integer, String> queue = new SortedPriorityQueue<>();
        HeapPriorityQueue <Integer, String> heap = new HeapPriorityQueue<>();
//        queue.insert(20, "B");
//        queue.insert(50, "E");
//        queue.insert(30, "C");
//        queue.insert(10, "A");
//        queue.insert(40, "D");

        heap.insert(20, "B");
        heap.insert(50, "E");
        heap.insert(30, "C");
        heap.insert(10, "A");
        heap.insert(40, "D");

//        while(!queue.isEmpty())
//            System.out.println(queue.removeMin().getValue());

        //queue.show();
        heap.show();
    }

}
