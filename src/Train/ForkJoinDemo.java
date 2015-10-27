package Train;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by ejiping on 2015/10/27.
 */
public class ForkJoinDemo {
	public static void main(String[] args) throws Exception{
		ForkJoinPool pool = new ForkJoinPool();
		SumTask task = new SumTask(1, 100);

		ForkJoinTask<Integer> r = pool.submit(task);
		System.out.println(r == task);
		System.out.println(r.join());

	}

}

class SumTask extends RecursiveTask<Integer> {
	int to;
	int from;

	public SumTask(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public Integer compute() {
		int result = 0;
		if (to - from > 25) {
			SumTask left = new SumTask(from, (from + to) / 2);
			SumTask right = new SumTask((from + to) / 2 + 1, to);
			left.fork();
			right.fork();
			result = left.join() + right.join();
		} else {
			for (int i = from; i <= to; i++) {
				result += i;
			}
		}
		return result;
	}
}
