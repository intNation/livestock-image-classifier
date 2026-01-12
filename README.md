# Livestock Image Classifier (Graph-Based)

This is a Java program that groups livestock images based on how similar the animals look. It helps you organize images, find similar animals, count them, and manage your dataset.

## About

Each image is a node in a graph. Connections between nodes show how visually similar the animals are. The program uses classical image processing and graph algorithms instead of machine learning.

## Features

- Upload and manage images  
- Find visually similar animals  
- Count similar animals for a chosen image  
- Add or remove animals from the dataset  
- Graph-based similarity search  

## How It Works

1. Images are converted into feature vectors using histogram analysis  
2. Vectors are compared to calculate similarity scores  
3. A similarity graph is built with weighted edges  
4. Nearest neighbors in the graph are used to find similar animals  

## Accuracy

- Around **70–80%** accuracy on test datasets  

## Tech

- Java & JavaFX  
- Graph data structures  
- Histogram-based features  
- Custom priority queues and hash maps  

## Running the Program

1. Clone this repository  
2. Open it in IntelliJ IDEA or any Java IDE  
3. Run the `MainApp` class  
4. Load your image dataset  

## Author

**Nation Dibakwane**  
Computer Science Graduate – University of Johannesburg
