package com.anthonyhilyard.equipmentcompare.mixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

public class CuriosMixinConfig implements IMixinConfigPlugin
{
	private LoadingModList loadingModList = null;

	@Override
	public void onLoad(String mixinPackage) { }

	@Override
	public String getRefMapperConfig() { return null; }

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
	{
		if (mixinClassName.toLowerCase().contains("curios"))
		{
			if (loadingModList == null)
			{
				loadingModList = FMLLoader.getLoadingModList();
			}

			// Check if Curios is available.  Not sure if there's a better way to do this at such an early stage...
			for (ModInfo modInfo : loadingModList.getMods())
			{
				if (modInfo.getModId().equals("curios"))
				{
					return true;
				}
			}
			
			return false;
		}
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }

	@Override
	public List<String> getMixins() { return null; }

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
}
