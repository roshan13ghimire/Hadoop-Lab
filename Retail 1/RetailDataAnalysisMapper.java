package retailproduct;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RetailDataAnalysisMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
	private FloatWritable percentVal = new FloatWritable();
	private Text moKey = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		//Date          Time    City        Product-Category  Sale-Vale   Payment-Mode
		//2012-01-01	09:00	San Jose	Men's Clothing	  214.05    	Amex
		try {
			String valueTokens[] = value.toString().split("\t");
			String date = valueTokens[0];
			String productCat = valueTokens[3];
			float saleValue ;
			if (valueTokens.length > 0 && valueTokens.length == 6) {
				moKey.set(date + "\t" + productCat);
				saleValue = Float.parseFloat(valueTokens[4]);
				percentVal.set(saleValue);
				context.write(moKey, percentVal);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}