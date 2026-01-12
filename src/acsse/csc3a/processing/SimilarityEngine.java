package acsse.csc3a.processing;

import acsse.csc3a.ds.graph.*;
import acsse.csc3a.ds.list.Position;
import acsse.csc3a.ds.priorityqueue.*;
import acsse.csc3a.util.*;
import java.util.ArrayList;

public class SimilarityEngine {
	private AdjacencyMapGraph<FeatureVector, Double> graph = new AdjacencyMapGraph<>();
	private FeatureExtractor extractor = new HistogramFeatureExtractor();

	public void loadFolder(String dir) {
		String[] files = FileUtils.listImages(dir);
		if (files == null)
			return;
		for (String name : files) {
			FeatureVector fv = extractor.extract(ImageLoader.load(new java.io.File(dir, name)), dir + "/" + name);
			graph.insertVertex(fv);
		}
		// build full graph (O(n^2)) w/ weights
		ArrayList<Position<FeatureVector>> verts = new ArrayList<>();
		
		for (Position<FeatureVector> v : graph.vertices())
			verts.add(v);
		for (int i = 0; i < verts.size(); i++)
			for (int j = i + 1; j < verts.size(); j++) {
				double d = Distance.euclidean(verts.get(i).getElement().getData(), verts.get(j).getElement().getData());
				graph.insertEdge(verts.get(i), verts.get(j), d);
			}
	}

	public FeatureVector[] topK(FeatureVector query, int k) {
		HeapAdaptablePriorityQueue<Double, FeatureVector> pq = new HeapAdaptablePriorityQueue<>();
		for (Position<FeatureVector> v : graph.vertices()) {
			double d = Distance.euclidean(query.getData(), v.getElement().getData());
			pq.insert(d, v.getElement());
		}
		FeatureVector[] res = new FeatureVector[Math.min(k, pq.size())];
		for (int i = 0; i < res.length; i++)
			res[i] = pq.removeMin().getValue();
		return res;
	}

	public AdjacencyMapGraph<FeatureVector, Double> getGraph() {
		return graph;
	}

	public FeatureVector addNewImage(String path) {
		FeatureVector q = extractor.extract(ImageLoader.load(new java.io.File(path)), path);
		Position<FeatureVector> vq = graph.insertVertex(q);
		for (Position<FeatureVector> v : graph.vertices())
			if (v != vq) {
				double d = Distance.euclidean(q.getData(), v.getElement().getData());
				graph.insertEdge(vq, v, d);
			}
		return q;
	}

	public void sell(FeatureVector fv) { // remove node & delete file
		FileUtils.deleteImage(fv.getPath());
		Position<FeatureVector> target = null;
		for (Position<FeatureVector> p : graph.vertices())
			if (p.getElement() == fv) {
				target = p;
				break;
			}
		if (target != null)
			graph.removeVertex(target);
	}
}
