package com.mcecraft.worldgen.layers.impl;

import com.mcecraft.worldgen.biomes.impl.mountains.MountainGravelValleys;
import com.mcecraft.worldgen.biomes.impl.mountains.MountainValleys;
import com.mcecraft.worldgen.biomes.impl.mountains.Mountains;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgen.layers.MultiSamplingLayer;

public class MountainValleysLayer extends MultiSamplingLayer {

	private final int chance;

	public MountainValleysLayer(long baseSeed, int chance) {
		super(baseSeed);
		this.chance = chance;
	}

	@Override
	protected int genBiomes(int x, int z, int u, int d, int r, int l, int c, ChunkRandom rng) {
		rng.initChunkSeed(x, z);
		int climate = Layer.getClimate(c);
		if (climate != Mountains.getInstance().getClimateId()) {
			return c;
		}
		if (Layer.getClimate(u) == climate && Layer.getClimate(d) == climate && Layer.getClimate(r) == climate && Layer.getClimate(l) == climate && rng.nextInt(chance) == 0) {
			switch (rng.nextInt(2)) {
				case 0: {
					final MountainValleys instance = MountainValleys.getInstance();
					return Layer.setBiomeId(Layer.setClimate(c, instance.getClimateId()), instance.getBiomeId());
				}
				case 1: {
					final MountainGravelValleys instance = MountainGravelValleys.getInstance();
					return Layer.setBiomeId(Layer.setClimate(c, instance.getClimateId()), instance.getBiomeId());
				}
			}
		}
		return c;
	}

}
