package com.mcecraft.worldgen.layers.impl;

import com.mcecraft.worldgen.biomes.impl.Beach;
import com.mcecraft.worldgen.biomes.impl.ShelfBiome;
import com.mcecraft.worldgen.biomes.impl.Ocean;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgen.layers.MultiSamplingLayer;

public class BeachLayer extends MultiSamplingLayer {

	public BeachLayer(long baseSeed) {
		super(baseSeed);
	}

	@Override
	protected int genBiomes(int x, int z, int u, int d, int r, int l, int c, ChunkRandom rng) {
		rng.initChunkSeed(x, z);
		int oceanClimate = Ocean.getInstance().getClimateId();
		if (Layer.getClimate(u) == oceanClimate || Layer.getClimate(d) == oceanClimate || Layer.getClimate(r) == oceanClimate || Layer.getClimate(l) == oceanClimate) {
			if (Layer.getClimate(u) == oceanClimate && Layer.getClimate(d) == oceanClimate && Layer.getClimate(r) == oceanClimate && Layer.getClimate(l) == oceanClimate) {
				return c;
			}
			return Layer.setIsLand(0, true) | Layer.setClimate(0, Beach.getInstance().getClimateId()) | Layer.setBiomeId(0, Beach.getInstance().getBiomeId());
		}
		return c;
	}

}
