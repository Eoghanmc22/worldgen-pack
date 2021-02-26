package com.mcecraft.worldgen.plates;

import de.articdive.jnoise.JNoise;

public enum Plate {
	COLD(0), TEMPERATE(1), LUSH(2), DRY(3), OCEAN(4);

	private final JNoise noise;

	Plate(int seed) {
		JNoise base = JNoise.newBuilder().superSimplex().setFrequency(1.0/16).setSeed(634568 + seed).build();

		this.noise = JNoise.newBuilder().octavated().setNoise(base).setLacunarity(2).setPersistence(0.5).setOctaves(4).build();
	}

	public JNoise getNoise() {
		return noise;
	}
}
