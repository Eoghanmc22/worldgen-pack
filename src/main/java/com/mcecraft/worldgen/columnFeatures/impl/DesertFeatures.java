package com.mcecraft.worldgen.columnFeatures.impl;

import com.mcecraft.worldgen.columnFeatures.ColumnFeature;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.BlockPosition;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgenUtils.Batch;

public class DesertFeatures implements ColumnFeature {

	@Override
	public void generate(WorldGen wg, int biomeId, ChunkRandom rng, int x, int y, int z, int chunkX, int chunkZ) {
		Batch batch = new Batch(new BlockPosition(x + chunkX*16,y,z + chunkZ*16));
		switch (rng.nextInt(2)) {
			case 0: {
				batch.setBlock(0,0,0, Block.DEAD_BUSH);
				break;
			}
			case 1: {
				if (rng.nextInt(8) == 0) {
					batch.setBlock(0,0,0, Block.CACTUS);
				} else if (rng.nextInt(4) == 0) {
					for (int i = 0; i < rng.nextInt(3) + 2; i++) {
						batch.setBlock(0,i,0, Block.CACTUS);
					}
				}
				break;
			}
		}
		batch.apply(wg);
	}

}
