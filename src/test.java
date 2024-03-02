package src;


public class test {

    public static void main(String[] args) {
       testInsert();
       testDeleteMin();
       testFindMin();
       testDeleteMin2();
       testDecreaseKey();
       testDelete();
       testMeld();
       testSize();
       testEmpty();
       testNumTrees();
       deleteEdgeCaseSameKey();
    }

    public static void testInsert() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        System.out.println("Insertion test passed.");
        if (heap.size == 3) {
        	System.out.println("Insertion size passed.");
        }
        else {
        	System.out.println("Insertion size faild.");
        }
        if (heap.last.item.key == 5) {
        	System.out.println("Insertion last passed.");
        }
        else {
        	System.out.println("Insertion last faild.");
        }
        if (heap.last.next.item.key == 20) {
        	System.out.println("Insertion last.next passed.");
        }
        else {
        	System.out.println("Insertion last.next faild.");
        }
        if (heap.last.child.item.key == 10) {
        	System.out.println("Insertion last.child passed.");
        }
        else {
        	System.out.println("Insertion last.child faild.");
        }
    }

    public static void testDeleteMin() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        heap.deleteMin();
        
        if (heap.min.item.key == 10) {
        	System.out.println("DeleteMin min passed.");
        }
        else {
        	System.out.println("DeleteMin min faild.");
        }
        
        if (heap.last.item.key == 10) {
        	System.out.println("DeleteMin last passed.");
        }
        else {
        	System.out.println("DeleteMin last faild.");
        }
        
        if (heap.last.parent.rank == -1) {
        	System.out.println("DeleteMin last.parent passed.");
        }
        else {
        	System.out.println("DeleteMin last.parent faild.");
        }
        
        if (heap.last.child.item.key == 20) {
        	System.out.println("DeleteMin last.child passed.");
        }
        else {
        	System.out.println("DeleteMin last.child faild.");
        }

        System.out.println("DeleteMin test passed.");
    }
    
    public static void testDeleteMin2() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");
        heap.insert(1, "Info 1");
        heap.insert(7, "Info 7");
        heap.insert(4, "Info 4");
        heap.insert(42, "Info 42");
        heap.insert(3, "Info 3");
        heap.insert(800, "Info 800");
        heap.insert(18, "Info 18");
        heap.insert(100, "Info 100");
        heap.printHeap();
        
        
        if (heap.min.next.item.key == 100) {
        	System.out.println("insert min.next.item passed.");
        }
        else {
        	System.out.println("insert min faild.");
        }

        heap.deleteMin();
        heap.printHeap();
        if (heap.min.item.key == 3) {
        	System.out.println("DeleteMin2 min passed.");
        }
        else {
        	System.out.println("DeleteMin2 min faild.");
        }
        
        if (heap.last.item.key == 3) {
        	System.out.println("DeleteMin2 last passed.");
        }
        else {
        	System.out.println("DeleteMin2 last faild.");
        }
        
        if (heap.last.next.item.key == 20) {
        	System.out.println("DeleteMin2 last.next passed.");
        }
        else {
        	System.out.println("DeleteMin2 last.next faild.");
        }
        
        if (heap.last.child.item.key == 5) {
        	System.out.println("DeleteMin2 last.child passed.");
        }
        else {
        	System.out.println("DeleteMin2 last.child faild.");
        }
        
        if (heap.last.child.child.item.key == 18) {
        	System.out.println("DeleteMin2 last.child.child passed.");
        }
        else {
        	System.out.println("DeleteMin2 last.child.child faild.");
        }
        
        if (heap.last.next.child.item.key == 100) {
        	System.out.println("DeleteMin2 last.next.child passed.");
        }
        else {
        	System.out.println("DeleteMin2 last.next.child faild.");
        }
        
        if (heap.size == 10) {
        	System.out.println("DeleteMin2 size passed.");
        }
        else {
        	System.out.println("DeleteMin2 size faild.");
        }
        
        if (heap.last.rank == 3) {
        	System.out.println("DeleteMin2 last.rank passed.");
        }
        else {
        	System.out.println("DeleteMin2 last.rank faild.");
        }

        System.out.println("DeleteMin2 test passed.");
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

        heap.decreaseKey(item, 7);
        
        if (heap.min.item.key == 3) {
        	System.out.println("testDecreaseKey min.item.key passed.");
        }
        else {
        	System.out.println("testDecreaseKey min.item.key faild.");
        }
        
        if (heap.last.item.key == 3) {
        	System.out.println("testDecreaseKey last.item.key passed.");
        }
        else {
        	System.out.println("testDecreaseKey last.item.key faild.");
        }
        
        if (heap.last.child.item.key == 5) {
        	System.out.println("testDecreaseKey last.child.item.key passed.");
        }
        else {
        	System.out.println("testDecreaseKey last.child.item.key faild.");
        }


        System.out.println("DecreaseKey test passed.");
    }

    public static void testDelete() {
        BinomialHeap heap = new BinomialHeap();
        BinomialHeap.HeapItem item = heap.insert(10, "Info 10");
        heap.insert(5, "Info 5");
        heap.insert(20, "Info 20");

        heap.delete(item);
        
        if (heap.last.child.item.key == 20) {
        	System.out.println("testDelete last.child passed.");
        }
        else {
        	System.out.println("testDelete last.child faild.");
        }

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
        
        if (heap1.size == 4) {
        	System.out.println("Insertion size passed.");
        }
        else {
        	System.out.println("Insertion size faild.");
        }
        if (heap1.min.item.key == 5) {
        	System.out.println("Insertion last passed.");
        }
        else {
        	System.out.println("Insertion last faild.");
        }
        if (heap1.last.item.key == 5) {
        	System.out.println("Insertion last.next passed.");
        }
        else {
        	System.out.println("Insertion last.next faild.");
        }
        if (heap1.last.child.item.key == 15) {
        	System.out.println("Insertion last.child passed.");
        }
        else {
        	System.out.println("Insertion last.child faild.");
        }
        if (heap1.last.child.next.item.key == 10) {
        	System.out.println("Insertion last.child.next passed.");
        }
        else {
        	System.out.println("Insertion last.child.next faild.");
        }
        if (heap1.last.child.child.next.item.key == 20) {
        	System.out.println("Insertion last.child.child.next passed.");
        }
        else {
        	System.out.println("Insertion last.child.child.next faild.");
        }
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
        heap.insert(1, "Info 1");
        heap.insert(42, "Info 42");

        System.out.println("Number of trees in heap: " + heap.numTrees());
        System.out.println("Number of trees in heap: " + (heap.numTrees() == 2));
        System.out.println("NumTrees test passed.");
    }
    
    public static void deleteEdgeCaseSameKey() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(1, "1");
        heap.insert(1, "34");
        heap.insert(20, "Info 20");
        heap.insert(42, "Info 42");
        
        heap.deleteMin();
        
        heap.printHeap();
        
        if (heap.min.item.key == 1) {
        	System.out.println("deleteEdgeCaseSameKey heap.min.item.key passed.");
        }
        else {
        	System.out.println("deleteEdgeCaseSameKey heap.min.item.key faild.");
        }
        if (heap.last.item.key == 20) {
        	System.out.println("deleteEdgeCaseSameKey heap.last.item.key passed.");
        }
        else {
        	System.out.println("deleteEdgeCaseSameKey heap.last.item.key faild.");
        }
        if (heap.last.next.item.key == 1) {
        	System.out.println("deleteEdgeCaseSameKey heap.last.next.item.key passed.");
        }
        else {
        	System.out.println("deleteEdgeCaseSameKey heap.last.next.item.key faild.");
        }
    }
}
