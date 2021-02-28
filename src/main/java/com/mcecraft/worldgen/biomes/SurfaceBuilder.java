package com.mcecraft.worldgen.biomes;

import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.worldgen.ChunkRandom;

public interface SurfaceBuilder {

	void generate(ChunkBatch batch, int x, int z, int startingHeight, int height, int chunkX, int chunkZ, int biomeId, ChunkRandom rng);
}
