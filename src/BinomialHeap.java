package src;

/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers.
 * Based on exercise from previous semester.
 */
public class BinomialHeap {
	public int size;
	public HeapNode last;
	public HeapNode min;

	public BinomialHeap() {
		this.size = 0;
	}


	/**
	 * Inserts a new HeapItem into the heap with the given key and information.
	 *
	 * @param key  The key associated with the new HeapItem (must be greater than 0).
	 * @param info Additional information to be stored in the new HeapItem.
	 * @return The newly generated HeapItem.
	 */
	public HeapItem insert(int key, String info) {
		// Create a new HeapNode with the provided key and info
		HeapNode nodeInsert = new HeapNode(key, info);

		// Create a new BinomialHeap instance to hold the new node
		BinomialHeap heap2 = new BinomialHeap();

		// Set the last and min pointers of heap2 to the new node, and update its size
		heap2.last = nodeInsert;
		heap2.min = nodeInsert;
		heap2.size = 1;
		nodeInsert.next = nodeInsert;

		// Meld the new heap (heap2) with the current heap
		this.meld(heap2);

		// Return the newly generated HeapItem
		return nodeInsert.item;
	}


	/**
	 * Delete the minimal item
	 */
	public void deleteMin() {
		return; // should be replaced by student code

	}

	/**
	 * Return the minimal HeapItem
	 */
	public HeapItem findMin() {
		return this.min.item;
	}

	/**
	 * pre: 0 < diff < item.key
	 * <p>
	 * Decrease the key of item by diff and fix the heap.
	 */
	public void decreaseKey(HeapItem item, int diff) {
		return; // should be replaced by student code
	}

	/**
	 * Delete the item from the heap.
	 */
	public void delete(HeapItem item) {
		return; // should be replaced by student code
	}

	/**
	 * Melds the current heap with another heap.
	 * 
	 * @param heap2 The heap to be melded with the current heap.
	 */
	public void meld(BinomialHeap heap2) {
	    // If both heaps are empty, no merging is needed
	    if (this.size == 0 && heap2.size == 0) {
	        return;
	    } else if (this.size == 0) { // If the current heap is empty, assign heap2 to the current heap
	        this.min = heap2.min;
	        this.last = heap2.last;
	        this.size = heap2.size;
	        return;
	    } else if (heap2.size == 0) { // If heap2 is empty, no merging is needed
	        return;
	    } else {
	        // Compare the minimum items of both heaps and update the minimum item of the current heap if needed
	        if (heap2.min.item.key < this.min.item.key) {
	            this.min = heap2.min;
	        }
	        // Update the size of the current heap by adding the size of heap2
	        this.size = this.size + heap2.size;
	        // Initialize pointers for iterating through the heaps
	        HeapNode counterHeapOne = this.last.next;
	        HeapNode counterHeapTwo = heap2.last.next;
	        // Flags to track the completion of iteration for each heap
	        boolean finishedOne = false;
	        boolean finishedTwo = false;
	        // Create a dummy node for merging
	        HeapNode point = new HeapNode(-1, "");
	        HeapNode setup = point;
	        HeapNode curr = new HeapNode();
	        // Iterate through the heaps until all nodes are merged
	     // Loop until we finish merging nodes from both heaps
	        while (!finishedOne || !finishedTwo) {
	            // Merge nodes with the same rank from both heaps
	            if (counterHeapOne.rank == counterHeapTwo.rank && !finishedOne && !finishedTwo) {
	                // Check if the current result node already has the same rank
	                if (curr.rank == counterHeapOne.rank) {
	                    // Append the current result node to the linked list and move to the next node
	                    setup.next = curr;
	                    setup = setup.next;
	                    curr = new HeapNode(); // Reset the current result node
	                }
	                // Link the nodes with the same rank and update the pointers
	                curr = this.link(counterHeapOne, counterHeapTwo);
	                counterHeapOne = counterHeapOne.next; // Move to the next node in the first heap
	                if (counterHeapOne.rank == this.last.next.rank) {
	                    finishedOne = true; // Mark the first heap as finished if reached the last node
	                }
	                counterHeapTwo = counterHeapTwo.next; // Move to the next node in the second heap
	                if (counterHeapTwo.rank == heap2.last.next.rank) {
	                    finishedTwo = true; // Mark the second heap as finished if reached the last node
	                }
	            } else if ((counterHeapOne.rank < counterHeapTwo.rank && !finishedOne && !finishedTwo) || (finishedTwo)) {
	                // Merge nodes when the rank of the node from the first heap is lower
	                if (curr.rank == counterHeapOne.rank) {
	                    // Link the nodes with the same rank if the current result node already has the same rank
	                    curr = this.link(counterHeapOne, curr);
	                } else {
	                    // Append the node from the first heap to the linked list
	                    if (curr.rank != -1) {
	                        setup.next = curr;
	                        setup = setup.next;
	                        curr = new HeapNode(); // Reset the current result node
	                    }
	                    setup.next = counterHeapOne;
	                    setup = setup.next;
	                }
	                counterHeapOne = counterHeapOne.next; // Move to the next node in the first heap
	                if (counterHeapOne.rank == this.last.next.rank) {
	                    finishedOne = true; // Mark the first heap as finished if reached the last node
	                }
	            } else {
	                // Merge nodes when the rank of the node from the second heap is lower
	                if (curr.rank == counterHeapTwo.rank) {
	                    // Link the nodes with the same rank if the current result node already has the same rank
	                    curr = this.link(counterHeapTwo, curr);
	                } else {
	                    // Append the node from the second heap to the linked list
	                    if (curr.rank != -1) {
	                        setup.next = curr;
	                        setup = setup.next;
	                        curr = new HeapNode(); // Reset the current result node
	                    }
	                    setup.next = counterHeapTwo;
	                    setup = setup.next;
	                }
	                counterHeapTwo = counterHeapTwo.next; // Move to the next node in the second heap
	                if (counterHeapTwo.rank == heap2.last.next.rank) {
	                    finishedTwo = true; // Mark the second heap as finished if reached the last node
	                }
	            }
	        }

	        // Check if there is any node left in the merging process and update the pointers accordingly
	        if (curr.rank != -1) {
	            setup.next = curr;
	            setup = setup.next;
	        }
	        setup.next = point.next;
	        point.next = null;
	        this.last = setup;
	    }
	}

