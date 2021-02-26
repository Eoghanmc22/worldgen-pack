package com.mcecraft.worldgen.biomes.impl.mountains;

import com.mcecraft.worldgen.biomes.NormalBiome;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;

public class MountainGravelValleys extends NormalBiome {

	private static MountainGravelValleys INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:mountain_gravel_vallies")).build();

	public MountainGravelValleys(WorldGen wg) {
		super(wg, BIOME, Plate.MOUNTAINS, Block.SNOW.getBlockId(), 120, Block.GRAVEL.getBlockId(), Block.GRAVEL.getBlockId(), 3);
		addColumnFeatures();
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/64).setSeed(789236).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(3).setPersistence(0.4).setOctaves(3).build();

	public static MountainGravelValleys getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (75+getPlateNoise(x,z, 4) + noise1.getNoise(x, z)*4);
	}

}
