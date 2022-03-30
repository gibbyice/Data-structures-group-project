import java.util.Scanner;

public class Tester {

    // i would assume multiple languages would be handled kinda like this, but i haven't thought about it too much so idk
    private Dictionary English = new Dictionary();
    private Dictionary notEnglish = new Dictionary();
    private Dictionary[] dictionaries = {English, notEnglish};
    private int activePos = 0;
    
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
	 * Allows the user to change what dictionary to run methods on
	 */
	public void changeActiveDic() {
		System.out.println("Please select which dictioanry to set as active:");
		System.out.println("1 - English");
		System.out.println("2 - Not English");
		Scanner s = new Scanner(System.in);
		String userInput = s.nextLine();
		switch(userInput)
        {
        	case "1" :
        		System.out.println("English is now the active dictionary.");
        		setActivePos(0);
        		break;
        		
        	case "2" :
        		System.out.println("Not English is now the active dictionary.");
        		setActivePos(1);
        		break;
                 
            default :
                System.out.println("Command not recognised.\n");
        }
	}
	
	/**
	 * Sets activePos to a new position
	 * @param pos the position of the new active dictionary
	 */
	public void setActivePos(int pos) {
		activePos = pos;
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
        		dictionaries[activePos].userAddNode();
        		break;
        		
        	case "2" :
        		System.out.println("Search for node Selected. \n");
        		dictionaries[activePos].userSearch();
        		break;
    
            case "3" :
            	System.out.println("Delete node selected. \n");
            	dictionaries[activePos].userDelete();
                break;
                
            case "4" :
            	System.out.println("Display Tree selected. \n");
            	dictionaries[activePos].showTree();
                break;
                
            case "5" :
            	System.out.println("Run preset tests selected. \n");
            	setTests();
                break;
                
            case "6" :
            	System.out.println("load a dic selected. \n");
            	dictionaries[activePos].loadDic();
                break;
                
            case "7" :
            	System.out.println("Display alphabetically selected. \n");
            	dictionaries[activePos].displayAlphabetically(dictionaries[activePos].getRoot());
                break;
                
            case "8" :
            	System.out.println("Save dictionary selected. \n");
            	dictionaries[activePos].userSaveDic(dictionaries[activePos].getRoot());
                break;

			case "9" :
				System.out.println("Autocomplete a word selected. \n");
				dictionaries[activePos].autoComplete();
				break;
				
			case "10" :
				System.out.println("Change active dictionary selected. \n");
				changeActiveDic();
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
		System.out.println("6 - load a dic");
		System.out.println("7 - Display alphabetically");
		System.out.println("8 - Save dictionary");
		System.out.println("9 - Autocomplete a word");
		System.out.println("10 - Change active dictionary");
		System.out.println("0 - Exit\n");	
	}
}
