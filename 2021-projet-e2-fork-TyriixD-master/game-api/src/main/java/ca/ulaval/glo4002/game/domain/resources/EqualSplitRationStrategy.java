package ca.ulaval.glo4002.game.domain.resources;

import java.io.Serializable;
import java.util.List;

public class EqualSplitRationStrategy implements RationStrategy, Serializable {
    @Override
    public LunchBox prepareLunchBox(ResourcesNeeds order, List<Resources> freshResources,
                                    List<Resources> consumedResources) {
        int maxWaterQuantityPerDiet = calculateMaximumWaterQuantityPerDiet(freshResources);
        int carnivoreWaterQuantity = Math.min(order.waterForCarnivoresQuantity, maxWaterQuantityPerDiet);
        int herbivoreWaterQuantity = Math.min(order.waterForHerbivoresQuantity, maxWaterQuantityPerDiet);

        int burgerQuantity = extractConsumableResources(ResourceInfo.BURGER.type, order.burgerQuantity, freshResources,
                                                        consumedResources);
        int saladQuantity = extractConsumableResources(ResourceInfo.SALAD.type, order.saladQuantity, freshResources,
                                                       consumedResources);
        int waterForCarnivoresQuantity = extractConsumableResources(ResourceInfo.WATER.type, carnivoreWaterQuantity,
                                                                    freshResources, consumedResources);
        int waterForHerbivoresQuantity = extractConsumableResources(ResourceInfo.WATER.type, herbivoreWaterQuantity,
                                                                    freshResources, consumedResources);

        return new LunchBox(burgerQuantity, saladQuantity, waterForCarnivoresQuantity, waterForHerbivoresQuantity);
    }

    private int calculateMaximumWaterQuantityPerDiet(List<Resources> freshResources) {
        int totalWaterQuantity = 0;

        for (Resources resources : freshResources) {
            if (resources.getInfo().equals(ResourceInfo.WATER)) {
                totalWaterQuantity += resources.getQuantity();
            }
        }

        return totalWaterQuantity / 2;
    }

    private int extractConsumableResources(String type, int quantity, List<Resources> freshResources,
                                           List<Resources> consumedResources) {
        int totalQuantityExtracted = 0;
        int quantityExtracted = 0;
        int targetIndex = 0;

        while ((freshResources.size() - 1) >= targetIndex && totalQuantityExtracted < quantity) {
            Resources resources = freshResources.get(targetIndex);

            if (!resources.getInfo().type.equals(type)) {
                targetIndex++;
                continue;
            }

            quantityExtracted = resources.consume(quantity - totalQuantityExtracted);
            totalQuantityExtracted += quantityExtracted;

            consumedResources.add(new Resources(resources.getInfo(), ResourceState.CONSUMED, quantityExtracted));

            if (resources.isEmpty()) {
                freshResources.remove(resources);
            }
        }

        return totalQuantityExtracted;
    }
}
