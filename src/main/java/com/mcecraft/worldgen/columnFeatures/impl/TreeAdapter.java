package com.mcecraft.worldgen.columnFeatures.impl;

import com.mcecraft.worldgen.columnFeatures.ColumnFeature;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.features.impl.TreeFeature;
import net.minestom.worldgen.layers.Layer;

public class TreeAdapter implements ColumnFeature {

	private final TreeFeature tree;

	public TreeAdapter(TreeFeature tree) {
		this.tree = tree;
	}

	@Override
	public void generate(WorldGen wg, int biomeId, ChunkRandom rng, int x, int y, int z, int chunkX, int chunkZ) {
		if (Layer.isLand(biomeId)) {
			tree.place(wg, x, y, z, chunkX, chunkZ);
		}
	}

}
