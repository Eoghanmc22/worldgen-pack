package com.mcecraft.worldgen.biomes.impl;

import com.mcecraft.worldgen.biomes.NormalBiome;
import com.mcecraft.worldgen.columnFeatures.ColumnFeatureContainer;
import com.mcecraft.worldgen.columnFeatures.impl.FlowerFeature;
import com.mcecraft.worldgen.columnFeatures.impl.GrassFeature;
import com.mcecraft.worldgen.columnFeatures.impl.TreeAdapter;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.features.impl.TreeFeature;
import net.minestom.worldgen.layers.Layer;

public class SnowyTundra extends NormalBiome {

	private static SnowyTundra INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:snowy_tundra")).build();
	private static final TreeFeature tree = new TreeFeature(0, Block.OAK_LEAVES, Block.OAK_LOG);

	public SnowyTundra(WorldGen wg) {
		super(wg, BIOME, Plate.COLD, Block.SNOW.getBlockId(), Block.GRASS_BLOCK.withProperties("snowy=true"), Block.DIRT.getBlockId(), 3, tree);
		setIceLakes(true);
		addColumnFeatures(new ColumnFeatureContainer(0.0003f, new TreeAdapter(tree)), new ColumnFeatureContainer(0.03, new GrassFeature()), new ColumnFeatureContainer(1, new FlowerFeature(345634, Block.DANDELION)), new ColumnFeatureContainer(1, new FlowerFeature(78421, Block.POPPY)));
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/16).setSeed(45789).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(2).setPersistence(0.5).setOctaves(4).build();

	public static SnowyTundra getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (65+getPlateNoise(x,z, 1) + noise1.getNoise(x, z)*2 + (!Layer.isLand(biomeId) ? -5 : 0));
	}

}
