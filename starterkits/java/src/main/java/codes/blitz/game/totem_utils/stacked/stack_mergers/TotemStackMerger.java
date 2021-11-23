package codes.blitz.game.totem_utils.stacked.stack_mergers;

import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.StackedTotem;

import java.util.List;

public interface TotemStackMerger {

    List<TotemAnswer> merge(List<StackedTotem> stackedTotems);
}
