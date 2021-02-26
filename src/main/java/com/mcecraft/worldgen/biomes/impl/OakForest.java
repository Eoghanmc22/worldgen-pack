package com.mcecraft.worldgen.biomes.impl;

import com.mcecraft.worldgen.biomes.NormalBiome;
import com.mcecraft.worldgen.columnFeatures.ColumnFeatureContainer;
import com.mcecraft.worldgen.columnFeatures.impl.TreeAdapter;
import com.mcecraft.worldgen.plates.Plate;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.features.impl.TreeFeature;

public class OakForest extends NormalBiome {

	private static OakForest INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:oak_forest")).build();
	private static final TreeFeature tree = new TreeFeature(0, Block.OAK_LEAVES, Block.OAK_LOG);

	public OakForest(WorldGen wg) {
		super(wg, BIOME, Plate.TEMPERATE, Block.GRASS_BLOCK.getBlockId(), Block.DIRT.getBlockId(), 3, tree);
		addColumnFeatures(new ColumnFeatureContainer(0.05f, new TreeAdapter(tree)));
		INSTANCE = this;
	}

	public static OakForest getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (66+getPlateNoise(x,z, 1) + Forest.noise1.getNoise(x, z)*2);
	}

}
