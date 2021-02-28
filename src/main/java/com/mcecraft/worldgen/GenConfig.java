package com.mcecraft.worldgen;

import com.mcecraft.worldgen.biomes.impl.*;
import com.mcecraft.worldgen.biomes.impl.cold.SnowyTundra;
import com.mcecraft.worldgen.biomes.impl.dry.Desert;
import com.mcecraft.worldgen.biomes.impl.mountains.*;
import com.mcecraft.worldgen.biomes.impl.ocean.Ocean;
import com.mcecraft.worldgen.biomes.impl.temperate.*;
import com.mcecraft.worldgen.layers.impl.*;
import com.mcecraft.worldgen.layers.impl.mountains.*;
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
		wg.addLayer(new MountainValleysLayer(i++, 5));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new LakeLayer(i++, 32));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new LakeLayer(i++, 32));
		wg.addLayer(new MountainsEdgeLayer(i++));
		wg.addLayer(new MountainsOuterLayer(i++));
		wg.addLayer(new MountainsInnerLayer(i++));
		wg.addLayer(new MountainsTallLayer(i++));
		wg.addLayer(new MountainsVeryTallLayer(i++, 6));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new LakeLayer(i++, 32));
		wg.addLayer(new ShelfLayer(i++));
		wg.addLayer(new ZoomLayer(i++));
		wg.addLayer(new MountainsPeakLayer(i++, 4));
		wg.addLayer(new BeachLayer(i++));
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

		group = new BiomeGroup();
		group.addBiome(new Mountains(wg));
		wg.addBiomeGroup(group);

		wg.addReservedBiome(new Beach(wg));
		wg.addReservedBiome(new ShelfBiome(wg));
		wg.addReservedBiome(new MountainValleys(wg));
		wg.addReservedBiome(new MountainGravelValleys(wg));
		wg.addReservedBiome(new MountainsEdge(wg));
		wg.addReservedBiome(new TallMountains(wg));
		wg.addReservedBiome(new OuterMountains(wg));
		wg.addReservedBiome(new MountainsSteep(wg));
		wg.addReservedBiome(new PeakMountains(wg));
		wg.addReservedBiome(new VeryTallMountains(wg));
	}

}
