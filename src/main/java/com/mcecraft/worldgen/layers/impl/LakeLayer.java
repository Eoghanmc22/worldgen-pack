package com.mcecraft.worldgen.layers.impl;

import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgen.layers.MultiSamplingLayer;

public class LakeLayer extends MultiSamplingLayer {

	private final int chance;

	public LakeLayer(long baseSeed, int chance) {
		super(baseSeed);
		this.chance = chance;
	}

	@Override
	protected int genBiomes(int x, int z, int u, int d, int r, int l, int c, ChunkRandom rng) {
		rng.initChunkSeed(x, z);
		int climate = Layer.getClimate(c);
		if (Layer.getClimate(u) == climate && Layer.getClimate(d) == climate && Layer.getClimate(r) == climate && Layer.getClimate(l) == climate && rng.nextInt(chance) == 0) {
			return Layer.setIsLand(c, false);
		}
		return c;
	}

}
