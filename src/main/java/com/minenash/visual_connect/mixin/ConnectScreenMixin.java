package com.minenash.visual_connect.mixin;

import com.minenash.visual_connect.ScreenshotLoader;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ConnectScreen;renderBackground(Lnet/minecraft/client/util/math/MatrixStack;)V"))
	public void render(ConnectScreen screen, MatrixStack stack) {
		screen.renderBackground(stack);
		ScreenshotLoader.render(screen,stack);
	}

	@Inject(method = "connect", at = @At("HEAD"), cancellable = true)
	public void getImage(String address, int port, CallbackInfo info) {
		ScreenshotLoader.setScreenshot(address, port);
	}
}
