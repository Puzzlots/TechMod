package org.example.exmod.mixins;

import finalforeach.cosmicreach.gamestates.MainMenu;
import finalforeach.cosmicreach.ui.UIElement;
import org.example.exmod.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenu.class)
public class MainMenuMixin {

    @Inject(method = "create", at = @At("HEAD"))
    private void create0(CallbackInfo ci) {
        Constants.LOGGER.info("THE START OF THE MAIN MENU's create()");
    }

    @Inject(method = "create", at = @At("TAIL"))
    private void create1(CallbackInfo ci) {
        Constants.LOGGER.info("THE END OF THE MAIN MENU's create()");
    }

    @Redirect(method = "create", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/ui/UIElement;setText(Ljava/lang/String;)V", ordinal = 0))
    private void setText(UIElement startButton, String text) {
        startButton.setText("Better Button?");
    }


}
