package com.mcecraft.worldgen.biomes.impl;

import com.mcecraft.worldgen.biomes.NormalBiome;
import com.mcecraft.worldgen.columnFeatures.ColumnFeatureContainer;
import com.mcecraft.worldgen.columnFeatures.impl.TreeAdapter;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.features.impl.TreeFeature;
import net.minestom.worldgen.layers.Layer;

public class Forest extends NormalBiome {

	private static Forest INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:forest")).build();
	private static final TreeFeature bTree = new TreeFeature(0, Block.BIRCH_LEAVES, Block.BIRCH_LOG);
	private static final TreeFeature oTree = new TreeFeature(0, Block.OAK_LEAVES, Block.OAK_LOG);

	public Forest(WorldGen wg) {
		super(wg, BIOME, Plate.TEMPERATE, Block.GRASS_BLOCK.getBlockId(), Block.DIRT.getBlockId(), 3, bTree, oTree);
		addColumnFeatures(new ColumnFeatureContainer(0.02f, new TreeAdapter(bTree)), new ColumnFeatureContainer(0.03f, new TreeAdapter(oTree)));
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/16).setSeed(72681).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(2).setPersistence(0.5).setOctaves(4).build();

	public static Forest getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (66+getPlateNoise(x,z, 1) + noise1.getNoise(x, z)*2 + (!Layer.isLand(biomeId) ? -5 : 0));
	}

}
