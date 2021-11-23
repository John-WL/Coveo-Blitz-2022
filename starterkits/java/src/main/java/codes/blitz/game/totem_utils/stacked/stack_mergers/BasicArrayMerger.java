package codes.blitz.game.totem_utils.stacked.stack_mergers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.StackedTotem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasicArrayMerger implements TotemStackMerger {

    @Override
    public List<TotemAnswer> merge(List<StackedTotem> stackedTotems) {
        // finding the biggest size
        CoordinatePair biggestSize = new CoordinatePair(-1, -1);
        for(StackedTotem stackedTotem: stackedTotems) {
            if (stackedTotem.size().x() > biggestSize.x()) {
                biggestSize = new CoordinatePair(stackedTotem.size().x(), biggestSize.y());
            }
            if (stackedTotem.size().y() > biggestSize.y()) {
                biggestSize = new CoordinatePair(biggestSize.x(), stackedTotem.size().y());
            }
        }
        final CoordinatePair finalBiggestSize = biggestSize;

        final List<TotemAnswer> totemAnswers = new ArrayList<>();

        totemAnswers.addAll(stackedTotems.get(0).totemList());

        totemAnswers.addAll(stackedTotems.get(1).totemList().stream()
                .map(totemAnswer -> new TotemAnswer(totemAnswer.shape(), totemAnswer.coordinates().stream()
                        .map(coordinatePair -> new CoordinatePair(
                                coordinatePair.x() + finalBiggestSize.x(),
                                coordinatePair.y()))
                        .toList())).collect(Collectors.toList()));

        totemAnswers.addAll(stackedTotems.get(2).totemList().stream()
                .map(totemAnswer -> new TotemAnswer(totemAnswer.shape(), totemAnswer.coordinates().stream()
                        .map(coordinatePair -> new CoordinatePair(
                                coordinatePair.x() + 2*finalBiggestSize.x(),
                                coordinatePair.y()))
                        .toList())).collect(Collectors.toList()));

        totemAnswers.addAll(stackedTotems.get(3).totemList().stream()
                .map(totemAnswer -> new TotemAnswer(totemAnswer.shape(), totemAnswer.coordinates().stream()
                        .map(coordinatePair -> new CoordinatePair(
                                coordinatePair.x(),
                                coordinatePair.y() + finalBiggestSize.y()))
                        .toList())).collect(Collectors.toList()));

        totemAnswers.addAll(stackedTotems.get(4).totemList().stream()
                .map(totemAnswer -> new TotemAnswer(totemAnswer.shape(), totemAnswer.coordinates().stream()
                        .map(coordinatePair -> new CoordinatePair(
                                coordinatePair.x() + finalBiggestSize.x(),
                                coordinatePair.y() + finalBiggestSize.y()))
                        .toList())).collect(Collectors.toList()));

        totemAnswers.addAll(stackedTotems.get(5).totemList().stream()
                .map(totemAnswer -> new TotemAnswer(totemAnswer.shape(), totemAnswer.coordinates().stream()
                        .map(coordinatePair -> new CoordinatePair(
                                coordinatePair.x() + 2*finalBiggestSize.x(),
                                coordinatePair.y() + finalBiggestSize.y()))
                        .toList())).collect(Collectors.toList()));

        totemAnswers.addAll(stackedTotems.get(6).totemList().stream()
                .map(totemAnswer -> new TotemAnswer(totemAnswer.shape(), totemAnswer.coordinates().stream()
                        .map(coordinatePair -> new CoordinatePair(
                                coordinatePair.x(),
                                coordinatePair.y() + 2*finalBiggestSize.y()))
                        .toList())).collect(Collectors.toList()));

        return totemAnswers;
    }
}
