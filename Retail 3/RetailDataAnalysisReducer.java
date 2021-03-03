package vvsretailtotal;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RetailDataAnalysisReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	private FloatWritable result = new FloatWritable();

	public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			throws IOException, InterruptedException {
		float sum = 0.0f;
		int count = 0;
		for (FloatWritable val : values) {
			count += 1;
			sum += val.get();
		}

		result.set(sum);
		String reduceKey = "Number of sales " + String.valueOf(count) + ", Sales Value : ";
		context.write(new Text(reduceKey), result);
	}
}