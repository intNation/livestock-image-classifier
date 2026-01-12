package acsse.csc3a.ui;

import org.graphstream.graph.implementations.*;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.view.View;
import acsse.csc3a.ds.graph.*;
import acsse.csc3a.ds.list.Position;
import acsse.csc3a.util.*;
import javafx.scene.Node;

public class GraphVisualizer {
	private org.graphstream.graph.Graph gs;
	private View view;

	public GraphVisualizer() {
		gs = new SingleGraph("livestock");
		gs.setAttribute("ui.stylesheet", "node{size:40px;shape:box;fill-mode:image-scaled;} edge{text-size:10;}");
		FxViewer v = new FxViewer(gs, org.graphstream.ui.view.Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		v.enableAutoLayout();
		view = v.addDefaultView(false);
	}

	public Node getView() {
		return (Node) view;
	}

	public void sync(AdjacencyMapGraph<FeatureVector, Double> model) {
		gs.clear(); // rebuild nodes
		for (Position<FeatureVector> p : model.vertices()) {
			FeatureVector fv = p.getElement();
			org.graphstream.graph.Node n = gs.addNode(fv.getPath());
			n.setAttribute("ui.label", fv.getPath().substring(fv.getPath().lastIndexOf('/') + 1));
			n.setAttribute("ui.style", "fill-image: url('" + fv.getPath().replace("\\", "/") + "');");
		}
		for (acsse.csc3a.ds.graph.Edge<Double> e : model.edges()) {
			String id1 = ((FeatureVector) e.getU().getElement()).getPath();
			String id2 = ((FeatureVector) e.getV().getElement()).getPath();
			String eid = id1 + "--" + id2;
			if (gs.getEdge(eid) == null) {
				org.graphstream.graph.Edge ge = gs.addEdge(eid, id1, id2);
				ge.setAttribute("ui.label", String.format("%.2f", e.getWeight()));
			}
		}
	}
}