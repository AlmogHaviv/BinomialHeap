//username1 - tal9
//id1      - 322539651
//name1    - Tal Cohen
//username2 - almoghaviv
//id2      - 207298720
//name2    - Almog Haviv

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

	public BinomialHeap() { // Constructor for creating a new Binomial Heap, O(1) time complexity
		this.size = 0; // Initialize the size of the heap to 0
	}

	/**
	 * Function for insert one new node
	 *
	 * @param key  The key associated with the new HeapItem (must be greater than 0).
	 * @param info Additional information to be stored in the new HeapItem.
	 *
	 */
	public BinomialHeap BinomialHeapForInsert(HeapNode node) { //O(1) time complexity
		BinomialHeap heap2 = new BinomialHeap();
		heap2.size = 1;
		heap2.last = node;
		heap2.min = node;
		node.next = node;
		return heap2;
	}


	/**
	 * Inserts a new HeapItem into the heap with the given key and information.
	 *
	 * @param key  The key associated with the new HeapItem (must be greater than 0).
	 * @param info Additional information to be stored in the new HeapItem.
	 * @return The newly generated HeapItem.
	 */
	public HeapItem insert(int key, String info) { //O(log(n)) time complexity
		
		// Create a new HeapNode with the provided key and info
		HeapNode nodeInsert = new HeapNode(key, info);
		
		// Creating a heap
		BinomialHeap heap2 = BinomialHeapForInsert(nodeInsert);

		// Meld the new heap (heap2) with the current heap
		this.meld(heap2);

		// Return the newly generated HeapItem
		return nodeInsert.item;
	}


	/**
	 * Delete the minimal item from the binomial heap.
	 * This method removes the node with the minimum key value from the heap.
	 */
	public void deleteMin() { //O(log(n)) time complexity
		// If the heap is empty, do nothing
		if (this.size == 0) {
			return;
		}
		// If the heap size is 1, then after delete min the heap is empty
		if (this.size == 1) {
			this.size = 0;
			this.last = new HeapNode();
			this.min = new HeapNode();
		}
		else if (this.min.child != null) {
			// Get the child of the current minimum node
		    HeapNode tmp = this.min.child;
		    // Disconnect the child from its parent
		    tmp.parent = new HeapNode();
		    // Find the new minimum node among the children of the deleted node
		    HeapNode minTmp = tmp;
		    HeapNode iter = tmp.next;
		    while (iter != tmp) {
		        iter.parent = new HeapNode(); // Disconnect the child from its parent
		        if (iter.item.key <= minTmp.item.key) {
		            minTmp = iter;
		        }
		        iter = iter.next;
		    }
		    // Create a new heap to hold the children of the deleted node
		    BinomialHeap heap2 = new BinomialHeap();
		    heap2.last = tmp;
		    heap2.min = minTmp;
		    
		    // Calculate the size of the new heap
		    int k = this.min.rank;
		    heap2.size = (int) Math.pow(2, k) - 1;
		    
		    // If the heap used to be one binomial tree, then heap 2 is the new heap
		    if (this.numTrees() == 1) {
		    	this.size = heap2.size;
		    	this.last = heap2.last;
		    	this.min = heap2.min;
		    }
		    else {
		    	// Remove the minimum node from the current heap
			    this.removeMin();
			    // Merge the two heaps together
			    this.meld(heap2);
		    }
		}
		else {
			this.removeMin();
		}
	    
	}

	/**
	 * Remove the current minimum node from the binomial heap.
	 * This method removes the minimum node from the heap after it has been identified.
	 * It updates the minimum node and adjusts the size of the heap accordingly.
	 * it does not return anything, it is doing it in-place.
	 */
	private void removeMin() { //O(log(n)) time complexity
	    // Get the current minimum node
	    HeapNode oldMin = this.min;
	    // Find the next node in the circular linked list
	    HeapNode nextPoint = oldMin.next;
	    // Initialize variables for traversal
	    HeapNode curr = nextPoint;
	    HeapNode newMin = nextPoint;
	    HeapNode prev = nextPoint;
	    // Traverse the linked list to find the new minimum node
	    while (curr != oldMin) {
	        if (curr.item.key <= newMin.item.key) {
	            newMin = curr;
	        }
	        if (curr.next == oldMin) {
	            prev = curr;
	        }
	        curr = curr.next;
	    }
	    
	    // Removing the old minimum node from heap
	    prev.next = nextPoint;
	    
	    // Update the last node if necessary (if it was the oldMIn)
	    if (this.last == oldMin) {
	        this.last = prev;
	    }
	    
	    // Disconnect the old minimum node
	    oldMin.next = null;
	    // Update the minimum node and heap size
	    this.min = newMin;
	    this.size -= (int) Math.pow(2, oldMin.rank);
	}

	
	/**
	 * Return the minimal HeapItem
	 */
	public HeapItem findMin() { //O(1) time complexity
		if (this.size == 0){
			return null;
		}
		return this.min.item;
	}

	
	/**
	 * Decreases the key of the given HeapItem by the specified difference and adjusts the heap to maintain its properties.
	 * 
	 * @param item The HeapItem whose key is to be decreased.
	 * @param diff The amount by which to decrease the key (0 < diff < item.key).
	 */
	public void decreaseKey(HeapItem item, int diff) { //O(log(n)) time complexity
	    HeapNode node = item.node; // Get the node associated with the HeapItem
	    item.key -= diff; // Decrease the key by the specified difference

	    // Fix the heap property by swapping the node with its parent if necessary
	    while (node.parent != null && node.item.key <= node.parent.item.key) {
	        // Swap the HeapItems of the node and its parent
	        HeapItem tmp = node.parent.item;
	        node.parent.item = node.item;
	        node.item = tmp;
	        node = node.parent; // Move up the heap to the parent
	    }
	    
	    // Update the minimum node if necessary
	    if (node.item.key <= this.min.item.key) {
	        this.min = node;
	    }
	}


	/**
	 * Deletes the specified item from the heap.
	 * 
	 * @param item The item to be deleted from the heap.
	 */
	public void delete(HeapItem item) { //O(log(n)) time complexity
	    int n = item.key + 1;
	    // Decrease the key of the item by n
	    this.decreaseKey(item, n);
	    // Delete the minimum item from the heap
	    this.deleteMin();
	}


	/**
	 * Melds the current heap with another heap.
	 * 
	 * @param heap2 The heap to be melded with the current heap.
	 */
	public void meld(BinomialHeap heap2) { //O(log(n)) time complexity
	    // If both heaps are empty, no merging is needed
	    if (this.empty() && heap2.empty()) {
	        return;
	    }
	    // If the current heap is empty, assign heap2 to the current heap
	    else if (this.empty()) { 
	        this.min = heap2.min;
	        this.last = heap2.last;
	        this.size = heap2.size;
	        return;
	    }
	    // If heap2 is empty, no merging is needed
	    else if (heap2.empty()) { 
	        return;
	    } 
	    else {
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
	        int n = heap2.numTrees();
	        int m = this.numTrees();
	        
	        // Create a dummy node for merging
	        HeapNode point = new HeapNode(-1, "");
	        HeapNode setup = point;
	        HeapNode curr = new HeapNode();
	        
	        // Iterate through the heaps until all nodes are merged
	        while ( n != 0 || m != 0) {
	        	// Holding the next tree in both heaps
	        	HeapNode point1 = counterHeapOne.next;
	        	HeapNode point2 = counterHeapTwo.next;
	            // Merge nodes with the same rank from both heaps
	            if (counterHeapOne.rank == counterHeapTwo.rank && n != 0 && m != 0) {
	                // Check if the current result node already has the same rank
	                if (curr.rank == counterHeapOne.rank) {
	                    // Append the current result node to the linked list and move to the next node
	                    setup.next = curr;
	                    setup = setup.next;
	                    curr = new HeapNode(); // Reset the current result node
	                }
	                // Link the nodes with the same rank and update the pointers and flags
	                curr = this.link(counterHeapOne, counterHeapTwo);
	                counterHeapOne = point1; // Move to the next node in the first heap
	                m -= 1;
	                counterHeapTwo = point2; // Move to the next node in the second heap
	                n -= 1;
	            } 
	            else if ((counterHeapOne.rank < counterHeapTwo.rank && n != 0 && m != 0) || (n == 0)) {
	                // Merge nodes when the rank of the node from the first heap is lower, and we haven't finished the first heap
	                if (curr.rank == counterHeapOne.rank) {
	                    // Merge the nodes with the same rank if the current result node already has the same rank
	                    curr = this.link(counterHeapOne, curr);
	                } 
	                else {
	                    // Append the node from the first heap to the linked list
	                    if (curr.rank != -1) {
	                        setup.next = curr;
	                        setup = setup.next;
	                        curr = new HeapNode(); // Reset the current result node
	                    }
	                    setup.next = counterHeapOne;
	                    setup = setup.next;
	                }
	                counterHeapOne = point1; // Move to the next node in the first heap
	                m -= 1;
	            } 
	            else {
	                // Merge nodes when the rank of the node from the second heap is lower
	                if (curr.rank == counterHeapTwo.rank) {
	                    // Link the nodes with the same rank if the current result node already has the same rank
	                    curr = this.link(counterHeapTwo, curr);
	                } 
	                else {
	                    // Append the node from the second heap to the linked list
	                    if (curr.rank != -1) {
	                        setup.next = curr;
	                        setup = setup.next;
	                        curr = new HeapNode(); // Reset the current result node
	                    }
	                    setup.next = counterHeapTwo;
	                    setup = setup.next;
	                }
	                counterHeapTwo = point2; // Move to the next node in the second heap
	                n -= 1;
	            }
	        }

	        // Check if there is any carry node left in the merging process and update the pointers accordingly
	        if (curr.rank != -1) {
	            setup.next = curr;
	            setup = setup.next;
	        }
	        // Removing the dummy node 
	        setup.next = point.next;
	        point.next = null;
	        this.last = setup;
	    }
	}

	
	/**
	 * Links two heap nodes together and returns the parent node.
	 * @param x The first heap node.
	 * @param y The second heap node.
	 * @return The parent node after linking.
	 */
	public HeapNode link(HeapNode x, HeapNode y) { //O(1) time complexity
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
	public int size() { //O(1) time complexity
		return this.size;
	}

	/**
	 * 
	 * The method returns true if and only if the heap
	 * is empty.
	 *   
	 */
	public boolean empty() { //O(1) time complexity
		return this.size == 0;
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees() { //O(log(n)) time complexity
		// If the heap is empty, return 0 trees
		if (size == 0) {
			return 0;
		}
		// initializing counter for nuber of trees
		int counter = 1;

		// Store the stop node to mark the end of traversal
		HeapNode stop_node = this.last.next;

		// Start traversal from the second node
		HeapNode node = this.last.next.next;

		// Traverse through the nodes until reaching the stop node
		while (node != stop_node) {
			counter += 1; // Increment counter for each tree encountered
			node = node.next; // Move to the next node
		}
		// Return the total count of trees in the heap
		return counter;
	}

	/**
	 * Class implementing a node in a Binomial Heap.
	 *  
	 */
	public class HeapNode{ //O(1)
		public HeapItem item;
		public HeapNode child;
		public HeapNode next;
		public HeapNode parent;
		public int rank;
		
		
		/**
	     * Constructor to create a HeapNode with the provided key and info.
	     * 
	     * @param key  The key associated with the HeapItem.
	     * @param info A string containing additional information related to the HeapItem.
	     */
		public HeapNode(int key, String info) {
			this.item = new HeapItem(key, info, this);
			this.rank = 0;
			this.next = this; // Set the next pointer to itself, as it's a circular linked list by default
		}
		
		
		/**
	     * Default constructor for HeapNode.
	     * Initializes the rank to -1, indicating it's not part of any heap.
	     */
		public HeapNode() {
			this.rank = -1; // Initialize the rank to -1, indicating it's not part of any heap
		}
	}

	
	/**
	 * Class implementing an item in a Binomial Heap.
	 *  
	 */
	public class HeapItem{ //O(1)
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
		
		/**
	     * Default constructor for HeapItem.
	     * Initializes the key to -1, indicating an uninitialized state.
	     */
		public HeapItem(){
			this.key = -1; // Initialize the key to -1, indicating an uninitialized state
		}
	}

}

