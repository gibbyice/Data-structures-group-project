import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;

public class Dictionary 
{
    private Word root;

    /**
     * Default constructor. Initialise fields to default values.
     */
    public Dictionary()
    {
        root = null;
    }

    /**
     * Get the node which is at the 'root' of the list
     * 
     * @return A reference to a Word object which represents the node at the
     *         root of the tree (or null if the tree is empty, i.e. no 'root' has
     *         been set yet).    
     */
    public Word getRoot()
    {
        return root;
    }

    /**
     * Set the 'root' of the list to the given node
     * 
     * @param  newRoot A reference to a Word object which will be
     *                 the root of the tree. 
     *                 
     * if a tree already exists, the existing tree will be 
     * lost since the root is being assigned to something new.
     */
    public void setRoot(Word newRoot)
    {
        root = newRoot;
    }
    
    /**
     * Method gets the user to provide an word to search for in the dictionary
     */
    public void userSearch() {
    	Scanner s = new Scanner(System.in);
    	String userInput = null;
    	while(userInput==null) {
    		try {
    			System.out.println("Please enter the name of the item to search for:");
    			userInput = s.nextLine();
    			search(userInput);
    		} catch (Exception e) {
    			System.out.println("Please enter a string.");
    			s.next();
    		}
    	}
    }
    
    /**
     * method searches for a provided word in the dictionary and displays it if it can be found
     * @param word the word to search for
     */
    public void search(String word) {
    	Word current=root;
    	try {
    		while(!(word.equals(current.getWord()))) {
    			if(word.compareTo(current.getWord())<0) {
        			current=current.getLeft();
        		} else {
        			current=current.getRight();
        		}
    			if(word.equals(current.getWord())) {
    	    		System.out.println(current.getWord()+" was found.");
    	    	}
        	}
    	} catch (Exception e) {
 			System.out.println("'"+word+"' is not present in this dictionary, would you like to add it?");
 			//TODO actually implement this option^^^^^^^^^^^^^^
 		}
    }
    
    /**
     * Method gets user input for an item to add.
     */
    public void userAddNode() {
    	Scanner s = new Scanner(System.in);
    	String word=null;
    	while(word==null) {
    		try {
    			System.out.println("Please enter the name of the item to add:");
    			word = s.nextLine();
    		} catch (Exception e) {
    			System.out.println("Please enter a string.");
    			s.next();
    		}
    	}
		addNode(word);
    }
    
    /**
     * Add a node to the binary tree
     * @param id The id of the Word
     * @param name The name of the item
     * @param cost The cost of the item
     */
    public void addNode(String word) {
    	Word node = new Word(word);
    	// if tree is empty, set root = node
    	if(root==null) {
    		root=node;
    		System.out.println("'"+word+"' added as root.");
    	} else {
    		boolean found = false;
    		Word previous = null;
    		Word current = root;
    		//navigate through tree by comparing IDs until an empty spot is reached
    		while(current!=null && !found) {
    			previous=current;
    			//Note: a switch statement may be faster here but im not sure if that'd even work :p
    			if(word.equals(current.getWord())) {
    				found = true;
    			} else if (word.compareTo(current.getWord())<0) {
        			current=current.getLeft();
        		} else {
        			current=current.getRight();
        		}
    		}
    		if(found) {
	    		System.out.println("Item not added, word is already present.");
    		} else {
    			//compare words to determine if the node to add should be added as a left or right child
	    		if (word.compareTo(previous.getWord())<0) {
	    			previous.setLeft(node);
	    		} else {
	    			previous.setRight(node);
	    		}
	    		System.out.println("'"+word+"' added.");
    		}
    	}
    }
    
    /**
     * Method to display the content of a tree, on its side.
     */
    public void showTree()
    {
    	if(root!=null) {
    		// start indent at zero
        	String indent = "";

        	// display the tree from its root node
        	displayTree(root, indent);
    	} else {
    		System.out.println("Tree is empty, there is nothing to display");
    	}
    }

    /**
     * a method to recursively display a given tree (or sub-tree) 'p', to a specified level of indent
     * @param p the node to display from
     * @param indent the current level of indentation
     */
    public void displayTree(Word p, String indent)
    {
    	if (p != null)
    	{
    		// display the right sub-tree of 'p', increasing the indent
               	displayTree(p.getRight(), indent + "\t");              
               
               	// display 'p' itself, at the current level of indent 
                	System.out.println(indent + p.getWord());
    	
    		// display left sub-tree of 'p', increasing the indent
                	displayTree(p.getLeft(), indent + "\t");
    	}
    }
    
