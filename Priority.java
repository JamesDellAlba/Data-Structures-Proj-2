/**
 * This class is a priority queue created using an array. It has data fields for holding the maximum size
 * of the array, the rear of the queue, the front of the queue, the current number of elements in the queue,
 * and the array of countries that makes up the queue.
 *
 * @author James Dell'Alba
 * @version 2/17/2020
 */
public class Priority {
	private int maxSize;
	private int rear;
	private int front;
	private int currentSize;
	private Country[] queueArray;

	/**
	 * Constructor for creating a Priority object
	 * @param maxSize the largest number of items the queue can hold
	 */
	public Priority (int maxSize) {
		this.maxSize = maxSize;
		this.rear = -1;
		this.front = -1;
		this.currentSize = 0;
		this.queueArray = new Country[maxSize];
	}

	/**
	 * This method adds a country to any location inside the queue while maintaining the order of priority.
	 * In this case, countries with high GDP/Capita are the highest priority and stay at the front of the queue.
	 *
	 * The method is O(N) because in the worst case scenario the outer loop runs once while the inner loop runs N times.
	 *
	 * @param countryToInsert the country being inserted
	 */
	public void queueInsert(Country countryToInsert) {
		if (!this.isFull()) {
			for (int i = 0; i < currentSize; i++) { //outer loop
				if (queueArray[i].getGdpPerCapita() < countryToInsert.getGdpPerCapita()) {
					for (int j = this.rear; j >= i; j--) { //inner loop
						queueArray[j + 1] = queueArray[j]; //shift everything to the left
					}
					queueArray[i] = countryToInsert; //insert the item
					rear = ((rear + 1) % queueArray.length);
					currentSize++;
					return; //break out of the method
				}
			}
			if (this.isEmpty()) {
				queueArray[0] = countryToInsert; //insert the item
				rear = ((rear + 1) % queueArray.length);
				currentSize++;
				this.front = 0;
				return; //break out of the method
			}
			queueArray[rear + 1] = countryToInsert; //insert the item
			rear = ((rear + 1) % queueArray.length);
			currentSize++;
		} else {
			System.out.println("The queue is already full");
		}
	}

	/**
	 * This method removes an item from the front of the queue and changes the value of 'front' accordingly.
	 * This method is O(1) because there are no loops and it takes constant time.
	 * @return the item being removed
	 */
	public Country queueRemove() {
		if (!isEmpty()) {
			int temp = front;
			front = ((front + 1) % queueArray.length);
			currentSize--;
			return queueArray[temp];
		} else {
			System.out.println("There are no elements in the queue");
			return null;
		}
	}

	/**
	 * This method prints the entire contents of the queue from front to back without
	 * removing the items.
	 */
	public void printQueue() {
		System.out.printf("%-33s %-10s %-20s %-15s %-16s %-10s %-10s\n", "Name", "Code", "Capitol", "Population", "GDP", "GDP/Cap", "Happiness");
		for (int j = 0; j < this.currentSize; j++) {
			queueArray[j].printCountries();
		}
	}

	/**
	 * Checks to see if the queue is empty.
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		if ((this.currentSize == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks to see if the queue is full.
	 * @return true if full, false if not
	 */
	public boolean isFull() {
		if (this.currentSize == this.maxSize) {
			return true;
		} else {
			return false;
		}
	}
}
