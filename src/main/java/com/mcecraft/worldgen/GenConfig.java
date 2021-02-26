package com.mcecraft.worldgen;

import com.mcecraft.worldgen.biomes.impl.*;
import com.mcecraft.worldgen.layers.impl.BeachLayer;
import com.mcecraft.worldgen.layers.impl.LakeLayer;
import com.mcecraft.worldgen.layers.impl.ShelfLayer;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.WorldGenConfig;
import net.minestom.worldgen.biomes.BiomeGroup;
import net.minestom.worldgen.layers.impls.*;

public class GenConfig implements WorldGenConfig {

	public static final GenConfig INSTANCE = new GenConfig();

	private GenConfig() {}

	@Override
	public void addLayers(WorldGen wg) {
		int i = 2000;
		wg.addLayer(new BaseLayer());
		wg.addLayer(new LandLayer(i++, 1f));
		wg.addLayer(new BiomeGroupLayer(i++));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new SmoothLayer(i++));
		wg.addLayer(new BiomeLayer(i++));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new LakeLayer(i++, 32));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new LakeLayer(i++, 32));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new LakeLayer(i++, 32));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new BeachLayer(i++));
		wg.addLayer(new ShelfLayer(i++));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new ZoomLayer(i++));
	}

	@Override
	public void addBiomes(WorldGen wg) {
		BiomeGroup group = new BiomeGroup();
		group.addBiome(new Forest(wg));
		group.addBiome(new OakForest(wg));
		group.addBiome(new BirchForest(wg));
		group.addBiome(new Plains(wg));
		wg.addBiomeGroup(group);

		group = new BiomeGroup();
		group.addBiome(new Desert(wg));
		wg.addBiomeGroup(group);

		group = new BiomeGroup();
		group.addBiome(new Ocean(wg));
		wg.addBiomeGroup(group);

		group = new BiomeGroup();
		group.addBiome(new SnowyTundra(wg));
		wg.addBiomeGroup(group);

		wg.addReservedBiome(new Beach(wg));
		wg.addReservedBiome(new ShelfBiome(wg));
	}

}
