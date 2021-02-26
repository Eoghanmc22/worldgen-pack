package com.mcecraft.worldgen.biomes.impl;

import com.mcecraft.worldgen.biomes.NormalBiome;
import com.mcecraft.worldgen.plates.Plate;
import de.articdive.jnoise.JNoise;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.worldgen.WorldGen;

public class Ocean extends NormalBiome {

	private static Ocean INSTANCE;

	private static final Biome BIOME = Biome.builder().name(NamespaceID.from("wgp:desert")).build();

	public Ocean(WorldGen wg) {
		super(wg, BIOME, Plate.OCEAN, Block.AIR.getBlockId(), Block.GRAVEL.getBlockId(), 3);
		INSTANCE = this;
	}

	public static final JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/16).setSeed(789268).build();

	public static final JNoise noise1 = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(2).setPersistence(0.5).setOctaves(4).build();

	public static Ocean getInstance() {
		return INSTANCE;
	}

	@Override
	public int getHeight(int x, int z, int biomeId) {
		return (int) (50+getPlateNoise(x,z, 1) + noise1.getNoise(x, z)*3);
	}

}
