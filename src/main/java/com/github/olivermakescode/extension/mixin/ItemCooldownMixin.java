package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.extension;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemCooldownManager.class)
public class ItemCooldownMixin {
    @Inject(method="set", at=@At("HEAD"), cancellable = true)
    public void cooldownGamerule(Item item, int duration, CallbackInfo ci) {
        boolean itemCool = !extension.itemCooldown.getValue();
        boolean ePearl = !extension.ePearlEnable.getValue() && item instanceof EnderPearlItem;
        boolean chorus = !extension.chorusEnable.getValue() && item instanceof ChorusFruitItem;
        boolean shield = !extension.shieldEnable.getValue() && item instanceof ShieldItem;

        if (itemCool || ePearl || chorus || shield)
            ci.cancel();
    }
}
