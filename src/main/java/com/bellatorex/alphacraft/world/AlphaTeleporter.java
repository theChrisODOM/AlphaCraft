package com.bellatorex.alphacraft.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class AlphaTeleporter implements ITeleporter {

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity)entity;
            double d0 = player.getPosX();
            double d1 = player.getPosY();
            double d2 = player.getPosZ();
            float f = player.rotationPitch;
            float f1 = player.rotationYaw;
            currentWorld.getProfiler().startSection("moving");

            player.setLocationAndAngles(d0, d1, d2, f1, f);

            currentWorld.getProfiler().endSection();
            currentWorld.getProfiler().startSection("placing");

            if(entity.world.func_234923_W_() == World.field_234918_g_){
                player.setLocationAndAngles(0, 162, 48, f1, f);
            }else{
                BlockPos player_spawn = player.func_241140_K_();
                if (player_spawn == null){
                    player.setLocationAndAngles(destWorld.getWorldInfo().getSpawnX(), destWorld.getWorldInfo().getSpawnY(), destWorld.getWorldInfo().getSpawnZ(), f1, f);
                }else{
                    player.setLocationAndAngles(player_spawn.getX(), player_spawn.getY(), player_spawn.getZ(), f1, f);
                }
            }

            currentWorld.getProfiler().endSection();

            player.setWorld(destWorld);
            destWorld.addDuringPortalTeleport(player);

            player.connection.setPlayerLocation(player.getPosX(), player.getPosY(), player.getPosZ(), f1, f);

            return player;
        }
        else {

            Vector3d vec3d1 = entity.getLastPortalVec();


            entity.world.getProfiler().endStartSection("reloading");
            Entity newEntity = entity.getType().create(destWorld);
            if (newEntity != null) {
                newEntity.copyDataFromOld(entity);
                if(entity.world.func_234923_W_() == World.field_234918_g_) {
                    newEntity.moveToBlockPosAndAngles(new BlockPos(0, 162,48), newEntity.rotationYaw, newEntity.rotationPitch);
                }else{
                    newEntity.moveToBlockPosAndAngles(new BlockPos(destWorld.getWorldInfo().getSpawnX(), destWorld.getWorldInfo().getSpawnY(), destWorld.getWorldInfo().getSpawnZ()), newEntity.rotationYaw, newEntity.rotationPitch);
                }
                destWorld.addFromAnotherDimension(newEntity);
            }
            return newEntity;
        }
    }
}
