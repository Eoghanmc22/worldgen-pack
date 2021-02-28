package com.mcecraft.worldgen.layers.impl.mountains;

import com.mcecraft.worldgen.biomes.impl.mountains.DynamicMountains;
import com.mcecraft.worldgen.biomes.impl.mountains.Mountains;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgen.layers.MultiSamplingLayer;

public class DynamicMountainsFinisherLayer extends MultiSamplingLayer {

	private final int data;

	public DynamicMountainsFinisherLayer(long baseSeed, int height) {
		super(baseSeed);
		this.data = height;
	}

	@Override
	protected int genBiomes(int x, int z, int u, int d, int r, int l, int c, ChunkRandom rng) {
		rng.initChunkSeed(x, z);
		int climate = Mountains.getInstance().getClimateId();
		int biome = Mountains.getInstance().getBiomeId();

		if (!Layer.cmpBiomeClimate(c, climate, biome)) {
			return c;
		}
		return Layer.setIsLand(0, true) | Layer.setClimate(0, DynamicMountains.getInstance().getClimateId()) |
				Layer.setBiomeId(0, DynamicMountains.getInstance().getBiomeId()) | Layer.setBiomeData(0, data);
	}

}
