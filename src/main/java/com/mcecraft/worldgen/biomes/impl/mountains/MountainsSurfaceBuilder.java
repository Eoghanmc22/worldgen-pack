package com.mcecraft.worldgen.biomes.impl.mountains;

import com.mcecraft.worldgen.biomes.SurfaceBuilder;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.worldgen.ChunkRandom;

public class MountainsSurfaceBuilder implements SurfaceBuilder {

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/128).setSeed(4564234).build();
	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(3).setPersistence(0.5).setOctaves(4).build();

	public static final JNoise base2 = JNoise.newBuilder().superSimplex().setFrequency(1.0/128).setSeed(5674356).build();
	public static final JNoise noise2 = JNoise.newBuilder().octavated().setNoise(base2).setLacunarity(3).setPersistence(0.5).setOctaves(4).build();

	@Override
	public void generate(ChunkBatch batch, int x, int z, int startingHeight, int height, int chunkX, int chunkZ, int biomeId, ChunkRandom rng) {
		int realX = chunkX*16 + x;
		int realZ = chunkZ*16 + z;

		double snowChance = height/120.0;
		int snowLayers = (int) (snowChance*8);

		double grassChance = (height/-90.0)+1;

		double gravelSample = (noise1.getNoise(realX, realZ)+1)/2.0;

		double iceChance = height/300.0;
		double iceSample = (noise2.getNoise(realX, realZ)+1)/2.0;

		if (rng.nextFloat() < grassChance) {
			for (int y = startingHeight; y <= height - 1; y++) {
				batch.setBlock(x,y,z, Block.DIRT);
			}
			batch.setBlock(x,height,z, Block.GRASS_BLOCK);

		} else if (iceSample < iceChance) {
			for (int y = startingHeight; y <= height; y++) {
				batch.setBlock(x,y,z, Block.ICE);
			}
		} else if (gravelSample < 0.4) {
			for (int y = startingHeight; y <= height; y++) {
				batch.setBlock(x,y,z, Block.GRAVEL);
			}
		} else {
			for (int y = startingHeight; y <= height; y++) {
				batch.setBlock(x,y,z, Block.STONE);
			}
		}

		int realSnowLayers = 0;
		for (int i = 0; i < snowLayers; i++) {
			if (rng.nextFloat() < snowChance) {
				realSnowLayers++;
			}
		}
		int realSnowLayersCount = realSnowLayers;
		for (int i = 0; i < realSnowLayers / 8 + 1; i++) {
			if (realSnowLayersCount < 0) {
				break;
			}
			int layers = Math.max(Math.min(realSnowLayersCount, 8), 1);
			batch.setBlockStateId(x,height+i+1,z, Block.SNOW.withProperties("layers=" + layers));
			realSnowLayersCount -= layers;
		}
	}

}
