package codes.blitz.game.solvers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.message.TotemQuestion;
import codes.blitz.game.totem_utils.linear_mix_stacked.AvailableBlocks;
import codes.blitz.game.totem_utils.linear_mix_stacked.SpecialBlock;
import codes.blitz.game.totem_utils.stacked.StackedTotem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinearSystemSolver implements CoveoSolver {

    @Override
    public List<TotemAnswer> solve(List<TotemQuestion> totemsToPlace) {
        final int amountOfL = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.L)).count();
        final int amountOfJ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.J)).count();
        final int amountOfI = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.I)).count();
        final int amountOfT = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.T)).count();
        final int amountOfS = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.S)).count();
        final int amountOfZ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.Z)).count();
        final int amountOfO = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.O)).count();

        // transform the block data into arrays
        final int[] b = new int[]{amountOfL, amountOfJ, amountOfI, amountOfT, amountOfS, amountOfZ, amountOfO};
        final int[][] c = Arrays.stream(AvailableBlocks.blocks)
                .map(specialBlock -> new int[]{
                        specialBlock.LCoeff(),
                        specialBlock.JCoeff(),
                        specialBlock.ICoeff(),
                        specialBlock.TCoeff(),
                        specialBlock.SCoeff(),
                        specialBlock.ZCoeff(),
                        specialBlock.OCoeff()})
                .toArray(int[][]::new);

        // solve the set of linear equations for whole integers only
        final int[] x = diophantineLinearSolve(c, b);

        // generate the found blocks
        final List<SpecialBlock> specialBlocks = new ArrayList<>();
        for(int i = 0; i < x.length; i++) {
            final int amountOfBlocksToGenerate = x[i];
            for(int j = 0; j < amountOfBlocksToGenerate; j++) {
                specialBlocks.add(AvailableBlocks.blocks[i]);
            }
        }

        // get the amount of 4x4 squares that they take
        final int totalAmountOf4By4SpecialBlocks = specialBlocks.stream()
                .map(specialBlock -> specialBlock.pattern().amountOfSpecial4Squares())
                .reduce(Integer::sum).get();

        // some basic play-field info
        final int playFieldWidth = (int)(Math.ceil(Math.sqrt(totalAmountOf4By4SpecialBlocks))+0.5);
        final int playFieldHeight = (int)(Math.ceil(totalAmountOf4By4SpecialBlocks/(double)playFieldWidth)+0.5);
        final int playFieldArea = playFieldWidth * playFieldHeight;

        // split the blocks into 3 categories: 4x8, 4x4 to fill the gap, and 4x4 to fill-in the rest of the play-field
        // 4x8
        final List<SpecialBlock> specialBlocks4By8 = specialBlocks.stream()
                .filter(specialBlock -> specialBlock.pattern().amountOfSpecial4Squares() == 2)
                .collect(Collectors.toList());
        // 4x4 for gap
        int xOffsetForGap = specialBlocks4By8.size()%playFieldWidth;
        int yOffsetForGap = (specialBlocks4By8.size()/playFieldWidth)*2;
        final List<SpecialBlock> specialBlocks4By4ForGap = specialBlocks.stream()
                .filter(specialBlock -> xOffsetForGap != 0)
                .filter(specialBlock -> specialBlock.pattern().amountOfSpecial4Squares() == 1)
                .collect(Collectors.toList());
        // 4x4 for the rest
        final List<SpecialBlock> specialRemainingBlocks4By4 = specialBlocks.stream()
                .filter(specialBlock -> !specialBlocks4By4ForGap.contains(specialBlock))
                .filter(specialBlock -> specialBlock.pattern().amountOfSpecial4Squares() == 1)
                .collect(Collectors.toList());

        // our resulting array
        final List<StackedTotem> stackedTotems = new ArrayList<>();

        // place the 4x8 blocks
        for(int i = 0; i < specialBlocks4By8.size(); i++) {
            final int xCoordinate = (i%playFieldWidth) * 4;
            final int yCoordinate = (i/playFieldWidth) * 8;
            final StackedTotem block = specialBlocks4By8.get(i).pattern()
                    .moveBy(new CoordinatePair(xCoordinate, yCoordinate));
            stackedTotems.add(block);
        }

        // fill-in the gap that was probably created from 4x8 blocks
        for(int i = 0; i < specialBlocks4By4ForGap.size(); i++) {
            final int xCoordinate = (xOffsetForGap + (i%(playFieldWidth-xOffsetForGap))) * 4;
            final int yCoordinate = (yOffsetForGap + (i/playFieldWidth-xOffsetForGap)) * 4;
            final StackedTotem block = specialBlocks4By4ForGap.get(i).pattern()
                    .moveBy(new CoordinatePair(xCoordinate, yCoordinate));
            stackedTotems.add(block);
        }

        // finish to fill up the field with 4x4 blocks
        int yOffsetForTheRest = yOffsetForGap + (specialBlocks4By4ForGap.size() > 0 ? 2 : 0);
        for(int i = 0; i < specialRemainingBlocks4By4.size(); i++) {
            final int xCoordinate = (i%playFieldWidth) * 4;
            final int yCoordinate = (yOffsetForTheRest + i/playFieldWidth) * 4;
            final StackedTotem block = specialRemainingBlocks4By4.get(i).pattern()
                    .moveBy(new CoordinatePair(xCoordinate, yCoordinate));
            stackedTotems.add(block);
        }

        // that's it
        List<TotemAnswer> totemAnswers = new ArrayList<>();
        stackedTotems.forEach(stackedTotem -> totemAnswers.addAll(stackedTotem.totemList()));
        return totemAnswers;
    }

    private int[] diophantineLinearSolve(int[][] c, int[] b) {
        int[] x = new int[c.length];

        while(!solved(c, x, b)) {
            incrementNext(c, x, b, 0);
        }

        return x;
    }

    private void incrementNext(int[][] c, int[] x, int[] b, int i) {
        if(i >= x.length) {
            return;
        }
        x[i]++;
        if(overflowed(c, x, b)) {
            x[i] = 0;
            incrementNext(c, x, b, i+1);
        }
    }

    private boolean overflowed(int[][] c, int[] x, int[] b) {
        boolean isOverFlowed = false;

        for(int i = 0; i < b.length; i++) {
            int equationResult = 0;
            for(int j = 0; j < c.length; j++) {
                equationResult += c[j][i] * x[j];
            }
            if(equationResult > b[i]) {
                isOverFlowed = true;
                break;
            }
        }

        return isOverFlowed;
    }

    private boolean solved(int[][] c, int[] x, int[] b) {
        boolean isSolved = true;

        for(int i = 0; i < b.length; i++) {
            int equationResult = 0;
            for(int j = 0; j < c.length; j++) {
                equationResult += c[j][i] * x[j];
            }
            if(equationResult != b[i]) {
                isSolved = false;
                break;
            }
        }

        return isSolved;
    }
}
