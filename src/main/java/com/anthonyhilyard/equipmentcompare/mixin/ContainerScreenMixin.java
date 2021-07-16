package com.anthonyhilyard.equipmentcompare.mixin;

import javax.annotation.Nullable;

import com.anthonyhilyard.equipmentcompare.gui.ComparisonTooltips;
import com.mojang.blaze3d.matrix.MatrixStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.text.ITextComponent;

@Mixin(ContainerScreen.class)
public class ContainerScreenMixin extends Screen
{
	protected ContainerScreenMixin(ITextComponent titleIn) { super(titleIn); }

	@Shadow
	@Nullable
	protected Slot hoveredSlot;

	@Inject(method = "renderHoveredTooltip(Lcom/mojang/blaze3d/matrix/MatrixStack;II)V", at = @At(value  = "HEAD"), cancellable = true)
	public void renderHoveredTooltip(MatrixStack matrixStack, int x, int y, CallbackInfo info)
	{
		// If the comparison tooltips were displayed, cancel so the default functionality is not run.
		if (ComparisonTooltips.render(matrixStack, x, y, hoveredSlot, minecraft, font, this))
		{
			info.cancel();
		}
	}
}