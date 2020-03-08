/**
 * This class is a simple stack created using an array. It has data fields for
 * the size of the stack, the location of the top of the stack, as well as the array of data.
 *
 * @author James Dell'Alba
 * @version 2/17/2020
 */
public class Stack {
	private int size;
	private int top;
	private Country[] stackArray;

	/**
	 * Constructor for creating a Stack object
	 * @param size the maximum number of elements the stack array can hold
	 */
	public Stack(int size) {
		this.size = size;
		this.stackArray = new Country[size];
		this.top = -1;
	}

	/**
	 * Pushes a country to the top of the stack
	 * @param country the country being pushed
	 */
	public void push(Country country) {
		if(country == null) {
			//do nothing
		} else if (!this.isFull()) {
			top++;
			stackArray[top] = country;
		} else {
			System.out.println("The stack is already full");
		}
	}

	/**
	 * removes a country from the top of the stack and returns it
	 * @return the country removed from the top of the stack, unless the stack is empty
	 */
	public Country pop() {
		if (!this.isEmpty()) {
			top--;
			return stackArray[top + 1];
		} else {
			System.out.println("the stack is already empty");
			return null;
		}
	}

	/**
	 * Prints the contents of the stack from top to bottom
	 */
	public void printStack() {
		for (int i = top; i > -1; i--) {
			stackArray[i].printCountries();
		}
	}

	/**
	 * Checks to see if the stack is empty.
	 * @return true if the stack is empty, false if not.
	 */
	public boolean isEmpty() {
		if (this.top == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks to see if the stack is full.
	 * @return true if the stack is full, false if not
	 */
	public boolean isFull() {
		if (this.top == size - 1) {
			return true;
		} else {
			return false;
		}
	}
}
