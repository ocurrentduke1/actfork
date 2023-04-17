import java.util.concurrent.RecursiveAction;

public class forkjoin extends RecursiveAction {

    private mergesort mergeSort = new mergesort();
    int[] arrfork;
    int left, right;

    forkjoin(int[] arrfork, int left, int right) {
        this.arrfork = arrfork;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (left < right) {
            int mid = (left + right) / 2;
            forkjoin leftTask = new forkjoin(arrfork, left, mid);
            forkjoin rightTask = new forkjoin(arrfork, mid + 1, right);
            invokeAll(leftTask, rightTask);
            mergeSort.merge(arrfork, left, mid, right);
        }
    }
}