    /**
     * a method to recursively display a given tree (or sub-tree) 'p' in alphabetical order
     * @param p the node to display from
     */
    public void displayAlphabetically(Word p)
    {
    	if (p != null)
    	{
    		// display left sub-tree of 'p'
    			displayAlphabetically(p.getLeft());              
               
           	// display 'p' itself
            	System.out.println(p.getWord());
    	
    		// display the right sub-tree of 'p'
            	displayAlphabetically(p.getRight());
    	}
    }
    
    /**
     * Gets the user to input the id of a node to delete.
     */
    public void userDelete() {
    	Scanner s = new Scanner(System.in);
	    String word=null;
		while(word==null) {
			try {
				System.out.println("Please enter the name of the item to delete:");
				word = s.nextLine();
			} catch (Exception e) {
				System.out.println("Please enter a string.");
				s.next();
			}
		}
		deleteNode(word);
		}
    
    /**
     * A method to find the node to delete and call the correct method to remove it based on how many children it has.
     * @param id the id of the node to delete
     */
    public void deleteNode(String word) {
    	if(root!=null) {
    		Word previous=null;
        	Word current=root;
        	while(!(word.equals(current.getWord()))) {
        		previous = current;
        		try {
        			if(word.compareTo(current.getWord())<0) {
            			current=current.getLeft();
            		} else {
            			current=current.getRight();
            		}
        		} catch (Exception e) {
        			System.out.println(e+word+" is not present in this dictionary.");
        		}
        		
        	}
        	if(word.equals(current.getWord())) {
        		//if current has no children, call respective deletion method
            	if(current.getLeft()==null&&current.getRight()==null) {
            		deleteLeaf(current, previous);
            	} else if (current.getLeft()!=null&&current.getRight()!=null) {
            		//if current has two children, call respective deletion method
            		deleteTwoChilded(current);
            	} else {
            		//if current has one children, call respective deletion method
            		deleteOneChilded(current, previous);
            	}
            	System.out.println(word+" deleted.");
        	}
    	} else {
    		System.out.println("Cannot delete from empty tree.");
    	}
    	
    }
    
    /**
     * Deletes a leaf node from the binary tree
     * @param current the node to delete
     * @param previous the node previous to the node to delete
     */
    public void deleteLeaf(Word current, Word previous) {
    	if (previous.getLeft()==current) {
    		previous.setLeft(null);
    	} else {
    		previous.setRight(null);
    	}
    }
    
