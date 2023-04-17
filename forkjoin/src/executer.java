import java.util.concurrent.Callable;

public class executer extends mergesort implements Callable<int[]>{

    private int[] arrexecuter;
    private int l;
    private int r;

    public executer(int[]arrexecuter, int l, int r){
        this.arrexecuter = arrexecuter;
        this.l = l;
        this.r = r;
    }

    @Override
    public int[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }

    
}
