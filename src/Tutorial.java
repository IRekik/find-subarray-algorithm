import java.io.File;
import java.util.*;

public class Tutorial {
    public static void main(String[] args) {
        // Initialize variables and lists
        String line = "";
        List <String> nameList = new ArrayList<>();
        List <Integer> salaryList = new ArrayList<>();
        List <Integer> ageList = new ArrayList<>();
        int numberOfEntries = 0;

        // Open file and read line by line
        try {
                File csvFile = new File("Livre.csv");
                Scanner sc = new Scanner(csvFile);
                boolean firstFlag = true;
                while (sc.hasNextLine()) {
                    line = sc.nextLine() + "\n";
                    if (firstFlag) {
                        firstFlag = false;
                        continue;
                    }
                    // Split information and add it to its list
                    String [] data = line.split(",");
                    String col1 = data[0].substring(1, data[0].length() - 1);
                    int col2 = Integer.parseInt(data[1].substring(1, data[1].length() - 1));
                    int col3 = Integer.parseInt(data[2].substring(1, data[2].length() - 2));
                    nameList.add(col1);
                    salaryList.add(col2);
                    ageList.add(col3);
                    numberOfEntries++;
                }

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // Average age at the company
        double avgAge = ageList.stream()
                .mapToInt(val -> val)
                .average()
                .orElse(0.0);
        avgAge = Math.round(avgAge);
        System.out.println("Average age at the company: " + avgAge);
        System.out.println();

        // Average salary at the company
        double avgSalary = 0;
        for (Integer integer : salaryList) {
            avgSalary = avgSalary + integer;
        }
        avgSalary = avgSalary / salaryList.size();
        System.out.println("Average salary at the company: " + avgSalary);
        System.out.println();

        // Employee names in alphabetical order
        List sortedNameList = Arrays.asList(
                nameList.stream().sorted(
                        (s1, s2) -> s1.compareToIgnoreCase(s2)
                ).toArray(String[]::new)
        );
        System.out.println("The list of employees in alphabetical order:");
        System.out.println(sortedNameList);
        System.out.println();

        // How many employees make less than the average salary
        int lessThanAvg = 0;
        for (int i = 0; i < salaryList.size(); i++) {
            if (salaryList.get(i) < avgSalary) {
                lessThanAvg++;
            }
        }
        System.out.println("A total of " + lessThanAvg + " employees make less than the average salary of " + avgSalary);
        System.out.println();

        // Find the employee who makes $68,000
        int index = -1;
        for (int i = 0; i < salaryList.size(); i++) {
            if (salaryList.get(i) == 68000) {
                index = 1;
            }
        }
        System.out.println("The employee who makes $68,000 is " + nameList.get(index));
    }
}
