package com.mcecraft.worldgen.biomes;

import com.mcecraft.worldgen.columnFeatures.ColumnFeatureContainer;
import com.mcecraft.worldgen.plates.Plate;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.biomes.BiomeConfig;
import net.minestom.worldgen.features.PlaceableFeature;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BaseBiome extends BiomeConfig {

	private final ArrayList<ColumnFeatureContainer> columnFeatures = new ArrayList<>();

	private final WorldGen wg;
	private final Plate plate;

	protected BaseBiome(Biome minestomBiome, int variants, WorldGen wg, Plate plate, PlaceableFeature... features) {
		super(minestomBiome, variants, features);
		this.wg = wg;
		this.plate = plate;
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
		for (final ColumnFeatureContainer feature : columnFeatures){
			feature.run(wg, biomeId, rng, x, height + 1, z, chunkX, chunkZ);
		}
	}

}
