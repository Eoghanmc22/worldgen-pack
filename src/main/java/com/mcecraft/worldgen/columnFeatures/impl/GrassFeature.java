package com.mcecraft.worldgen.columnFeatures.impl;

import com.mcecraft.worldgen.columnFeatures.ColumnFeature;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.BlockPosition;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgenUtils.Batch;

public class GrassFeature implements ColumnFeature {

	@Override
	public void generate(WorldGen wg, int biomeId, ChunkRandom rng, int x, int y, int z, int chunkX, int chunkZ) {
		if (Layer.isLand(biomeId)) {
			Batch batch = new Batch(new BlockPosition(x + chunkX * 16, y, z + chunkZ * 16));
			if (rng.nextInt(2) == 0) {
				batch.setBlock(0, 0, 0, Block.GRASS);
			} else {
				batch.setBlockId(0, 0, 0, Block.TALL_GRASS.withProperties("half=lower"));
				batch.setBlockId(0, 1, 0, Block.TALL_GRASS.withProperties("half=upper"));
			}
			batch.apply(wg);
		}
	}

}
