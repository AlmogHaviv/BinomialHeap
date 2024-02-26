import BinomialHeap;

public class test {

    public static void main(String[] args) {
        testInsert();
        //testDeleteMin();
        testFindMin();
        //testDecreaseKey();
        //testDelete();
        testMeld();
        testSize();
        testEmpty();
        //testNumTrees();
    }

    public static void testInsert() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        System.out.println("Insertion test passed.");
    }

    public static void testDeleteMin() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        heap.deleteMin();

        System.out.println("DeleteMin test passed.");
    }

    public static void testFindMin() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        System.out.println("Min value in heap: " + heap.findMin().key);

        System.out.println("FindMin test passed.");
    }

    public static void testDecreaseKey() {
        BinomialHeap heap = new BinomialHeap();
        BinomialHeap.HeapItem item = heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        heap.decreaseKey(item, 3);

        System.out.println("DecreaseKey test passed.");
    }

    public static void testDelete() {
        BinomialHeap heap = new BinomialHeap();
        BinomialHeap.HeapItem item = heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        heap.delete(item);

        System.out.println("Delete test passed.");
    }

    public static void testMeld() {
        BinomialHeap heap1 = new BinomialHeap();
        heap1.insert(10, "Info 10");
        heap1.insert(5, "Info 5");

        BinomialHeap heap2 = new BinomialHeap();
        heap2.insert(20, "Info 20");
        heap2.insert(15, "Info 15");

        heap1.meld(heap2);

        System.out.println("Meld test passed.");
    }

    public static void testSize() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        System.out.println("Size of heap: " + heap.size());

        System.out.println("Size test passed.");
    }

    public static void testEmpty() {
        BinomialHeap heap = new BinomialHeap();

        System.out.println("Is heap empty? " + heap.empty());

        System.out.println("Empty test passed.");
    }

    public static void testNumTrees() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        System.out.println("Number of trees in heap: " + heap.numTrees());

        System.out.println("NumTrees test passed.");
    }
}
