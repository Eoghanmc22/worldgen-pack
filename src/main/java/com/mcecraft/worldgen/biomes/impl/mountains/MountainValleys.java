package com.mcecraft.worldgen.biomes.impl.mountains;

import com.mcecraft.worldgen.biomes.NormalBiome;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;

public class MountainValleys extends NormalBiome {

	private static MountainValleys INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:mountain_vallies")).build();

	public MountainValleys(WorldGen wg) {
		super(wg, BIOME, Plate.MOUNTAINS, Block.SNOW.getBlockId(), 120, Block.GRASS_BLOCK.getBlockId(), Block.DIRT.getBlockId(), 3);
		addColumnFeatures();
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/64).setSeed(789236).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(3).setPersistence(0.4).setOctaves(3).build();

	public static MountainValleys getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (75+getPlateNoise(x,z, 4) + noise1.getNoise(x, z)*4);
	}

}
