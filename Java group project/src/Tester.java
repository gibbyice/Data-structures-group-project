import java.util.Scanner;

public class Tester {

    // i would assume multiple languages would be handled kinda like this, but i haven't thought about it too much so idk
    private Dictionary English = new Dictionary();
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tester test = new Tester();
		boolean continueRunning = true;
		while (continueRunning) {
			continueRunning = test.mainMenu();
		}
	}
	
	
	/**
	 * process - run out tests
	 */
	public void setTests(){
		//TODO make this more comprehensive ig, prob do last
		System.out.println("\nTest deleting from empty tree.");
		English.deleteNode("java");
		System.out.println("\nTest displaying empty tree.");
		English.showTree();
		System.out.println("\nTest searching on empty tree.");
		English.search("poggers");
		System.out.println("\nTest adding nodes");
		English.addNode("banana");
		System.out.println(":)");
		English.addNode("apple");
		English.addNode("pomegranite");
		English.addNode("pear");
		English.addNode("apples");
		English.addNode("orange");
		System.out.println("\nTest Showing populated tree.");
		English.showTree();
		//TODO finish the tests that are commented out below.
		//System.out.println("\nTest deleting node with 2 children");
		//English.deleteNode();
		//English.showTree();
		//System.out.println("\nTest deleting node with 1 child");
		//English.deleteNode();
		//English.showTree();
		//System.out.println("\nTest deleting node with 0 children");
		//English.deleteNode();
		//English.showTree();
		System.out.println("\nTest seaching for a node that exists");
		English.search("apple");
		System.out.println("\nTest seaching for a node that doesn't exist");
		English.search("mywilltolive");		
	}
	
	/**
     * Method prints a formatted display of the game board in it's current state
     * @return Returns a boolean value to indicate if the program should keep running or not.
     */
	public boolean mainMenu() {
		displayMainMenu();
		Scanner s = new Scanner(System.in);
		String userInput = s.nextLine();
		switch(userInput)
        {
        	case "1" :
        		System.out.println("Add node selected. \n");
        		English.userAddNode();
        		break;
        		
        	case "2" :
        		System.out.println("Search for node Selected. \n");
        		English.userSearch();
        		break;
    
            case "3" :
            	System.out.println("Delete node selected. \n");
            	English.userDelete();
                break;
                
            case "4" :
            	System.out.println("Display Tree selected. \n");
            	English.showTree();
                break;
                
            case "5" :
            	System.out.println("Run preset tests selected. \n");
            	setTests();
                break;
                
            case "0" :
                System.out.println("Quiting. \n");
                return false;
                 
            default :
                System.out.println("Command not recognised.\n");
        }
		return true;
	}
	
	/**
     * Method prints a formatted display of the main menu options
     */
	public void displayMainMenu() {
		System.out.println("\nPlease select one of the options below:");
		System.out.println("1 - Add node");
		System.out.println("2 - Search for node");
		System.out.println("3 - Delete node");
		System.out.println("4 - Display Tree");
		System.out.println("5 - Run preset tests");
		System.out.println("0 - Exit\n");	
	}
}
