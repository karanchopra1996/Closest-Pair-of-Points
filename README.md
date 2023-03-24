# Closest-Pair-of-Points
Implemented the Closest Pair of Points


Here is a summary of the algorithm:

Read data from file into Graph G.
Create residual graph Gf by adding source and sink nodes and edges from source and to sink.
Initialize flow f to zero along each edge.
While not done:
	Construct level graph LG from Gf using breadth-first search (delete back and cross edges).
	If no path exists from source to sink (i.e., sink not found during BFS), output matching, done.
	Initialize location to source node, path to empty.
	While not stuck at source:
		If location is sink:
			Augment flow with path.
			Update Gf.
			Delete edges in path from LG.
			Set location to source.
			Clear path.
		Else:
			If stuck, retreat:
				Delete current node and incoming edges from LG.
				Delete last edge from path.
			Else:
				Advance along some edge in LG that leaves current location.
				Update current path.
