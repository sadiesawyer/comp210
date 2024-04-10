
package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return _heap.size();
    }
//all of my helper functions
    boolean isIndex(int idx){
        if((idx >= 0) && (idx <= size() - 1)){return true;}
        else{return false;}
    }
    static int rightChild(int idx){
        return (2*idx + 2);
    }
    static int leftChild(int idx){
        return (2*idx + 1);
    }
    private void swap(int i, int j) {
        Prioritized<V, P> temp = _heap.get(i);
        _heap.set(i, _heap.get(j));
        _heap.set(j, temp);
    }





    // TODO (Task 2A): enqueue
    public void enqueue(V value) { //simple enqueue that doesn't need to bubbleUp
        Patient newPatient = new Patient(value); //create a new patient
        System.out.println("Enqueueing" + value);
        _heap.add(_heap.size(), newPatient); //add them to the heap
    }

    // TODO (Task 2A): enqueue
    @Override
    public void enqueue(V value, P priority) { //enqueue with a priority
        Patient newPatient = new Patient(value, priority);
        System.out.println("Enqueueing" + value + ", " + newPatient.getPriority());
        _heap.add(newPatient);
        bubbleUp(_heap.size() - 1);
    }

    public void bubbleUp(int index){
        if (index == 0) {
            return;
        }

        Prioritized child = _heap.get(index);
        Prioritized parent = _heap.get((index - 1) / 2);

        if (child.getPriority().compareTo(parent.getPriority()) >= 0) {
            _heap.set(((index - 1) / 2), child);
            _heap.set(index, parent);
            bubbleUp((index - 1) / 2);
        }
    }

    // TODO (Task 2A): dequeue
    @Override
    public V dequeue() {
        if(_heap.size() == 0){
            return null;}

        V value = _heap.get(0).getValue();

        if (_heap.size() == 1) {
            _heap.remove(_heap.get(0));
        }

        else{
            _heap.set(0,_heap.get(_heap.size()-1));
            _heap.remove(size()-1);
            bubbleDown(0);
        }
        System.out.println("Dequeueing" + value);
        return value;
    }
    public void bubbleDown(int index) {
        int maxIndex = index;

        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);

        if (leftChildIndex < size() && _heap.get(leftChildIndex).getPriority().compareTo(_heap.get(maxIndex).getPriority()) >= 0) {
            maxIndex = leftChildIndex;
        }

        if (rightChildIndex < size() && _heap.get(rightChildIndex).getPriority().compareTo(_heap.get(maxIndex).getPriority()) >= 0) {
            maxIndex = rightChildIndex;
        }

        if (maxIndex != index) {
            swap(index, maxIndex);
            bubbleDown(maxIndex);
        }
    }

    // TODO (Task 2A): getMax
    @Override
    public V getMax() {
        if (_heap.isEmpty()) {
            return null;
        }
        int highestidx = 0; //set a highest index at zero

        for (int i = 1; i < _heap.size(); i++) {
            Prioritized compare = _heap.get(i);
            Prioritized currentHighest = _heap.get(highestidx);

            if (compare.getPriority().compareTo(currentHighest.getPriority()) > 0) { //if the one we are checking is bigger than the current highest then it is the new highest
                highestidx = i;
            }
        }

        return _heap.get(highestidx).getValue(); //return the value of the highest priority
    }

    // TODO (part 2B) : updatePriority
    public void updatePriority(V value, P newPriority) {
        int index = -1;
        for (int i = 0; i < size(); i++) { //we find the value we are looking for
            Prioritized<V, P> patient = _heap.get(i);
            if (patient.getValue().equals(value)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            Prioritized<V, P> updatedPatient = new Patient<>(value, newPriority); // create a new patient with updated priority
            _heap.set(index, updatedPatient); // Re-insert the patient into the heap
            bubbleUp(_heap.size() - 1); // Maintain the heap property
        }
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Task 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for(int i = 0; i < initialEntries.length; i++){
            enqueue(initialEntries[i].getValue(), initialEntries[i].getPriority());
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }





}
