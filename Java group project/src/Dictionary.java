import java.util.Scanner;

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
    	String userInput = "";
    	while(userInput==null) {
    		try {
    			System.out.println("Please enter the name of the item to add:");
    			userInput = s.nextLine();
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
    	    		System.out.println(current.getWord());
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
     * Gets the user to input the id of a node to delete.
     */
    public void userDelete() {
    	Scanner s = new Scanner(System.in);
    	int id = -1;
    	while(id<0) {
    		try {
    			System.out.println("Please enter the id of the item to add:");
    			id = s.nextInt();
    		} catch (Exception e) {
    			System.out.println("Please enter an integer value greater than or equal to 0.");
    			s.next();
    		}
    	}
    }
    
    /**
     * A method to find the node to delete and call the correct method to remove it based on how many children it has.
     * @param id the id of the node to delete
     */
    public void deleteNode(String word) {
    	if(root!=null) {
    		Word previous=null;
        	Word current=root;
        	while(word.equals(current.getWord())) {
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
}
