
 
 /*  Linear Probing Hash Table */ 
public class Hashtable {
	private int currentSize, maxSize;       
  
   public Pair[] map = null;	// stores hash table elements
 
    // Constructor 
    public Hashtable(int capacity) {
        currentSize = 0;
        maxSize = capacity;
    	map = new Pair[maxSize];
		for(int i = 0; i < maxSize ; i++)
			map[i]=null;
    }  
 
 
    // Function to check if hash table is empty 
    public boolean isEmpty() 
    {
        return getSize() == 0;
    }
    
    // Function to get size of hash table 
    public int getSize() 
    {
        return currentSize;
    }
 

    // Function to check if hash table is full 
    public boolean isFull() 
    {
        return currentSize == maxSize;
    }
 

 
    // Function to check if hash table contains a key
    public boolean contains(String key) 
    {
        return get(key) !=  null;
    }
 
    // Function to get hash-code/hash-value for a given key 
    public int hash(String key) 
    {
        return Math.abs(key.hashCode()) % maxSize;
    }    
 
    // Function to get value for a given key 
    public String get(String key)
    {
    	// TODO(1)
    	// Get element value using key and linear probing
    	// If the element doesn't exist, return null
        for(int start=hash(key), i=start; map[i] != null;) {
            if(map[i].getKey().equals(key))//Pair class doesn't have a flag parameter. Should I add one?
                return map[i].getValue();
            i=(i+1)%maxSize;
            if(i == start)
                break;
        }
        return null;
    }

    
    // Function to insert key-value pair 
    public void put(String key, String val)  
    {                
		// TODO(2)    	
        // Insert element val using key. If the table is full, you must call rehash first
        if(isFull())
            rehash();
        int index=hash(key);
        for(; map[index] != null; index=(index+1)%maxSize) {}
        map[index] = new Pair(key, val);
        currentSize++;
    }
    
    
    /// Function to rehash when the table is full
    public void rehash()  
    {   
		// TODO(3)
    	// Hint: 1-backup the reference to the old hash map 
    	//      2-create a new map twice the old size
    	//      3-hash all elements from the old hash map to new hash map
        Pair[] oldMap = map;
        maxSize *= 2;
        map = new Pair[maxSize];
        currentSize = 0;
        for(int i=0; i<oldMap.length; i++)
            put(oldMap[i].getKey(), oldMap[i].getValue());
    }
     
 
    // Function to print HashTable 
    public void printHashTable()
    {
        System.out.println("\nHash Table: Key, Value ");
        for (int i = 0; i < maxSize; i++)
            if (map[i] != null)
            	System.out.println(map[i].getKey()+", "+map[i].getValue());
        System.out.println();
    }   
}

class Pair{

	private String key;
	private String value;

	public Pair(String key, String value){
		this.key = key;
		this.value = value;
	}

	public String getKey(){
		return key;
	}

	public String getValue(){
		return value;
	}

}


