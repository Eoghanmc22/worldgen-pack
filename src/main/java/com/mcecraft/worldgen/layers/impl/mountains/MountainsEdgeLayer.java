package com.mcecraft.worldgen.layers.impl.mountains;

import com.mcecraft.worldgen.biomes.impl.mountains.Mountains;
import com.mcecraft.worldgen.biomes.impl.mountains.MountainsSteep;
import com.mcecraft.worldgen.biomes.impl.mountains.MountainsEdge;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgen.layers.MultiSamplingLayer;

public class MountainsEdgeLayer extends MultiSamplingLayer {

	public MountainsEdgeLayer(long baseSeed) {
		super(baseSeed);
	}

	@Override
	protected int genBiomes(int x, int z, int u, int d, int r, int l, int c, ChunkRandom rng) {
		rng.initChunkSeed(x, z);
		int climate = Mountains.getInstance().getClimateId();
		if (Layer.getClimate(u) == climate || Layer.getClimate(d) == climate || Layer.getClimate(r) == climate || Layer.getClimate(l) == climate) {
			if (Layer.getClimate(u) == climate && Layer.getClimate(d) == climate && Layer.getClimate(r) == climate && Layer.getClimate(l) == climate) {
				return c;
			}
			return Layer.setIsLand(0, true) | Layer.setClimate(0, MountainsEdge.getInstance().getClimateId()) | Layer.setBiomeId(0, MountainsEdge.getInstance().getBiomeId());
		}
		return c;
	}

}
