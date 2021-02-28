package com.mcecraft.worldgen.layers.impl.mountains;

import com.mcecraft.worldgen.biomes.impl.mountains.PeakMountains;
import com.mcecraft.worldgen.biomes.impl.mountains.TallMountains;
import com.mcecraft.worldgen.biomes.impl.mountains.VeryTallMountains;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgen.layers.MultiSamplingLayer;

public class MountainsPeakLayer extends MultiSamplingLayer {

    private final int chance;

    public MountainsPeakLayer(long baseSeed, int chance) {
        super(baseSeed);
        this.chance = chance;
    }

    @Override
    protected int genBiomes(int x, int z, int u, int d, int r, int l, int c, ChunkRandom rng) {
        rng.initChunkSeed(x, z);
        int climateM = VeryTallMountains.getInstance().getClimateId();
        int biomeM = VeryTallMountains.getInstance().getBiomeId();
        if (!Layer.cmpBiomeClimate(c, climateM, biomeM)) {
            return c;
        }
        if (rng.nextInt(chance) == 0) {
            return Layer.setIsLand(0, true) | Layer.setClimate(0, PeakMountains.getInstance().getClimateId()) | Layer.setBiomeId(0, PeakMountains.getInstance().getBiomeId());
        }
        return c;
    }

}
