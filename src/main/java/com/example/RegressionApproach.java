package com.example;

import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;


public class RegressionApproach {
    Dataset<Row>cleanInput;
    
    public void RegressionApproach(Dataset<Row> cleanInput){
        this.cleanInput = cleanInput;
        return;
    };

    public Dataset<Row> main(){
                // Create a binary sentiment label (positive or negative)
        Dataset<Row> labeledData = this.cleanInput.withColumn("label", this.cleanInput.col("overall").geq(4).cast("double"));

        // Split the data into training and testing sets
        Dataset<Row>[] splits = labeledData.randomSplit(new double[]{0.8, 0.2}, 1234);
        Dataset<Row> trainingData = splits[0];
        Dataset<Row> testData = splits[1];

        // Train a logistic regression model
        LogisticRegression lr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(0.01);
        LogisticRegressionModel lrModel = lr.fit(trainingData);

        // Make predictions on the test data
        Dataset<Row> predictions = lrModel.transform(testData);

        // Evaluate the model
        double accuracy = lrModel.evaluate(predictions).accuracy();
        System.out.println("Test set accuracy: " + accuracy);

        return predictions;

    }
}