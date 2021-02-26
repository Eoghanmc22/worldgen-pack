package com.mcecraft.worldgen.columnFeatures;

import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;

public class ColumnFeatureContainer {

	private final float chance;

	public double getChance() {
		return chance;
	}

	public ColumnFeature getFeature() {
		return feature;
	}

	private final ColumnFeature feature;

	public ColumnFeatureContainer(double chance, ColumnFeature feature) {
		this.chance = (float) chance;
		this.feature = feature;
	}

	public void run(WorldGen wg, int biomeId, ChunkRandom rng, int x, int y, int z, int chunkX, int chunkZ) {
		if (rng.nextFloat() < chance) {
			ChunkBatch.BLOCK_BATCH_POOL.execute(() -> feature.generate(wg, biomeId, rng, x, y, z, chunkX, chunkZ));
		}
	}
}
