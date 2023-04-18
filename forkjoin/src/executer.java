import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class executer implements Runnable {
private int[] arrexecuter;
private int left;
private int right;
private mergesort mergesort = new mergesort();

public executer(int[] arrexecuter, int left, int right){
    this.arrexecuter = arrexecuter;
    this.left = left;
    this.right = right;

}

@Override
public void run() {
    if (left < right) {
        int mid = (left + right) / 2;
        executer leftTask = new executer(arrexecuter, left, mid);
        executer rightTask = new executer(arrexecuter, mid + 1, right);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(leftTask);
        executor.submit(rightTask);
        mergesort.merge(arrexecuter, left, mid, right);
        executor.shutdown();
    }
}
}