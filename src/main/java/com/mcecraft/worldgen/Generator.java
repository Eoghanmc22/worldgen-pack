package com.mcecraft.worldgen;

import net.minestom.server.instance.ChunkGenerator;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.worldgen.WorldGen;

public class Generator {

	private final WorldGen wg;

	public Generator(InstanceContainer instance) {
		this.wg = new WorldGen(instance, GenConfig.INSTANCE);
	}

	public WorldGen getWorldGen() {
		return wg;
	}

	public ChunkGenerator getChunkGenerator() {
		return wg.getChunkGenerator();
	}
}
