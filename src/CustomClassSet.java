import Sorting.SelectionSort;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomClassSet {

    public static void main(String args[]) {
        Set myEmpList = new HashSet<Emp>();
        myEmpList.add(new Emp("Shrey", 22));
        myEmpList.add(new Emp("Stuti", 26));
        myEmpList.add(new Emp("Tanmay", 22));

        myEmpList.add(new Emp("Shrey", 23));
        myEmpList.add(new Emp("Shrey", 23));
        myEmpList.add(new Emp("Tanmay", 23));
        myEmpList.add(new Emp("Tanmay", 23));


        for (Object emp : myEmpList) {
            Emp emp2 = (Emp) emp;
            System.out.println("Employee Name : " + emp2.name + "Employee Age : " + emp2.age);
        }
    }

    static class Emp {
        String name;
        int age;

        Emp(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) // ie. both pointers are pointing to same object
                return true;
            if (obj == null) // ie. if obj is not pointing to any object
                return false;
            if (this.getClass() != obj.getClass()) // ie. we are checking if both objects are of same class or not
                return false;
            // The above checks are common checks that we should do if we want to override equals method

            // now below is the actual logic that varies based on how you want 'set' to
            // equate your custom objects ie. here I am telling that
            // if the names of 2 objects are same then they are duplicate

            Emp emp2 = (Emp) obj;
            return this.name.equals(emp2.name); // this equal method is of String so that will simply
            // compare whether both strings are same or not

        }

        @Override
        public int hashCode() { // we have to override hashcode as well otherwise
            // our set won't work. Here you can write your own implementation of hashcode. The object hashcode uses hashing algo to generate hashcode
            return this.age + this.name.hashCode(); // this is fairly an easy way to
            // implement hashcode. Here an object will have same hashcode if there age and name
            // both are same and then set will simply keep one of the duplicates and drop others but if
            // only one is same and other one is different then set will consider it as different
            // because then they will have different hashcode.
        }
    }

}
