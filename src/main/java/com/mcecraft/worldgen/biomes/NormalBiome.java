package com.mcecraft.worldgen.biomes;

import com.mcecraft.worldgen.plates.Plate;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.features.PlaceableFeature;

public abstract class NormalBiome extends BaseBiome {
	private boolean iceLakes = false;
	private final short overlayBlock;
	private final int overlayElevation;
	private final short surfaceBlock;
	private final short topBlock;
	private final int topLayers;

	public NormalBiome(WorldGen wg, Biome minestomBiome, Plate plate, short overlayBlock, int overlayElevation, short surfaceBlock, short topBlock, int topLayers, PlaceableFeature... features) {
		super(minestomBiome, 1, wg, plate, features);
		this.overlayBlock = overlayBlock;
		this.overlayElevation = overlayElevation;
		this.surfaceBlock = surfaceBlock;
		this.topBlock = topBlock;
		this.topLayers = topLayers;
	}

	@Override
	public void generate(ChunkBatch batch, int x, int z, int height, int chunkX, int chunkZ, int biomeId, ChunkRandom rng) {
		for (int y = 0; y < height-topLayers; y++) {
			batch.setBlock(x,y,z, Block.STONE);
		}
		for (int y = height-topLayers; y < height; y++) {
			batch.setBlockStateId(x,y,z, topBlock);
		}
		if (height < 63) {
			batch.setBlockStateId(x,height,z, topBlock);
			for (int y = height+1; y < 64; y++) {
				batch.setBlock(x,y,z, Block.WATER);
			}
			if (iceLakes) {
				batch.setBlock(x,63,z, Block.ICE);
			}
		} else {
			batch.setBlockStateId(x,height,z, surfaceBlock);
			if (overlayBlock != -1 && height+1 >= overlayElevation) {
				batch.setBlockStateId(x, height+1, z, overlayBlock);
			}
		}
		super.generate(batch, x, z, height, chunkX, chunkZ, biomeId, rng);
	}

	public void setIceLakes(boolean iceLakes) {
		this.iceLakes = iceLakes;
	}

}
