package codes.blitz.game.totem_utils.stacked;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;

import java.util.List;

public record StackedTotem(List<TotemAnswer> totemList, Totem type, CoordinatePair size) {
}
