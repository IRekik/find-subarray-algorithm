import java.util.*;
import java.util.stream.Collectors;

public class Tutorial {

    // This method finds subarrays that are present in two list (biggerArray, smallerArray)
    // and not present in a third list (excludedArray)
    public static List<List> findSubArrays (List biggerArray, List smallerArray, List excludedArray) {
        boolean hasFoundMatch = false;

        List <Integer> subArray = new ArrayList();
        List <List> subArrayList = new ArrayList();

        // Iterate through bigger list and see if item is present in smaller array
        for (int i = 0; i < biggerArray.size(); i++) {
            // if bigger array item is in smaller array and not in excludedArray, activates hasFoundMatch
            if (smallerArray.contains(biggerArray.get(i)) && !excludedArray.contains(biggerArray.get(i))) {
                subArray.add((Integer) biggerArray.get(i));
                hasFoundMatch = true;
                // if we reached the end of the list and there is a match
                if (i == biggerArray.size() - 1) {
                    subArrayList.add(new ArrayList(subArray));
                }
            }
            // else if previously found elements of biggerArray U smallerArray, started inserting, and
            // found the first element that no longer match, stop insertion and disable hasFoundMatch
            else if (!smallerArray.contains(biggerArray.get(i)) && hasFoundMatch) {
                subArrayList.add(new ArrayList(subArray));
                subArray.clear();
                hasFoundMatch = false;
            }
        }

        return subArrayList;
    }

    // This method converts a List of ints to an Array of ints
    public static int [] convertList (List list) {
        int [] subArray = new int[list.size()];
        for (int i = 0; i < (int)list.size(); i++) {
            subArray[i] = (int) list.get(i);
        }
        return subArray;
    }

    // This method finds the index of the biggest subArray List
    public static int findIndexOfBiggestList(List<List> subArrayList) {
        int biggestListSize = subArrayList.get(0).size();
        int listIndex = 0;
        for (int i = 1; i < subArrayList.size(); i++) {
            if (subArrayList.get(i).size() > biggestListSize) {
                listIndex = i;
            }
        }
        return listIndex;
    }
    
    public static int [] algo (int [] a1, int [] a2, int [] a3) {
        List<Integer> biggerArray;
        List<Integer> smallerArray;
        List<Integer> excludedArray = Arrays.stream(a3).boxed().collect(Collectors.toList());

        // Compares which list is the biggest between a1 and a2
        if (a1.length > a2.length) {
            biggerArray = Arrays.stream(a1).boxed().collect(Collectors.toList());
            smallerArray = Arrays.stream(a2).boxed().collect(Collectors.toList());
        }
        else {
            biggerArray = Arrays.stream(a2).boxed().collect(Collectors.toList());
            smallerArray = Arrays.stream(a1).boxed().collect(Collectors.toList());
        }

        List <List> subArrayList = new ArrayList();
        subArrayList = findSubArrays(biggerArray, smallerArray, excludedArray);

        int listIndex = findIndexOfBiggestList(subArrayList);

        int [] subArray = convertList(subArrayList.get(listIndex));
        
        return subArray;
    }

    public static void main(String[] args) {
        int [] a1 = {1, 2, 3, 4, 5, 6, 7};
        int [] a2 = {2, 3, 4, 6, 7};
        int [] a3 = {4, 5};
        int [] r = algo(a1, a2, a3);
        for (int j : r) {
            System.out.println(j);
        }
    }
}