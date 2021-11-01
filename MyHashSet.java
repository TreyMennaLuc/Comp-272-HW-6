import java.util.*;
import java.security.*;
import java.io.*;

public class MyHashSet{
        // find the best hash based on the data
                //The best would be hash 2
    public static int collisionNum(ArrayList<LinkedList<Integer>> myList)
    {
        int collisions = 0;
        for(LinkedList<Integer> list: myList){
            if(list.size() > 1)
            {
                collisions++;
            }
        }
        return collisions;
    }
    public static int averageLLSize(ArrayList<LinkedList<Integer>> myList)
    {
        int linkNum = 0;
        int average = 0;
        for(LinkedList<Integer> list: myList)
        {
            average += list.size();
            linkNum++;
        }
        return average/linkNum;
    }
    
    public static long miliTime()
    {
        //System.currentTimeMillis() and time for before and after main hash function
       return System.currentTimeMillis();
    }

    public static void hash1(Hashtable<Integer, String> ewl) {
        ArrayList<LinkedList<Integer>> myHashSet = new ArrayList<>();
        /*  Use hashCode() for any string s, hashCode(s) = Math.abs(s.hashCode()) % tableSize, where
        tableSize = 262,127. Use an ArrayList<LinkedList> of capacity tableSize, and the given hash function H to store all the unique words in the EnglishWordList.
Use the variable myHashSet for this data structure.
Whenever a collision occurs, store the word in the LinkedList at the hash
index where collision occurred. After reading all the words into myHashSet*/
        Enumeration<Integer> e = ewl.keys();
        long before = miliTime();
        while (e.hasMoreElements())
        {
            int key = e.nextElement();
            int hashInt = ewl.get(key).hashCode();

            LinkedList<Integer> hashList = new LinkedList<>();
            hashList.add(hashInt);
            if (myHashSet.contains(hashList))
            {
                int index = myHashSet.indexOf(hashList);
                LinkedList<Integer> getList = myHashSet.get(index);
                getList.add(hashInt);
                getList.add(hashInt);
                myHashSet.remove(hashList);
                myHashSet.add(getList);
            }
            else
            {
                myHashSet.add(hashList);
            }
        }
        long after = miliTime();
        long timeTaken = after - before;
        int collisonNum = collisionNum(myHashSet);
        int averageLLSize = averageLLSize(myHashSet);
        System.out.println("Time taken: " + timeTaken + " milliseconds");
        System.out.println("Collision number: " + collisonNum);
        System.out.println("Average LL size: " + averageLLSize);



    }
     public static int hashCode(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public static void hash2(Hashtable<Integer, String> ewl) {
        ArrayList<LinkedList<Integer>> myHashSet = new ArrayList<>();
        /*Repeat the above with the following hash function H(s) = hash(key)%tableSize, where*/
        Enumeration<Integer> e = ewl.keys();
        long before = miliTime();
        while (e.hasMoreElements())
        {
            int key = e.nextElement();
            int hashInt = hashCode(key);

            LinkedList<Integer> hashList = new LinkedList<>();
            hashList.add(hashInt);
            if (myHashSet.contains(hashList))
            {
                int index = myHashSet.indexOf(hashList);
                LinkedList<Integer> getList = myHashSet.get(index);
                getList.add(hashInt);
                getList.add(hashInt);
                myHashSet.remove(hashList);
                myHashSet.add(getList);
            }
            else
            {
                myHashSet.add(hashList);
            }
        }
        long after = miliTime();
        long timeTaken = after - before;
        int collisonNum = collisionNum(myHashSet);
        int averageLLSize = averageLLSize(myHashSet);
        System.out.println("Time taken: " + timeTaken + " milliseconds");
        System.out.println("Collision number: " + collisonNum);
        System.out.println("Average LL size: " + averageLLSize);


    }
    public static int hashCode (String s) {
        byte[] sb = s.getBytes();
        int hash = 0;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] key = md.digest(sb);
            for (byte b : key) {
                hash += b;
            }
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;

    }
    public static void hash3(Hashtable<Integer, String> ewl) {
        ArrayList<LinkedList<Integer>> myHashSet = new ArrayList<>();
        /*For the third hash function, the size of the table will be 262,144 = 218. For this we will use the
one-way hash SHA-256 used in some computer security applications. We discussed this in class
a little bit.
BitSet bs = BitSet.valueOf(key);
// using the BitSet bs, you will extract 18 bits based on the
//get() method of BitSet. The 18 bits are extracted at the first
//18 prime numbers 2, 7,17,29,41,53,67,79,97,107,127,139,157,
173,191, 199,227,239. Put the bits together in that //order to
form an integer and return it. That will be the hash //value of
the key that you can use in the table of size 2^18.
}*/ Enumeration<Integer> e = ewl.keys();
        long before = miliTime();
        while (e.hasMoreElements())
        {
            int key = e.nextElement();
            int hashInt = hashCode(ewl.get(key));

            LinkedList<Integer> hashList = new LinkedList<>();
            hashList.add(hashInt);
            if (myHashSet.contains(hashList))
            {
                int index = myHashSet.indexOf(hashList);
                LinkedList<Integer> getList = myHashSet.get(index);
                getList.add(hashInt);
                getList.add(hashInt);
                myHashSet.remove(hashList);
                myHashSet.add(getList);

            }
            else
            {
                myHashSet.add(hashList);
            }
        }
        long after = miliTime();
        long timeTaken = after - before;
        int collisonNum = collisionNum(myHashSet);
        int averageLLSize = averageLLSize(myHashSet);
        System.out.println("Time taken: " + timeTaken + " milliseconds");
        System.out.println("Collision number: " + collisonNum);
        System.out.println("Average LL size: " + averageLLSize);
        }

    public static void main(String[] args)
    {
        Set<String> uniqueWords = new HashSet<>();
        int x = 0;
        //change hastable size for  hash 1-2 and then make it different for hash 3
        //524,288 the prime closest is 524,287
        Hashtable<Integer, String> englishWordList = new Hashtable<>();

        File file = new File("EnglishWordList.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                uniqueWords.add(sc.nextLine());
            }
            for (String i : uniqueWords) {
                englishWordList.put(x, i);
                x++;
            }
        }
        catch  (FileNotFoundException e) {
            System.out.println("file not found");
            }
            System.out.println("Hashing 1");
                hash1(englishWordList);
            System.out.println("Hashing 2");
                hash2(englishWordList);
            System.out.println("Hashing 3");
                 hash3(englishWordList);
            System.out.println("Done");
        }




}
