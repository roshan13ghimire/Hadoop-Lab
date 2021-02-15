package vvstemp;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;


public class HighestDriver extends Configured implements Tool{
      public int run(String[] args) throws Exception
      {
            
            JobConf conf = new JobConf(getConf(), HighestDriver.class);
            conf.setJobName("HighestDriver");

         
            conf.setOutputKeyClass(Text.class);
            conf.setOutputValueClass(IntWritable.class);

            
            conf.setMapperClass(HighestMapper.class);
            conf.setReducerClass(HighestReducer.class);
            
            Path inp = new Path(args[0]);
            Path out = new Path(args[1]);
            
            FileInputFormat.addInputPath(conf, inp);
            FileOutputFormat.setOutputPath(conf, out);

            JobClient.runJob(conf);
            return 0;
      }
     
      public static void main(String[] args) throws Exception
      {
           
        int res = ToolRunner.run(new Configuration(), new HighestDriver(),args);
            System.exit(res);
      }
}