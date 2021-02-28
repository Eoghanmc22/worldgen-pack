package com.mcecraft.worldgen.layers.impl.mountains;

import com.mcecraft.worldgen.biomes.impl.mountains.Mountains;
import com.mcecraft.worldgen.biomes.impl.mountains.MountainsSteep;
import com.mcecraft.worldgen.biomes.impl.mountains.TallMountains;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgen.layers.MultiSamplingLayer;

public class MountainsTallLayer extends MultiSamplingLayer {

    public MountainsTallLayer(long baseSeed) {
        super(baseSeed);
    }

    @Override
    protected int genBiomes(int x, int z, int u, int d, int r, int l, int c, ChunkRandom rng) {
        rng.initChunkSeed(x, z);
        int climateM = Mountains.getInstance().getClimateId();
        int biomeM = Mountains.getInstance().getBiomeId();
        if (!Layer.cmpBiomeClimate(c, climateM, biomeM)) {
            return c;
        }
        return Layer.setIsLand(0, true) | Layer.setClimate(0, TallMountains.getInstance().getClimateId()) | Layer.setBiomeId(0, TallMountains.getInstance().getBiomeId());
    }
}
