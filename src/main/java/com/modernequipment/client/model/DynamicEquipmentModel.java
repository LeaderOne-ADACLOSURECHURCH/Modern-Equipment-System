package com.modernequipment.client.model;

import com.modernequipment.MESMod;
import com.modernequipment.core.item.EquipmentArmorItem;
import com.modernequipment.util.ResourceValidator;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DynamicEquipmentModel extends GeoModel<EquipmentArmorItem> {
    @Override
    public ResourceLocation getModelResource(EquipmentArmorItem animatable) {
        var render = animatable.getData().getRender();
        if (render != null && render.getGeo() != null) {
            String geoPath = render.getGeo();
            if (!geoPath.contains(":")) {
                geoPath = "modernequipment:" + geoPath;
            }

            // 检查 Geo 模型资源是否存在
            if (!ResourceValidator.geoExists(geoPath)) {
                MESMod.LOGGER.debug("DynamicEquipmentModel.getModelResource - Geo not found, using default: {}", geoPath);
                return ResourceValidator.getDefaultGeo();
            }

            return new ResourceLocation(geoPath);
        }
        return new ResourceLocation("modernequipment", "geo/armor/default.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EquipmentArmorItem animatable) {
        var render = animatable.getData().getRender();
        if (render != null && render.getTexture() != null) {
            String texPath = render.getTexture();
            if (!texPath.contains(":")) {
                texPath = "modernequipment:" + texPath;
            }

            // 检查贴图资源是否存在
            if (!ResourceValidator.textureExists(texPath)) {
                MESMod.LOGGER.debug("DynamicEquipmentModel.getTextureResource - Texture not found, using default: {}", texPath);
                return ResourceValidator.getDefaultTexture();
            }

            return new ResourceLocation(texPath);
        }
        return new ResourceLocation("modernequipment", "textures/armor/default.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EquipmentArmorItem animatable) {
        var render = animatable.getData().getRender();
        if (render != null && render.getAnimation() != null) {
            String animPath = render.getAnimation();
            if (!animPath.contains(":")) {
                animPath = "modernequipment:" + animPath;
            }

            // 检查动画资源是否存在，如果不存在则返回 null（不使用动画）
            if (!ResourceValidator.animationExists(animPath)) {
                MESMod.LOGGER.debug("DynamicEquipmentModel.getAnimationResource - Animation not found, skipping: {}", animPath);
                return null;
            }

            return new ResourceLocation(animPath);
        }
        return null;
    }
}