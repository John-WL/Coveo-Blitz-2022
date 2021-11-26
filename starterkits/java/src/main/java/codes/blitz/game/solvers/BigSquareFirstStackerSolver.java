package codes.blitz.game.solvers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.message.TotemQuestion;
import codes.blitz.game.totem_utils.stacked.big_square_stackers.Special4By4SquareBuilder;
import codes.blitz.game.totem_utils.stacked.StackedTotem;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BigSquareFirstStackerSolver implements CoveoSolver {

    public List<TotemAnswer> solve(List<TotemQuestion> totemsToPlace) {
        final int amountOfL = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.L)).count();
        final int amountOfJ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.J)).count();
        final int amountOfI = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.I)).count();
        final int amountOfT = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.T)).count();
        final int amountOfS = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.S)).count();
        final int amountOfZ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.Z)).count();
        final int amountOfO = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.O)).count();


        // building remnants, S and Z first

        // remnants
        final List<StackedTotem> yikesSpecialSquaresRemnants = new ArrayList<>();
        if(amountOfT%4 != 0) {
            yikesSpecialSquaresRemnants.add(SpecialBlockStacker.stackBlocks(amountOfT % 4, new TStackerV2()));
        }
        if(amountOfL%4 != 0) {
            yikesSpecialSquaresRemnants.add(SpecialBlockStacker.stackBlocks(amountOfL%4, new LStacker()));
        }
        if(amountOfJ%4 != 0) {
            yikesSpecialSquaresRemnants.add(SpecialBlockStacker.stackBlocks(amountOfJ%4, new JStacker()));
        }
        if(amountOfI%4 != 0) {
            yikesSpecialSquaresRemnants.add(SpecialBlockStacker.stackBlocks(amountOfI%4, new IStacker()));
        }
        if(amountOfO%4 != 0) {
            yikesSpecialSquaresRemnants.add(SpecialBlockStacker.stackBlocks(amountOfO%4, new OStacker()));
        }

        // S
        final List<StackedTotem> yikesSpecialSquaresS = new ArrayList<>();
        yikesSpecialSquaresS.addAll(new Special4By4SquareBuilder(Totem.S).build(amountOfS /4));
        if(amountOfS%4 != 0) {
            yikesSpecialSquaresS.add(SpecialBlockStacker.stackBlocks(amountOfS % 4, new SStackerV2()));
        }

        // Z
        final List<StackedTotem> yikesSpecialSquaresZ = new ArrayList<>();
        yikesSpecialSquaresZ.addAll(new Special4By4SquareBuilder(Totem.Z).build(amountOfZ /4));
        yikesSpecialSquaresZ.forEach(stackedTotem -> stackedTotem.moveBy(new CoordinatePair(1, 0)));
        if(amountOfZ %4 != 0) {
            yikesSpecialSquaresZ.add(SpecialBlockStacker.stackBlocks(amountOfZ % 4, new ZStacker()).moveBy(new CoordinatePair(1, 0)));
        }


        // building initial malformed base square

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        final List<StackedTotem> stackedTotems = new ArrayList<>();// calculate the size of the big square
        final int amountOfFullSquares = amountOfL /4 + amountOfJ /4 + amountOfI /4 + amountOfO /4 + amountOfT /4 + yikesSpecialSquaresRemnants.size() + yikesSpecialSquaresS.size() + yikesSpecialSquaresZ.size();
        final int sideLengthOfBiggestPossibleCoolSquare = (int) (Math.sqrt(amountOfFullSquares));
        final int areaOfBiggestPossibleCoolSquare = sideLengthOfBiggestPossibleCoolSquare * sideLengthOfBiggestPossibleCoolSquare;

        // use all the valid blocks for the big square
        final List<StackedTotem> special4By4Squares = new ArrayList<>();
        special4By4Squares.addAll(new Special4By4SquareBuilder(Totem.L).build(amountOfL /4));
        special4By4Squares.addAll(new Special4By4SquareBuilder(Totem.J).build(amountOfJ /4));
        special4By4Squares.addAll(new Special4By4SquareBuilder(Totem.I).build(amountOfI /4));
        special4By4Squares.addAll(new Special4By4SquareBuilder(Totem.O).build(amountOfO /4));
        special4By4Squares.addAll(new Special4By4SquareBuilder(Totem.T).build(amountOfT /4));
        special4By4Squares.addAll(yikesSpecialSquaresRemnants);
        special4By4Squares.addAll(yikesSpecialSquaresS);
        special4By4Squares.addAll(yikesSpecialSquaresZ);

        // to know the big square size with the over-heading parts on top and to the right of it
        CoordinatePair malformedBigSquareSize = new CoordinatePair(sideLengthOfBiggestPossibleCoolSquare, sideLengthOfBiggestPossibleCoolSquare);

        // move all the blocks that fit in the big square at their location
        for(int i = 0; i < areaOfBiggestPossibleCoolSquare; i++) {
            final int xCoordinate = (i%sideLengthOfBiggestPossibleCoolSquare)*4;
            final int yCoordinate = (i/sideLengthOfBiggestPossibleCoolSquare)*4;
            special4By4Squares.get(i)
                    .moveBy(new CoordinatePair(xCoordinate, yCoordinate));
        }

        // get the remaining unplaced special blocks
        final List<StackedTotem> remainingSpecialSquares = special4By4Squares.subList(
                areaOfBiggestPossibleCoolSquare,
                special4By4Squares.size());

        int amountOfRemainingSpecialSquares = remainingSpecialSquares.size();

        // fill the top line if we have too many remaining squares
        if(amountOfRemainingSpecialSquares > sideLengthOfBiggestPossibleCoolSquare) {
            malformedBigSquareSize = new CoordinatePair(malformedBigSquareSize.x(), malformedBigSquareSize.y() + 1);
            for(int i = 0; i < sideLengthOfBiggestPossibleCoolSquare; i++) {
                final int xCoordinate = i*4;
                final int yCoordinate = sideLengthOfBiggestPossibleCoolSquare*4;
                special4By4Squares.get(i + areaOfBiggestPossibleCoolSquare)
                        .moveBy(new CoordinatePair(xCoordinate, yCoordinate));
                amountOfRemainingSpecialSquares--;
            }
        }

        int amountOf4SquareHOLE_THERES_A_HOLE_DUDE = Math.max(sideLengthOfBiggestPossibleCoolSquare - amountOfRemainingSpecialSquares, 0);
        int initialHeightInTheHOLE_THERES_A_HOLE_DUDE = amountOfRemainingSpecialSquares;
        int initialWidthInTheHOLE_THERES_A_HOLE_DUDE = sideLengthOfBiggestPossibleCoolSquare;

        // fill the right-most line with the remaining special squares
        for(int i = 0; i < amountOfRemainingSpecialSquares; i++) {
            final int xCoordinate = sideLengthOfBiggestPossibleCoolSquare*4;
            final int yCoordinate = i*4;
            remainingSpecialSquares.get(remainingSpecialSquares.size() - i - 1)
                    .moveBy(new CoordinatePair(xCoordinate, yCoordinate));
        }

        if(amountOfRemainingSpecialSquares > 0) {
            malformedBigSquareSize = new CoordinatePair(malformedBigSquareSize.x() + 1, malformedBigSquareSize.y());
        }

        // big malformed square done
        stackedTotems.addAll(special4By4Squares);
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        // avoiding a plane crash
        if(malformedBigSquareSize.x() == 0) {
            malformedBigSquareSize = new CoordinatePair(1, malformedBigSquareSize.y());
        }
        if(malformedBigSquareSize.y() == 0) {
            malformedBigSquareSize = new CoordinatePair(malformedBigSquareSize.x(), 1);
        }

        // finding the right proportion to split T
        int s = yikesSpecialSquaresS.size();
        int z = yikesSpecialSquaresZ.size();
        /*int t = yikesSpecialSquaresT.size();
        int amountOfTToPutInS = (int)(Math.min(Math.max(((s-z-t)/(-2.0*t)), 0), 1)*t);
        int amountOfTToPutInZ = t - amountOfTToPutInS;
        amountOfTToPutInS += ((z % malformedBigSquareSize.y()) + amountOfTToPutInZ) % malformedBigSquareSize.y();
        if(amountOfTToPutInS > yikesSpecialSquaresT.size()) {
            amountOfTToPutInS = yikesSpecialSquaresT.size();
        }
        List<StackedTotem> tForS = yikesSpecialSquaresT.subList(0, amountOfTToPutInS);
        List<StackedTotem> tForZ = yikesSpecialSquaresT.subList(amountOfTToPutInS, yikesSpecialSquaresT.size());
        tForZ.forEach(StackedTotem::inversePattern);
        yikesSpecialSquaresS.addAll(tForS);
        yikesSpecialSquaresZ.addAll(tForZ);*/

        int newBigHeight = 0;
        int newBigWidth = 4;

        /*
        // and then doing Z
        for(int i = 0; i < yikesSpecialSquaresZ.size(); i++) {
            final int yCoordinate = (i % (malformedBigSquareSize.y())) * 4;
            final int xCoordinate = malformedBigSquareSize.x() * 4 + (i / (malformedBigSquareSize.y())) * 4;
            yikesSpecialSquaresZ.get(i).moveBy(new CoordinatePair(xCoordinate, yCoordinate));
            if (newBigHeight < yCoordinate) {
                newBigHeight = yCoordinate;
            }
            if(newBigWidth < xCoordinate) {
                newBigWidth = xCoordinate;
            }
        }

        // starting with S
        for(int i = 0; i < yikesSpecialSquaresS.size(); i++) {
            if(i/(malformedBigSquareSize.x()) == 0) {
                final int xCoordinate = (i % (malformedBigSquareSize.x())) * 4;
                final int yCoordinate = malformedBigSquareSize.y() * 4 + (i / (malformedBigSquareSize.x())) * 4;
                yikesSpecialSquaresS.get(i).moveBy(new CoordinatePair(xCoordinate, yCoordinate));
                if (newBigHeight < yCoordinate) {
                    newBigHeight = yCoordinate;
                }
            }
            else {
                final int xCoordinate = ((i-malformedBigSquareSize.x()) % (newBigWidth/4)) * 4;
                final int yCoordinate = malformedBigSquareSize.y() * 4 + ((i-malformedBigSquareSize.x()) / (newBigWidth/4)) * 4 + 4;
                yikesSpecialSquaresS.get(i).moveBy(new CoordinatePair(xCoordinate, yCoordinate));
                if (newBigHeight < yCoordinate) {
                    newBigHeight = yCoordinate;
                }
            }
        }*/

        /*
        // then doing T
        for(int i = 0; i < yikesSpecialSquaresT.size(); i++) {
            final int xCoordinate = malformedBigSquareSize.x()*4 + (i%(malformedBigSquareSize.x()))*4 + 1;
            final int yCoordinate = malformedBigSquareSize.y()*4 + (i/(malformedBigSquareSize.x()))*4 + 1;
            yikesSpecialSquaresT.get(i).moveBy(new CoordinatePair(xCoordinate, yCoordinate));
            if(newBigHeight < yCoordinate) {
                newBigHeight = yCoordinate;
            }
        }*/

        // updating TSZ
        //stackedTotems.addAll(yikesSpecialSquaresS);
        //stackedTotems.addAll(yikesSpecialSquaresZ);
        //stackedTotems.addAll(yikesSpecialSquaresT);

        // DON'T FORGET TO HANDLE THE REMAINING ghjkaghafdklghalk BLOCKS THAT ARE SPECIAL SNOW FLAKES

        // getting remaining amounts
        final int remainingL = amountOfL%4;
        final int remainingJ = amountOfJ%4;
        final int remainingI = amountOfI%4;
        final int remainingT = amountOfT%4;
        final int remainingS = amountOfS%4;
        final int remainingZ = amountOfZ%4;
        final int remainingO = amountOfO%4;

        // getting remaining blocks
        List<StackedTotem> snowFlakes = new ArrayList<>();
        //snowFlakes.add(SpecialBlockStacker.stackBlocks(remainingL, new LStacker()));
        //snowFlakes.add(SpecialBlockStacker.stackBlocks(remainingJ, new JStacker()));
        //snowFlakes.add(SpecialBlockStacker.stackBlocks(remainingI, new IStacker()));
        //snowFlakes.add(SpecialBlockStacker.stackBlocks(remainingT, new TStacker()));
        //snowFlakes.add(SpecialBlockStacker.stackBlocks(remainingS, new SStacker()));
        //snowFlakes.add(SpecialBlockStacker.stackBlocks(remainingZ, new ZStacker()));
        //snowFlakes.add(SpecialBlockStacker.stackBlocks(remainingO, new OStacker()));

        // filtering useless elements
        snowFlakes = snowFlakes.stream().filter(snowFlake -> snowFlake.totemList().size() > 0).collect(Collectors.toList());

        // filling the damn hole

        // absurd way to put back the blocks at the right height when there are not enough blocks
        if(stackedTotems.stream().noneMatch(stackedTotem -> stackedTotem.totemList().size() != 0)) {
            newBigHeight = -4;
        }

        // doing the remaining snowflakes
        for(int i = 0; i < snowFlakes.size(); i++) {
            final int xCoordinate = i*5;
            final int yCoordinate = newBigHeight + 4;
            snowFlakes.get(i).moveBy(new CoordinatePair(xCoordinate, yCoordinate));
        }

        // yeay
        stackedTotems.addAll(snowFlakes);

        List<TotemAnswer> totemAnswers = new ArrayList<>();
        stackedTotems.forEach(stackedTotem -> totemAnswers.addAll(stackedTotem.totemList()));
        System.out.println(totemsToPlace.size());
        System.out.println(totemAnswers.size());
        return totemAnswers;
    }
}
