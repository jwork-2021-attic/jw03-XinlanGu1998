package example;

public class HeapSorter implements Sorter {

    private int[] a;
    private int heapsize;

    @Override
    public void load(int[] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    private void siftDown(int i){
        if (i*2+1>=heapsize) return;
        int j = i*2+1;
        if (j+1<heapsize && a[j+1]>a[j]) j++;
        if (a[j]>a[i]){
            swap(i, j);
            siftDown(j);
        } 
    }

    @Override
    public void sort() {
        heapsize = a.length;
        //build max heap
        for (int i = (a.length-2)/2; i >= 0; i--){
            siftDown(i);
        }
        //pop n max elements and put at tail
        for (int i = a.length-1; i>0;i--){
            swap(0,i);
            heapsize--;
            siftDown(0);
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}
