public class Word    
{
    private String word;
    Word leftChild;
    Word rightChild;

    /**
     * Default constructor. Initialise fields to default values
     */
    public Word()
    { 
        word = "";
        leftChild=null;
        rightChild=null;  
    }

    /**
     * Alternative constructor. Set fields to given values.
     *
     * @param id The id for the item
     * @param name The item's name
     * @param cost The item's cost
     */
    public Word(String word)
    {
        this.word = word;
    }

	/**
	 * Set node's contained word
	 * @param id the new word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
     * Get the node's contained word
     * 
     * @return The word
     */
    public String getWord()
    {
        return word;
    }

    /**
     * Get the node's left child
     * 
     * @return a reference to the left child
     */
    public Word getLeft()
    {
       return leftChild;
    }
    
    /**
     * Get the node's right child
     * 
     * @return a reference to the right child
     */
    public Word getRight()
    {
       return rightChild;
    }

    /**
     * Set the left child
     * 
     * @param leftChild a reference to a Word to become the left child node of this node
     */
    public void setLeft(Word node)
    {
    	this.leftChild = node;
    }
    
    /**
     * Set the right child
     * 
     * @param rightChild a reference to a Word to become the right child node of this node
     */
    public void setRight(Word node)
    {
    	this.rightChild = node;
    }
}
