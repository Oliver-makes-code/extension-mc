package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.nicknames;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class NickMessageMixin {

    @Inject(method="getName()Lnet/minecraft/text/Text;", at=@At("INVOKE"),cancellable = true)
    public void nickName(CallbackInfoReturnable<Text> cir) {
        PlayerEntity self = (PlayerEntity) (Object) this;
        cir.setReturnValue(Text.of(nicknames.getName(self)));
    }
}
