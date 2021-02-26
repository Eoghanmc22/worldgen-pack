package com.mcecraft.worldgen.layers.impl;

import com.mcecraft.worldgen.biomes.impl.Beach;
import com.mcecraft.worldgen.biomes.impl.ShelfBiome;
import com.mcecraft.worldgen.biomes.impl.ocean.Ocean;
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
		int shelfClimate = ShelfBiome.getInstance().getClimateId();
		int shelfBiome = ShelfBiome.getInstance().getBiomeId();
		if (Layer.cmpBiomeClimate(c, shelfClimate, shelfBiome)) {
			return c;
		}
		if (Layer.cmpBiomeClimate(u, shelfClimate, shelfBiome) || Layer.cmpBiomeClimate(d, shelfClimate, shelfBiome) || Layer.cmpBiomeClimate(l, shelfClimate, shelfBiome) || Layer.cmpBiomeClimate(r, shelfClimate, shelfBiome)) {
			if (Layer.cmpBiomeClimate(u, shelfClimate, shelfBiome) && Layer.cmpBiomeClimate(d, shelfClimate, shelfBiome) && Layer.cmpBiomeClimate(l, shelfClimate, shelfBiome) && Layer.cmpBiomeClimate(r, shelfClimate, shelfBiome)) {
				return c;
			}
			if (Layer.getClimate(c) == Ocean.getInstance().getClimateId()) {
				return c;
			}
			return Layer.setIsLand(0, true) | Layer.setClimate(0, Beach.getInstance().getClimateId()) | Layer.setBiomeId(0, Beach.getInstance().getBiomeId());
		}
		return c;
	}

}
