package com.mcecraft.worldgen.biomes.impl;

import com.mcecraft.worldgen.biomes.NormalBiome;
import com.mcecraft.worldgen.columnFeatures.ColumnFeatureContainer;
import com.mcecraft.worldgen.columnFeatures.impl.DesertFeatures;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;

public class Desert extends NormalBiome {
	private static Desert INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:desert")).build();

	public Desert(WorldGen wg) {
		super(wg, BIOME, Plate.DRY, Block.SAND.getBlockId(), Block.SANDSTONE.getBlockId(), 3);
		addColumnFeatures(new ColumnFeatureContainer(0.01, new DesertFeatures()));
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/64).setSeed(7893467).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(2).setPersistence(0.5).setOctaves(4).build();

	public static Desert getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (70+getPlateNoise(x/64.0,z/64.0, 6) + noise1.getNoise(x, z)*7);
	}

}
