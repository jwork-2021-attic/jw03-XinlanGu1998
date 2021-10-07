package example;

public class QuickSorter implements Sorter {

    private int[] a;

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

    private int partition(int left, int right){
        int k = left-1;
        for (int i = left; i < right; i++){
            if (a[i] < a[right]){
                swap(i, ++k);
            }
        }
        swap(right, ++k);
        return k;
    }

    private void quickSort(int left, int right){
        if (left >= right) return;
        int pivot = partition(left, right);
        quickSort(left, pivot-1);
        quickSort(pivot+1, right);
    }

    @Override
    public void sort() {
        quickSort(0, a.length-1);
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}
