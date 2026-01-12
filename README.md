# Livestock Image Classifier (Graph-Based)

Ever wondered if there was an easier way to keep track of your livestock just by looking at their photos? That’s exactly what this project does. It’s a Java-based tool that groups visually similar animals, helping farmers, researchers, or anyone working with livestock to manage their herd through images.  

## About This Project
Instead of throwing machine learning at the problem, this project takes a more hands-on approach. Each livestock image is treated as a point in a graph, and the connections between them show how similar the animals look. By building this “similarity graph,” the system can quickly find animals that look alike, count them, and even help update your image database.  

## What You Can Do
- Upload and organize your livestock images  
- Find animals that look alike in seconds  
- Count visually similar animals with ease  
- Add new animals or remove ones that are sold  
- Use a graph-based search to quickly find matches  

## How It Works
1. Each image is converted into a feature vector using histogram analysis  
2. Vectors are compared to calculate similarity scores  
3. A graph is built with these scores as connections between images  
4. The system uses nearest neighbors in the graph to find similar animals  

Think of it like a social network—but for animals that look alike.  

## Why Graphs?
Graphs make comparisons fast and efficient. You don’t need heavy machine learning libraries, and you can actually see how the system is connecting the dots between your images. It’s lightweight, transparent, and surprisingly effective.  

## Accuracy
On test datasets, the system matches animals with around **70–80% accuracy** based purely on visual similarity.  

## Tech Stack
- Java & JavaFX  
- Graph Data Structures  
- Histogram-Based Image Features  
- Custom Priority Queues and Hash Maps  

## Getting Started
1. Clone this repository  
2. Open the project in IntelliJ IDEA or your favorite Java IDE  
3. Run the `MainApp` class  
4. Load your image dataset and start classifying  

## Author
**Nation Dibakwane**  
Computer Science Graduate – University of Johannesburg