	/**
	 * Links two heap nodes together and returns the parent node.
	 * 
	 * @param x The first heap node.
	 * @param y The second heap node.
	 * @return The parent node after linking.
	 */
	public HeapNode link(HeapNode x, HeapNode y) {
	    // Ensure x has smaller key than y
	    if (x.item.key > y.item.key) {
	        return link(y, x);
	    }
	    // Link y as a child of x
	    if (x.child == null) {
	        y.next = y;
	    } else {
	        y.next = x.child.next;
	        x.child.next = y;
	    }
	    // Update parent, child, and rank
	    y.parent = x;
	    x.child = y;
	    x.rank += 1;
	    return x;
	}

		
	/**
	 * 
	 * Return the number of elements in the heap
	 *   
	 */
	public int size() {
		return this.size; // should be replaced by student code
	}

	/**
	 * 
	 * The method returns true if and only if the heap
	 * is empty.
	 *   
	 */
	public boolean empty() {
		return this.size == 0; // should be replaced by student code
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees() {
		if (size == 0) {
			return 0;
		}
		int counter = 1;
		HeapNode stop_node = this.last.next;
		HeapNode node = this.last.next.next;
		while (node != stop_node) {
			counter += 1;
			node = node.next;
		}
		return counter;
	}



	/**
	 * Class implementing a node in a Binomial Heap.
	 *  
	 */
	public class HeapNode{
		public HeapItem item;
		public HeapNode child;
		public HeapNode next;
		public HeapNode parent;
		public int rank;
		
		public HeapNode(int key, String info) {
			this.item = new HeapItem(key, info, this);
			this.rank = 0;
			this.next = this;
		}
		public HeapNode() {
			this.rank = -1;
		}

	}

	/**
	 * Class implementing an item in a Binomial Heap.
	 *  
	 */
	public class HeapItem{
		public HeapNode node;
		public int key;
		public String info;
		
		/**
		 * Constructor for creating a HeapItem object with the provided key, info, and node.
		 * 
		 * @param key  The key associated with the HeapItem.
		 * @param info A string containing additional information related to the HeapItem.
		 * @param node The HeapNode associated with the HeapItem.
		 */
		public HeapItem(int key, String info, HeapNode node){
			this.key = key;
			this.info = info;
			this.node = node;
		}

//		public int getKey() {
//			return this.key;
//		}
//
//		public String getInfo() {
//			return this.info;
//		}
//
//		public HeapNode getNode() {
//			return this.node;
//		}
//
//		public void setKey(int key) {
//			this.key = key;
//		}
//
//		public void setInfo(String info) {
//			this.info = info;
//		}
//
//		public void getNode(HeapNode node) {
//			this.node = node;
//		}
	}

}

