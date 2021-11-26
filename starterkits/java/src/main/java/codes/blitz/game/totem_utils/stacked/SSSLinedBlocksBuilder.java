package codes.blitz.game.totem_utils.stacked;

import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.SSSLineStacker;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.SpecialBlockStacker;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.ZZZLineStacker;

import java.util.ArrayList;
import java.util.List;

public record SSSLinedBlocksBuilder() {

    public List<StackedTotem> build(final int playFieldWidth, final int amountOfS, final int amountOfJ) {
        final List<StackedTotem> stackedTotems = new ArrayList<>();

        // some field info
        int maxAmountOfSPerLines = (playFieldWidth/4) * 4 - 2;
        int maxAmountOfJPerLine = 2;
        int amountOfFullLines = Math.min(amountOfS/maxAmountOfSPerLines, amountOfJ/maxAmountOfJPerLine);

        // build every single full line you can
        for(int i = 0; i < amountOfFullLines; i++) {
            final List<TotemAnswer> lineOfS = new ArrayList<>();
            final SpecialBlockStacker sssStacker = new SSSLineStacker((maxAmountOfSPerLines+2)/4);
            sssStacker.build4SquaresBlockAt(lineOfS, 0, 0, 1);
            stackedTotems.add(new StackedTotem(lineOfS, sssStacker.getEdgeStackOverhead()));
        }

        // build the last line
        int remainingBlocksForS = (amountOfS - (amountOfFullLines * maxAmountOfSPerLines) + 2) / 4;
        if(remainingBlocksForS > 0 && amountOfJ - amountOfFullLines*2 >= 2) {
            final List<TotemAnswer> lastLineOfS = new ArrayList<>();
            final SpecialBlockStacker sssStacker = new SSSLineStacker(remainingBlocksForS);
            sssStacker.build4SquaresBlockAt(lastLineOfS, 0, 0, 1);
            stackedTotems.add(new StackedTotem(lastLineOfS, sssStacker.getEdgeStackOverhead()));
        }

        return stackedTotems;
    }
}
