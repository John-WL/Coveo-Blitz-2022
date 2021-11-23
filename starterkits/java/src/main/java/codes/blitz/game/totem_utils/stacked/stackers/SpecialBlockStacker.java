package codes.blitz.game.totem_utils.stacked.stackers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.StackedTotem;

import java.util.ArrayList;
import java.util.List;

public interface SpecialBlockStacker {
    Totem getType();
    CoordinatePair getEdgeStackOverhead();
    void build4SquaresBlockAt(List<TotemAnswer> totems, int xOffset, int yOffset, int amountToBuild);

    static StackedTotem stackBlocks(int amount, SpecialBlockStacker stacker) {
        final List<TotemAnswer> totems = new ArrayList<>(amount);
        final int amountOfSpecial4Squares = amount/4;
        final int squareSizeOutOfSpecial4Squares = (int) Math.sqrt(amountOfSpecial4Squares);
        final int iteratorSizeToCompleteSquareFirst = squareSizeOutOfSpecial4Squares * squareSizeOutOfSpecial4Squares;
        CoordinatePair edgeStackOverhead = stacker.getEdgeStackOverhead();
        CoordinatePair size = new CoordinatePair((squareSizeOutOfSpecial4Squares)*4 + edgeStackOverhead.x(), (squareSizeOutOfSpecial4Squares)*4 + edgeStackOverhead.y());

        // make the biggest square out of the blocks
        for(int i = 0; i < iteratorSizeToCompleteSquareFirst; i++) {    // 4x4 squares of totem
            final int xOffset = i % (squareSizeOutOfSpecial4Squares);
            final int yOffset = i / (squareSizeOutOfSpecial4Squares);
            stacker.build4SquaresBlockAt(totems, xOffset, yOffset, 4);
        }

        int remainingAmount = amount - iteratorSizeToCompleteSquareFirst*4;

        // make an upper line if we still have too many blocks
        if(remainingAmount/4 > squareSizeOutOfSpecial4Squares) {
            for(int i = 0; i < squareSizeOutOfSpecial4Squares; i++) {
                final int xOffset = i;
                final int yOffset = squareSizeOutOfSpecial4Squares;
                stacker.build4SquaresBlockAt(totems, xOffset, yOffset, 4);
            }
            remainingAmount -= squareSizeOutOfSpecial4Squares*4;
            size = new CoordinatePair(size.x(), size.y() + 4);
        }

        if (remainingAmount != 0) {
            if(size.y() <= 1) {
                size = new CoordinatePair(size.x(), size.y() + 4);
            }
            size = new CoordinatePair(size.x() + 4, size.y());
        }

        // setting the size to 0 if there's no block in there
        if(size.x() <= 1 && size.y() <= 1) {
            size = new CoordinatePair(0, 0);
        }

        // make a right-most line of blocks
        for(int i = 0; i < remainingAmount/4; i++) {
            final int xOffset = squareSizeOutOfSpecial4Squares;
            final int yOffset = i;
            stacker.build4SquaresBlockAt(totems, xOffset, yOffset, 4);
        }

        // place the final remaining blocks
        stacker.build4SquaresBlockAt(totems, squareSizeOutOfSpecial4Squares, remainingAmount/4, (remainingAmount%4));

        return new StackedTotem(totems, stacker.getType(), size);
    }
}
