package com.mcecraft.worldgen.biomes.impl.mountains;

import com.mcecraft.worldgen.biomes.SurfacedBiome;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;
import net.minestom.worldgen.layers.Layer;

public class DynamicMountains extends SurfacedBiome {

	private static DynamicMountains INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:mountains")).build();

	public DynamicMountains(WorldGen wg) {
		super(wg, BIOME, Plate.MOUNTAINS, new MountainsSurfaceBuilder(), 5);
		addColumnFeatures();
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/128).setSeed(789236).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(3).setPersistence(0.5).setOctaves(4).build();

	public static DynamicMountains getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		final int height = Layer.getBiomeData(biomeId);
		return (int) (height+(getPlateNoise(x,z, 0)) + noise1.getNoise(x, z)*32);
	}

}
