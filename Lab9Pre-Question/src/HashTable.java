
import java.io.IOException;


class DataItem {

    private int iData;

    public DataItem(int ii) // constructor
    {
        iData = ii;
    }

    public int getKey() {
        return iData;
    }
}

class HashTable {

    private DataItem[] hashArray; // array holds hash table
    private int arraySize;

    public HashTable(int size) // constructor
    {
        arraySize = size;
        hashArray = new DataItem[arraySize];
    }

    public void displayTable() {
        // Displays the hashTable as shown in output
        for (DataItem dataItem : hashArray) {
            if(dataItem != null)
            {
                System.out.print(dataItem.getKey() + "\t");
            }
            else{
                System.out.print("**\t");
            }
        }
        System.out.println("");
    }

    public int hashFunc(int key) {
        // Calculates the hash value and returns it
        return key%10;
    }

    public void insert(DataItem item) {
        // Inserts an item into the hash table
        hashArray[hashFunc(item.getKey())] = item;
    }

    public DataItem delete(int key) {
        // Deletes an item from the hash table
        // Puts -1 to the place of removed item
        DataItem temp = find(key);
        if(temp != null)
        {
            var deleted = hashArray[hashFunc(key)];
            hashArray[hashFunc(key)] = new DataItem(-1);
            return deleted;
        }
        return null;
    }

    public DataItem find(int key) {
        // Finds an item inside the hash table
        return hashArray[hashFunc(key)];
    }
}

    class HashTableApp {

        public static void main(String[] args) throws IOException {
            DataItem aDataItem;
            HashTable theHashTable = new HashTable(10);
            aDataItem = new DataItem(23);
            theHashTable.insert(aDataItem);
            theHashTable.displayTable();
            aDataItem = new DataItem(88);
            theHashTable.insert(aDataItem);
            theHashTable.displayTable();
            aDataItem = new DataItem(37);
            theHashTable.insert(aDataItem);
            theHashTable.displayTable();
            aDataItem = new DataItem(43);
            theHashTable.insert(aDataItem);
            theHashTable.displayTable();

            theHashTable.delete(55);
            theHashTable.displayTable();

            theHashTable.delete(43);
            theHashTable.displayTable();

        }
    
}
