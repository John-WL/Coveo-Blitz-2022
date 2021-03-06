package codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.StackedTotem;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.mixed.*;

import java.util.ArrayList;
import java.util.List;

public interface SpecialBlockStacker {

    Totem getType();
    CoordinatePair getEdgeStackOverhead();
    void build4SquaresBlockAt(List<TotemAnswer> totems, int xOffset, int yOffset, int amountToBuild);

    static void addWeirdBlock(List<StackedTotem> listToAddTo, SpecialBlockStacker block) {
        final List<TotemAnswer> totemAnswers = new ArrayList<>();
        block.build4SquaresBlockAt(totemAnswers, 0, 0, 1);
        listToAddTo.add(new StackedTotem(totemAnswers, block.getEdgeStackOverhead()));
    }

    static StackedTotem getWeirdBlock(SpecialBlockStacker block) {
        final List<TotemAnswer> totemAnswers = new ArrayList<>();
        block.build4SquaresBlockAt(totemAnswers, 0, 0, 1);
        return new StackedTotem(totemAnswers, block.getEdgeStackOverhead());
    }

    static List<StackedTotem> generateEveryAvailableWeirdBlocks() {
        final List<StackedTotem> stackedTotems = new ArrayList<>();
        addWeirdBlock(stackedTotems, new J2I2Stacker());
        addWeirdBlock(stackedTotems, new JOLIStacker());
        addWeirdBlock(stackedTotems, new L2I2Stacker());
        addWeirdBlock(stackedTotems, new L2J2Stacker());
        addWeirdBlock(stackedTotems, new O2I2Stacker());
        addWeirdBlock(stackedTotems, new O2J2Stacker());
        addWeirdBlock(stackedTotems, new O2L2Stacker());
        addWeirdBlock(stackedTotems, new S2J2Stacker());
        addWeirdBlock(stackedTotems, new SL2IStacker());
        addWeirdBlock(stackedTotems, new ST2JStacker());
        addWeirdBlock(stackedTotems, new T2JIStacker());
        addWeirdBlock(stackedTotems, new T2LIStacker());
        addWeirdBlock(stackedTotems, new Z2L2Stacker());
        addWeirdBlock(stackedTotems, new ZJ2IStacker());
        addWeirdBlock(stackedTotems, new ZT2LStacker());
        return stackedTotems;
    }

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

        return new StackedTotem(totems, stacker.getType(), size, iteratorSizeToCompleteSquareFirst);
    }

    static List<StackedTotem> generateSpecial4By4BlockList(int amount, SpecialBlockStacker stacker) {
        final List<StackedTotem> stackedTotems = new ArrayList<>();

        for(int i = 0; i < amount; i++) {
            final List<TotemAnswer> totems = new ArrayList<>();
            stacker.build4SquaresBlockAt(totems, 0, 0, 4);
            final StackedTotem special4By4Square = new StackedTotem(totems, stacker.getType(), new CoordinatePair(4, 4), 1);
            stackedTotems.add(special4By4Square);
        }

        return stackedTotems;
    }
}
