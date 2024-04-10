package assn05;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
//        testP3();

    }

    // test Part 1
    public static void testP1(){

        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
    }

    // test Part 2
    public static void testP2(){
        MaxBinHeapER<Integer, Integer> heap = new MaxBinHeapER<>();

        // Enqueue some elements
        heap.enqueue(10);
        heap.enqueue(20);
        heap.enqueue(15);
        heap.enqueue(30);
        heap.dequeue();
        }


       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */


    /*
    Part 3
     */
    public static void testP3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /*
    Part 4
     */
    public static void testP4() {
        System.out.println(compareRuntimes());
               /*
        Part 4 - Write some tests to convince yourself that your code for Part 4 is working
         */

    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        // Array which you will populate as part of Part 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (Task 4.1) Here
        double timeBeforeDequeue = System.nanoTime();
        for (int i = 0; i < simplePQ.size(); i++) {
            simplePQ.dequeue();
        }
        double timeAfterDequeue = System.nanoTime();
        results[0] = timeAfterDequeue - timeBeforeDequeue;
        results [1] = results[0]/100000;


        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.2) Here
        double timeBeforeBHDequeue = System.nanoTime();
        for (int i = 0; i < binHeap.size(); i++) {
            binHeap.dequeue();
        }
        double timeAfterBHDequeue = System.nanoTime();
        results[2] = timeAfterBHDequeue - timeBeforeBHDequeue;
        results[3] = results[2]/100000;

        return results;
    }

}
