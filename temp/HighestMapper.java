package vvstemp;
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class HighestMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>
{
		public static final int MISSING = 9999;
     
            public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException
      {
            String line = value.toString();
            String year = line.substring(15,19);               
            int temperature;                    
            if (line.charAt(87)=='+')
                        temperature = Integer.parseInt(line.substring(88, 92));
            else
                        temperature = Integer.parseInt(line.substring(87, 92));       
           
            String quality = line.substring(92, 93);
            if(temperature != MISSING && quality.matches("[01459]"))
            output.collect(new Text(year),new IntWritable(temperature));
                 
            }
       
}

