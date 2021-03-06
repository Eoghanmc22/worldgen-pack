package com.mcecraft.worldgen.biomes.impl.mountains;

import com.mcecraft.worldgen.biomes.SurfacedBiome;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;

public class MountainsEdge extends SurfacedBiome {

	private static MountainsEdge INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:mountains")).build();

	public MountainsEdge(WorldGen wg) {
		super(wg, BIOME, Plate.MOUNTAINS, new MountainsSurfaceBuilder(), 5);
		addColumnFeatures();
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/128).setSeed(789236).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(3).setPersistence(0.5).setOctaves(4).build();

	public static MountainsEdge getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (80+(getPlateNoise(x,z, 6)) + noise1.getNoise(x, z)*20);
	}

}
