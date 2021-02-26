package com.mcecraft.worldgen.biomes.impl.mountains;

import com.mcecraft.worldgen.biomes.NormalBiome;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;

public class SmallMountains extends NormalBiome {

	private static SmallMountains INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:small_mountains")).build();

	public SmallMountains(WorldGen wg) {
		super(wg, BIOME, Plate.MOUNTAINS, Block.SNOW.getBlockId(), 110, Block.STONE.getBlockId(), Block.STONE.getBlockId(), 3);
		addColumnFeatures();
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/1024).setSeed(789236).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(3).setPersistence(0.7).setOctaves(5).build();

	public static SmallMountains getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (90+Math.abs(getPlateNoise(x,z, 3)) + Math.abs(noise1.getNoise(x, z)*100));
	}

}
