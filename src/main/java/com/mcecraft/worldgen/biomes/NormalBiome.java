package com.mcecraft.worldgen.biomes;

import com.mcecraft.worldgen.columnFeatures.ColumnFeature;
import com.mcecraft.worldgen.columnFeatures.ColumnFeatureContainer;
import com.mcecraft.worldgen.plates.Plate;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.BlockPosition;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.biomes.BiomeConfig;
import net.minestom.worldgen.features.PlaceableFeature;
import net.minestom.worldgenUtils.Batch;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class NormalBiome extends BiomeConfig {

	private final ArrayList<ColumnFeatureContainer> columnFeatures = new ArrayList<>();

	private final WorldGen wg;
	private final Plate plate;
	private final short surfaceBlock;
	private final short topBlock;
	private final int topLayers;

	public NormalBiome(WorldGen wg, Biome minestomBiome, Plate plate, short surfaceBlock, short topBlock, int topLayers, PlaceableFeature... features) {
		super(minestomBiome, 1, features);
		this.wg = wg;
		this.plate = plate;
		this.surfaceBlock = surfaceBlock;
		this.topBlock = topBlock;
		this.topLayers = topLayers;
	}

	public void addColumnFeatures(ColumnFeatureContainer... features) {
		columnFeatures.addAll(Arrays.asList(features));
	}

	public Plate getPlate() {
		return plate;
	}

	public double getPlateNoise(double x, double z, double amplitude) {
		return plate.getNoise().getNoise(x, z) * amplitude;
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
		} else {
			batch.setBlockStateId(x,height,z, surfaceBlock);
		}
		for (final ColumnFeatureContainer feature : columnFeatures){
			feature.run(wg, biomeId, rng, x, height + 1, z, chunkX, chunkZ);
		}
	}

}
