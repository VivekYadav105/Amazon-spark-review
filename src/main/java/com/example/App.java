package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.io.IOException;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import com.example.RegressionApproach;

public class App {
    public static void main(String[] args) {
        // Set up Spark and imporrting the dataset
        SparkConf conf = new SparkConf().setAppName("SparkExample").setMaster("local[*]");
        conf.set("spark.ui.reverseProxy", "true");
        conf.set("spark.ui.reverseProxyUrl", "/static");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        SparkSession spark = SparkSession.builder().appName("SparkExample").config("spark.ui.enabled", true).getOrCreate();
        String filePath = "./target/classes/cloths.json";
        String uiUrl = spark.sparkContext().uiWebUrl().get();
        System.out.println("Spark Application UI: " + uiUrl);
        try {
            Desktop.getDesktop().browse(new URI(uiUrl));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Dataset<Row> rawData = spark.read().json(filePath);
        Dataset<Row> cleanDataset = rawData.na().drop().select("reviewText","asin","overall");
        cleanDataset.show();
        
        // char input = ' ';
        // while(input!='q'){
        //     Scanner inputScanner = new Scanner(System.in);
        //     input = inputScanner.next().charAt(0);
        //     if(input=='q'){
        //         spark.stop();
        //         sc.close();
        //     }else{
        //         System.out.println("To close the spark session press q");
        //     }
        //     inputScanner.close();
        // }

        int numFeatures = 1000;
        HashingTF hashingTF = new HashingTF()
                .setInputCol("words")
                .setOutputCol("features")
                .setNumFeatures(numFeatures);

        Tokenizer tokenizer = new Tokenizer().setInputCol("reviewText").setOutputCol("words");
        Dataset<Row> wordsData = tokenizer.transform(cleanDataset);
        Dataset<Row> featurizedData = hashingTF.transform(wordsData);

        featurizedData.show();
        //kmeans approach
        int numClusters = 5; // Adjust based on your data
        KMeans kmeans = new KMeans().setK(numClusters).setSeed(1L);
        KMeansModel kmeansModel = kmeans.fit(featurizedData);
        Dataset<Row> predictions = kmeansModel.transform(featurizedData);
        predictions.select("asin", "prediction").show();

        //logistic approach
        Dataset<Row> logisticPredictions = RegressionApproach(featurizedData);
        
        // Stop the Spark session
        spark.stop();
        sc.close();
    }
}
