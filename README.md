# Livestock Image Classifier (Graph-Based)

A Java-based livestock image classification system that identifies and groups visually similar animals using a graph-based similarity approach. The system is designed to support basic livestock management tasks such as identifying similar animals, counting them, and maintaining an up-to-date image dataset.

## Project Overview
This project models each livestock image as a node in a similarity graph, where edges represent visual similarity scores computed from image features. Instead of using machine learning libraries, the system relies on classical image processing, graph theory, and custom data structures.

## Key Features
- Upload and manage livestock images
- Identify and list visually similar animals
- Count similar animals for a selected image
- Add new animals to the dataset
- Remove animals from the dataset (e.g. sold livestock)
- Graph-based similarity search for efficient comparison

## Technologies Used
- Java
- JavaFX
- Graph Data Structures
- Histogram-Based Feature Extraction
- Custom Priority Queues and Hash Maps

## Accuracy
- Achieved approximately **70–80% accuracy** on test datasets based on visual similarity matching.

## How It Works
1. Images are converted into feature vectors using histogram analysis
2. Feature vectors are compared using distance metrics
3. A similarity graph is constructed with weighted edges
4. Nearest neighbors are used to classify and retrieve similar animals

## Why Graph-Based?
Using a similarity graph allows efficient comparison and retrieval without relying on machine learning models, making the system lightweight and transparent.

## How to Run
1. Clone the repository
2. Open the project in an IDE such as IntelliJ IDEA
3. Run the `MainApp` class
4. Load an image dataset to begin classification

## Author
**Nation Dibakwane**  
Computer Science Graduate – University of Johannesburg
