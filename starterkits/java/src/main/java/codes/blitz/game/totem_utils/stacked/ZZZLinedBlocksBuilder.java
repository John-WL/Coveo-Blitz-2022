package codes.blitz.game.totem_utils.stacked;

import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.*;

import java.util.ArrayList;
import java.util.List;

public record ZZZLinedBlocksBuilder() {

    public List<StackedTotem> build(final int playFieldWidth, final int amountOfZ, final int amountOfL) {
        final List<StackedTotem> stackedTotems = new ArrayList<>();

        // some field info
        int maxAmountOfZPerLines = (playFieldWidth/4) * 4 - 2;
        int maxAmountOfLPerLine = 2;
        int amountOfFullLines = Math.min(amountOfZ/maxAmountOfZPerLines, amountOfL/maxAmountOfLPerLine);

        // build every single full line you can
        for(int i = 0; i < amountOfFullLines; i++) {
            final List<TotemAnswer> lineOfZ = new ArrayList<>();
            final SpecialBlockStacker zzzStacker = new ZZZLineStacker((maxAmountOfZPerLines+2)/4);
            zzzStacker.build4SquaresBlockAt(lineOfZ, 0, 0, 1);
            stackedTotems.add(new StackedTotem(lineOfZ, zzzStacker.getEdgeStackOverhead()));
        }

        // build the last line
        int remainingBlocksForZ = (amountOfZ - (amountOfFullLines * maxAmountOfZPerLines) + 2) / 4;
        if(remainingBlocksForZ > 0 && amountOfL - amountOfFullLines*2 >= 2) {
            final List<TotemAnswer> lastLineOfZ = new ArrayList<>();
            final SpecialBlockStacker zzzStacker = new ZZZLineStacker(remainingBlocksForZ);
            zzzStacker.build4SquaresBlockAt(lastLineOfZ, 0, 0, 1);
            stackedTotems.add(new StackedTotem(lastLineOfZ, zzzStacker.getEdgeStackOverhead()));
        }

        return stackedTotems;
    }
}
