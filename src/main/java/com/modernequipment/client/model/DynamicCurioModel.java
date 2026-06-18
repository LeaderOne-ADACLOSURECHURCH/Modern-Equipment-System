package com.modernequipment.client.model;

import com.modernequipment.MESMod;
import com.modernequipment.core.item.EquipmentItem;
import com.modernequipment.util.MESDebugLogger;
import com.modernequipment.util.ResourceValidator;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DynamicCurioModel extends GeoModel<EquipmentItem> {

    @Override
    public ResourceLocation getModelResource(EquipmentItem animatable) {
        var render = animatable.getData().getRender();
        if (render != null && render.getGeo() != null) {
            String geoPath = render.getGeo();
            if (!geoPath.contains(":")) {
                geoPath = "modernequipment:" + geoPath;
            }

            // 检查 Geo 模型资源是否存在
            if (!ResourceValidator.geoExists(geoPath)) {
                MESDebugLogger.info(MESMod.LOGGER, "DynamicCurioModel.getModelResource - Geo not found, using default: {}", geoPath);
                return ResourceValidator.getDefaultGeo();
            }

            ResourceLocation result = new ResourceLocation(geoPath);
            MESDebugLogger.info(MESMod.LOGGER, "DynamicCurioModel.getModelResource - geoPath: {}, result: {}", geoPath, result);
            return result;
        }
        return new ResourceLocation("modernequipment", "geo/armor/default.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EquipmentItem animatable) {
        var render = animatable.getData().getRender();
        if (render != null && render.getTexture() != null) {
            String texPath = render.getTexture();
            if (!texPath.contains(":")) {
                texPath = "modernequipment:" + texPath;
            }

            // 检查贴图资源是否存在
            if (!ResourceValidator.textureExists(texPath)) {
                MESDebugLogger.info(MESMod.LOGGER, "DynamicCurioModel.getTextureResource - Texture not found, using default: {}", texPath);
                return ResourceValidator.getDefaultTexture();
            }

            ResourceLocation result = new ResourceLocation(texPath);
            MESDebugLogger.info(MESMod.LOGGER, "DynamicCurioModel.getTextureResource - texPath: {}, result: {}", texPath, result);
            return result;
        }
        return new ResourceLocation("modernequipment", "textures/armor/default.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EquipmentItem animatable) {
        var render = animatable.getData().getRender();
        if (render != null && render.getAnimation() != null) {
            String animPath = render.getAnimation();
            if (!animPath.contains(":")) {
                animPath = "modernequipment:" + animPath;
            }

            // 检查动画资源是否存在，如果不存在则返回 null（不使用动画）
            if (!ResourceValidator.animationExists(animPath)) {
                MESDebugLogger.info(MESMod.LOGGER, "DynamicCurioModel.getAnimationResource - Animation not found, skipping: {}", animPath);
                return null;
            }

            return new ResourceLocation(animPath);
        }
        return null;
    }
}