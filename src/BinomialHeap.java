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
	 * Meld the heap with heap2
	 * TODO - initate while loop (for shorter heap)
	 * TODO - CRY
	 * TODO - to handle the rest of the longer heap
	 * TODO - to update the main heap
	 */
	public void meld(BinomialHeap heap2) {
		this.min = Math.min(this.min.item.key, heap2.min.item.key);
		this.size = this.size + heap2.size;
		HeapNode counterHeapOne = this.last.next;
		HeapNode counterHeapTwo = heap2.last.next;
		Boolean finishedOne = false;
		Boolean fibishedTwo = false;
		HeapNode point = new HeapNode(-1, "");
		HeapNode setup = point;
		HeapNode curr = null;
		while (!finishedOne && !fibishedTwo) {
			if (counterHeapOne.rank == counterHeapTwo.rank && !finishedOne && !fibishedTwo) {
				if (curr.rank == counterHeapOne.rank) {
					setup.next = curr;
					setup = setup.next;
					curr = null;
				}
				curr = this.link(counterHeapOne, counterHeapTwo);
				counterHeapOne = counterHeapOne.next;
				counterHeapTwo = counterHeapTwo.next;
			} else if ((counterHeapOne.rank < counterHeapTwo.rank && !finishedOne && !fibishedTwo) || (finishedTwo)) {
				if (curr.rank == counterHeapOne.rank) {
					curr = this.link(counterHeapOne, curr);
				} else {
					if (curr != null) {
						setup.next = curr;
						setup = setup.next;
						curr = null;
					}
					setup.next = counterHeapOne;
					setup = setup.next;
				}
				counterHeapOne = counterHeapOne.next;
			} else {
				if (curr.rank == counterHeapTwo.rank) {
					curr = this.link(counterHeapTwo, curr);
				} else {
					if (curr != null) {
						setup.next = curr;
						setup = setup.next;
						curr = null;
					}
					setup.next = counterHeapTwo;
					setup = setup.next;
				}
				counterHeapTwo = counterHeapTwo.next;

			}
			if (counterHeapOne.rank == this.last.next.rank) {
				finishedOne = true;
			} else if (counterHeapTwo.rank == heap2.last.next.rank) {
				fibishedTwo = true;
			}

		}
		if (curr != null) {
			setup.next = curr;
			setup = setup.next;
		}
		setup.next = point.next;
		point.next = null;
		this.last = setup;

	}


			

		

	
	public HeapNode link(HeapNode x, HeapNode y) {
		if (x.item.key > y.item.key) {
			return link(y, x);
		}
		if (x.child == null) {
			y.next = y;
		}
		else {
			y.next = x.child.next;
			y.parent = x;
			x.child.next = y;
		}
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
		return 0; // should be replaced by student code
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

