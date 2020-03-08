import java.util.Scanner;
import java.io.File;

/**
 * COP 3530: Project 2–Stacks and Priority Queues
 * <p>
 *     After the user inputs a filename, this program creates 5 priority queues adds countries to each according
 *     to their GDP per Capita.
 *     Countries with GDP/Capita < 1000 go in the poor queue. GDP/Capita < 5000 go in the fair queue.
 *     GDP/Capita < 20000 go in the good queue. GDP/Capita < 50000 go in the very good queue. Finally,
 *     all the rest go in the excellent queue.
 * </p>
 * <p>
 *     After printing the contents of each queue, all items are dequeued from each queue starting with Poor and
 *     pushed to the stack. Then, the contents of the stack are printed from top to bottom without removing the items.
 * </p>
 *
 * @author James Dell'Alba
 * @version 2/17/2020
 */
public class Project2 {
	public static void main(String[] args) throws java.io.FileNotFoundException{
		Scanner input = new Scanner(System.in);
		String fileName = "";
		do {
			System.out.println("enter the name of the file");
			fileName = input.next();
		} while (!fileName.equals("Countries2.csv"));

		File countries = new File(fileName);
		Scanner fileInput = new Scanner(countries);

		fileInput.useDelimiter(",|\n"); //set the delimiter to comma or newline

		String firstLine = fileInput.nextLine(); //move the cursor past the first line of the file

		//create an array of countries
		Country[] countryArray = new Country[156];
		for(int i = 0; i < 155; i++) {
			//fill the array with Country objects
			String name = fileInput.next();
			String code = fileInput.next();
			String capitol = fileInput.next();
			long population = Double.valueOf(fileInput.next()).longValue();
			long gdp = Double.valueOf(fileInput.next()).longValue();
			long happiness = Double.valueOf(fileInput.next()).longValue();
			countryArray[i] = new Country(name, code, capitol, population, gdp, happiness);
		}

		//create 5 queues and a stack
		Priority queuePoor = new Priority(156);
		Priority queueFair = new Priority(156);
		Priority queueGood = new Priority(156);
		Priority queueVeryGood = new Priority(156);
		Priority queueExcellent = new Priority(156);
		Stack theStack = new Stack(156);

		//fill each queue with the appropriate countries
		for (int i = 0; i < 155; i++) {
			if (countryArray[i].getGdpPerCapita() < 1000) {
				queuePoor.queueInsert(countryArray[i]);
			} else if (countryArray[i].getGdpPerCapita() < 5000) {
				queueFair.queueInsert(countryArray[i]);
			} else if (countryArray[i].getGdpPerCapita() < 20000) {
				queueGood.queueInsert(countryArray[i]);
			} else if (countryArray[i].getGdpPerCapita() < 50000) {
				queueVeryGood.queueInsert(countryArray[i]);
			} else {
				queueExcellent.queueInsert(countryArray[i]);
			}
		}

		//print the contents of each queue
		System.out.println("\nPoor queue contents: \n");
		queuePoor.printQueue();
		System.out.println("\nFair queue contents: \n");
		queueFair.printQueue();
		System.out.println("\nGood queue contents: \n");
		queueGood.printQueue();
		System.out.println("\nVery Good queue contents: \n");
		queueVeryGood.printQueue();
		System.out.println("\nExcellent queue contents: \n");
		queueExcellent.printQueue();

		//push values of poor queue to the stack
		while(!queuePoor.isEmpty() && !theStack.isFull()) {
			theStack.push(queuePoor.queueRemove());
		}
		//push values of fair queue to the stack
		while(!queueFair.isEmpty() && !theStack.isFull()) {
			theStack.push(queueFair.queueRemove());
		}
		//push values of good queue to the stack
		while(!queueGood.isEmpty() && !theStack.isFull()) {
			theStack.push(queueGood.queueRemove());
		}
		//push values of very good queue to the stack
		while(!queueVeryGood.isEmpty() && !theStack.isFull()) {
			theStack.push(queueVeryGood.queueRemove());
		}
		//push values of excellent queue to the stack
		while(!queueExcellent.isEmpty() && !theStack.isFull()) {
			theStack.push(queueExcellent.queueRemove());
		}
		//print the stack
		System.out.println("\nStack Contents:\n");
		theStack.printStack();
	}
}

