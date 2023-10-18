# Amazon Spark Review Analysis

Analyzing Amazon product reviews using Apache Spark and MLlib with Logistic Regression and K-Means Clustering.

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
- [Report](#report)

## Overview

Amazon Spark Review Analysis is a project that leverages Apache Spark and its inbuilt MLlib library to analyze and derive insights from Amazon product reviews. This project utilizes the inbuilt logistic regression algorithm for sentiment analysis and the k-means clustering algorithm to identify patterns within the reviews. The combination of these algorithms, provided by Spark MLlib, offers a comprehensive understanding of the sentiments expressed in the reviews and groups them based on similarities.

## Tech Stack

- Apache Spark
- Apache Spark MLlib
- Apache Maven
- Java (OpenJDK 11)

## Prerequisites

List any prerequisites that are required to run the project. This may include software installations, dependencies, or other configurations.

- Apache Spark [Link to Installation Guide](spark-installation-guide)
- Apache Maven [Link to Installation Guide](maven-installation-guide)
- Java (OpenJDK 11) [Link to Installation Guide](java-installation-guide)

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/amazon-spark-review-analysis.git
   cd amazon-spark-review-analysis
2. Download the dataset from [https://drive.google.com/file/d/1TQz6oI5T-JEDuLeV4DvFNEsQk23kXsVA/view?usp=drive_link](here)
3. Download the spark binaries form [https://dlcdn.apache.org/spark/spark-3.5.0/spark-3.5.0-bin-hadoop3.tgz](here)
4. Add the SPARK_HOME variable to path with the value as root spark directory
5. Add the bin folder to the path
6. extract the dataset and paste it in `\src\main\resources`
7. Make sure that the name of json dataset is cloths.json
8. Build the project using the Maven Wrapper:

   ```bash
   ./mvnw clean package
   ```
   If you're on Windows, use:
   ```bash
   ./mvnw.cmd clean install
9. Execute the project
   ```bash
   ./mvnw exec:java
   ```
   If you're on Windows, use:
     ```bash
     ./mvnw.cmd exec:java

## Report
To view the report click [https://drive.google.com/file/d/1GTX68JZqmDmbsUYmgKLEyN13n9fPQB8X/view?usp=sharing](here) 
