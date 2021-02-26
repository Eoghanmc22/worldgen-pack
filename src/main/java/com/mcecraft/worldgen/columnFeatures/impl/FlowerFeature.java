package com.mcecraft.worldgen.columnFeatures.impl;

import com.mcecraft.worldgen.columnFeatures.ColumnFeature;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.BlockPosition;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgenUtils.Batch;

public class FlowerFeature implements ColumnFeature {

	private final JNoise noise;
	private final Block flower;

	public FlowerFeature(int seed, Block flower) {
		this.flower = flower;
		this.noise = JNoise.newBuilder().fastSimplex().setFrequency(1.0/64).setSeed(seed).build();
	}

	@Override
	public void generate(WorldGen wg, int biomeId, ChunkRandom rng, int x, int y, int z, int chunkX, int chunkZ) {
		if (Layer.isLand(biomeId) && y > 64 && noise.getNoise(x + chunkX * 16, z + chunkZ * 16) > 0.93) {
			Batch batch = new Batch(new BlockPosition(x + chunkX * 16, y, z + chunkZ * 16));
			batch.setBlock(0, 0, 0, flower);
			batch.apply(wg);
		}
	}

}