    /**
     * Loads incomplete words into the auto complete method and prints the time elapsed, word count and the words completed per second.
     */
    public void loadIncompleteWords() {
    	Date beginTime = new Date();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        int wordCount = 0;
        Scanner s = new Scanner(System.in);
		System.out.println("Enter a file name to search for: ");
		String searchName = s.nextLine();
		System.out.println(searchName);
        try{
            fileReader = new FileReader(searchName+".txt");
            bufferedReader = new BufferedReader(fileReader); 
            String nextLine = bufferedReader.readLine();
            while (nextLine != null){
                System.out.println(nextLine);
				wordCount++;
				String[] threeWords = autoCorrectSearcher(nextLine);
				System.out.println(threeWords[0] + "\t" + threeWords[1] + "\t" + threeWords[2]+"\n");
				nextLine = bufferedReader.readLine();
            } 
        }
        catch (FileNotFoundException e){
            System.out.println("File was not found.");
        }
        catch (IOException e){
            System.out.println("Error occured opening or reading from the file.");
        }
        finally
        {
        	Date endTime = new Date();
            long elapsed = (endTime.getTime() - beginTime.getTime())/1000;
            long wordsPerSec = wordCount/elapsed;
            System.out.println("Time elapsed: "+elapsed+ " Words pre second: "+wordsPerSec+" Word count: "+wordCount);
            if (bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                }
                catch (IOException e){
                    System.out.println("A problem occured whilst attempting to close the file.");
                }
            }
        }
    }
    
    /**
     * loads my dic ;)
     */
    public void loadDic() {
		System.out.println("Loading dic...");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        Scanner s = new Scanner(System.in);
		System.out.println("Enter a file name to search for: ");
		String searchName = s.nextLine();
        try{
            fileReader = new FileReader(searchName+".txt");
            bufferedReader = new BufferedReader(fileReader); 
            String nextLine = bufferedReader.readLine();
            while (nextLine != null){
				addNode(nextLine);
				nextLine = bufferedReader.readLine();
            } 
        }
        catch (FileNotFoundException e){
            System.out.println("File was not found.");
        }
        catch (IOException e){
            System.out.println("Error occured opening or reading from the file.");
        }
        finally
        {
            if (bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                }
                catch (IOException e){
                    System.out.println("A problem occured whilst attempting to close the file.");
                }
            }
        }
    }
    
   /**
    * Lets the user specify a file to save the contents of the dictionary to
    * @param p the node to display from
    */
    public void userSaveDic(Word p) {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        try{
        	//TODO Add scanner to allow user specified file name
            outputStream = new FileOutputStream("output.txt");
            printWriter = new PrintWriter(outputStream); 
            saveDic(p, printWriter);
        }
        catch (IOException e){
            System.out.println("A problem occured opening or writing to the file");
            System.out.println("/t" + e);
        }
        finally{
           if (printWriter != null)
              printWriter.close();
        }
    }
    
    /**
    * a method to recursively navigate a given tree (or sub-tree) and save it's contents to a file
    * @param p the node to display from
	* @param printWriter the PrintWriter used to write to the file
	*/
    public void saveDic(Word p, PrintWriter printWriter) {
    	if(p!=null) {
        	{
        			saveDic(p.getLeft(), printWriter); 
   
        			String lineToWrite=p.getWord();
        			System.out.println(lineToWrite+" written to file.");
        			printWriter.print(lineToWrite+"\r\n");
  
        			saveDic(p.getRight(), printWriter);	
        	}
        }
    }
    
    /**
     * deletes a node with two child nodes from the binary tree
     * @param toDelete the node to delete
     */
    public void deleteTwoChilded(Word toDelete) {
    	Word current = toDelete.getLeft();
    	Word previous = toDelete;
    	while(current.getRight()!=null) {
    		previous=current;
			current=current.getRight();
    	}
    	toDelete.setWord(current.getWord());
    	previous.setRight(current.getLeft());
    }
    
    /**
     * Deletes a node with one child from the binary tree
     * @param current the node to delete
     * @param previous the node previous to the node to delete
     */
    public void deleteOneChilded(Word current, Word previous) {
    	if (previous.getLeft()==current) {
    		if(current.getLeft()!=null) {
    			previous.setLeft(current.getLeft());
    		} else {
    			previous.setLeft(current.getRight());
    		}
    	} else {
    		if(current.getLeft()!=null) {
    			previous.setRight(current.getLeft());
    		} else {
    			previous.setRight(current.getRight());
    		}
    	}
    }

	public void autoComplete() {
		Scanner s = new Scanner(System.in);
		String userInput;
		System.out.println("Please input your unfinished word.");
		userInput = s.nextLine();
		String[] threeWords = autoCorrectSearcher(userInput);
		if (threeWords[0] == null) {
			System.out.println("No autocorrect can be found.");
		} else if ((threeWords[1] == null) && (threeWords[2] != null)) {
			System.out.println(threeWords[0] + "\t" + threeWords[2]);
		} else if ((threeWords[1] != null) && (threeWords[2] == null)) {
			System.out.println(threeWords[0] + "\t" + threeWords[1]);
		} else if ((threeWords[0] != null) && (threeWords[1] == null) && (threeWords[2] == null)) {
			System.out.println(threeWords[0]);
		} else {
			System.out.println(threeWords[0] + "\t" + threeWords[1] + "\t" + threeWords[2]);
		}
	}

	public String[] autoCorrectSearcher(String word) {
		Word current = root;
		Word previous = root;
		String[] output = new String[3];
		try {
			while(!(word.equals(current.getWord()))) {
				if(word.compareTo(current.getWord())<0) {
					previous = current;
					current=current.getLeft();
				} else {
					previous = current;
					current=current.getRight();
				}
				if(word.equals(current.getWord())) {
					output[0] = word;
					return output;
				}
			}
		} catch (Exception e) {
			try {
				output[0] = previous.getWord();
			} catch (Exception e1) {
				output[0] = null;
			}
			try {
				output[1] = previous.getLeft().getWord();
			} catch (Exception e2) {
				output[1] = null;
			}
			try {
				output[2] = previous.getRight().getWord();
			} catch (Exception e3) {
				output[2] = null;
			}
		}
		return output;
	}
}
