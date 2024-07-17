package uk.debb.bat_uwu.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.bat_uwu.BatUWU;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    @Inject(method = "createLevels", at = @At("RETURN"))
    private void vanillaDisable$createLevels(CallbackInfo ci) {
        BatUWU.server = (MinecraftServer) (Object) this;
    }
}
