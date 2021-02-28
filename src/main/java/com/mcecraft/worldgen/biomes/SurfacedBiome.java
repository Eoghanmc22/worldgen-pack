package com.mcecraft.worldgen.biomes;

import com.mcecraft.worldgen.plates.Plate;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.features.PlaceableFeature;

public class SurfacedBiome extends BaseBiome {

	private final SurfaceBuilder builder;
	private final int builderHeight;

	protected SurfacedBiome(WorldGen wg, Biome minestomBiome, Plate plate, SurfaceBuilder builder, int builderHeight, PlaceableFeature... features) {
		super(minestomBiome, 0, wg, plate, features);
		this.builder = builder;
		this.builderHeight = builderHeight;
	}

	@Override
	public void generate(ChunkBatch batch, int x, int z, int height, int chunkX, int chunkZ, int biomeId, ChunkRandom rng) {
		for (int y = 0; y < height-builderHeight; y++) {
			batch.setBlock(x,y,z, Block.STONE);
		}
		builder.generate(batch, x, z, height-builderHeight, height, chunkX, chunkZ, biomeId, rng);
		super.generate(batch, x, z, height, chunkX, chunkZ, biomeId, rng);
	}

	@Override
	public int getHeight(int i, int i1, int i2) {
		return 0;
	}

}
