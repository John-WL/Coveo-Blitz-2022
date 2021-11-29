package codes.blitz.game.solvers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.message.TotemQuestion;
import codes.blitz.game.totem_utils.stacked.SSSLinedBlocksBuilder;
import codes.blitz.game.totem_utils.stacked.StackedTotem;
import codes.blitz.game.totem_utils.stacked.ZZZLinedBlocksBuilder;
import codes.blitz.game.totem_utils.stacked.big_square_stackers.Special4By4SquareBuilder;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.*;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.mixed.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BigSquareFirstStackerSolverV2 implements CoveoSolver {

    public List<TotemAnswer> solve(List<TotemQuestion> totemsToPlace) {
        int amountOfL = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.L)).count();
        int amountOfJ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.J)).count();
        int amountOfI = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.I)).count();
        int amountOfT = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.T)).count();
        int amountOfS = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.S)).count();
        int amountOfZ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.Z)).count();
        int amountOfO = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.O)).count();

        final List<StackedTotem> stackedTotems = new ArrayList<>();

        // some basic play-field info
        int playFieldWidth = (int)(Math.ceil(Math.sqrt(totemsToPlace.size()*4))+0.5) + 4;
        if(totemsToPlace.size() == 256) {
            playFieldWidth -= 4;
        }
        else if(totemsToPlace.size() == 64) {
            playFieldWidth -= 4;
        }
        else if(totemsToPlace.size() == 32) {
            playFieldWidth -= 4;
        }
        else if(totemsToPlace.size() == 16) {
            playFieldWidth -= 4;
        }
        //final int playFieldHeight = (int)(Math.ceil(totemsToPlace.size()*4.0/playFieldWidth)+0.5);
        //final int playFieldArea = playFieldWidth * playFieldHeight;

        CoordinatePair lastPositionOnField = new CoordinatePair(0, 0);

        // building the lines of z and s
        // trying to build Z2nL2 blocks
        final List<StackedTotem> blockLinesOfZ = new ArrayList<>();
        blockLinesOfZ.addAll(new ZZZLinedBlocksBuilder().build(playFieldWidth, amountOfZ, amountOfL));
        for(int i = 0; i < blockLinesOfZ.size(); i++) {
            amountOfZ -= blockLinesOfZ.get(i).size().x()-2;
        }
        amountOfL -= blockLinesOfZ.size()*2;

        // trying to build S2nJ2 blocks
        final List<StackedTotem> blockLinesOfS = new ArrayList<>();
        blockLinesOfS.addAll(new SSSLinedBlocksBuilder().build(playFieldWidth, amountOfS, amountOfJ));
        for(int i = 0; i < blockLinesOfS.size(); i++) {
            amountOfS -= blockLinesOfS.get(i).size().x()-2;
        }
        amountOfJ -= blockLinesOfS.size()*2;

        if(blockLinesOfZ.size() == 0 && blockLinesOfS.size() == 0) {
            lastPositionOnField = new CoordinatePair(-1, -1);
        }


        // building the rest of the squares!
        List<StackedTotem> fullSquares = new ArrayList<>();

        // Z AND S
        // create as many Z2L2 block as possible
        while(amountOfZ >= 2 && amountOfL >= 2) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new Z2L2Stacker());
            amountOfZ -= 2;
            amountOfL -= 2;
        }
        // create as many S2J2 block as possible
        while(amountOfS >= 2 && amountOfJ >= 2) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new S2J2Stacker());
            amountOfS -= 2;
            amountOfJ -= 2;
        }

        // create a ZT2L if possible
        if(amountOfZ >= 1 && (amountOfT%4) >= 2 && amountOfL >= 1) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new ZT2LStacker());
            amountOfZ -= 1;
            amountOfT -= 2;
            amountOfL -= 1;
        }
        // if we can't then try to create a ZJ2I if possible
        else if(amountOfZ >= 1 && amountOfJ >= 2 && amountOfI >= 1) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new ZJ2IStacker());
            amountOfZ -= 1;
            amountOfJ -= 2;
            amountOfI -= 1;
        }

        // create a ST2J if possible
        if(amountOfS >= 1 && (amountOfT%4) >= 2 && amountOfJ >= 1) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new ST2JStacker());
            amountOfS -= 1;
            amountOfT -= 2;
            amountOfJ -= 1;
        }
        // if we can't then try to create a SL2I if possible
        else if(amountOfS >= 1 && amountOfL >= 2 && amountOfI >= 1) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new SL2IStacker());
            amountOfS -= 1;
            amountOfL -= 2;
            amountOfI -= 1;
        }

        // T NOW
        // create a T2JI if possible
        if((amountOfT%4) >= 2 && amountOfJ >= 1 && amountOfI >= 1) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new T2JIStacker());
            amountOfT -= 2;
            amountOfJ -= 1;
            amountOfI -= 1;
        }
        // if we can't, then try to create a T2LI if possible
        else if((amountOfT%4) >= 2 && amountOfL >= 1 && amountOfI >= 1) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new T2LIStacker());
            amountOfT -= 2;
            amountOfL -= 1;
            amountOfI -= 1;
        }

        // NOW WE DO THE O
        // create a JOLI if possible
        if((amountOfO%2) == 1 && amountOfL >= 1 && amountOfJ >= 1 && amountOfI >= 1) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new JOLIStacker());
            amountOfO -= 1;
            amountOfL -= 1;
            amountOfJ -= 1;
            amountOfI -= 1;
        }
        // create a O2I2 if possible
        if((amountOfO%4) >= 2 && amountOfI >= 2) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new O2I2Stacker());
            amountOfO -= 2;
            amountOfI -= 2;
        }
        // create a O2J2 if possible
        if((amountOfO%4) >= 2 && amountOfJ >= 2) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new O2J2Stacker());
            amountOfO -= 2;
            amountOfJ -= 2;
        }
        // create a O2L2 if possible
        if((amountOfO%4) >= 2 && amountOfL >= 2) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new O2L2Stacker());
            amountOfO -= 2;
            amountOfL -= 2;
        }

        // AND FINALLY WE DO THE L AND J AND I
        // create a L2I2 if possible
        if((amountOfL%4) >= 2 && (amountOfI%4) >= 2) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new L2I2Stacker());
            amountOfL -= 2;
            amountOfI -= 2;
        }
        // create a J2I2 if possible
        if((amountOfJ%4) >= 2 && (amountOfI%4) >= 2) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new J2I2Stacker());
            amountOfJ -= 2;
            amountOfI -= 2;
        }
        // create a L2J2 if possible
        if((amountOfL%4) >= 2 && (amountOfJ%4) >= 2) {
            SpecialBlockStacker.addWeirdBlock(fullSquares, new L2J2Stacker());
            amountOfL -= 2;
            amountOfJ -= 2;
        }



        // NOW, WE TRY TO MATCH INCOMPLETE BLOCKS, IF THERE ARE ANY
        // only do this for smaller problems.
        if(totemsToPlace.size() <= 16) {
            Optional<StackedTotem> bestBlockToPlaceOpt = Optional.empty();
            do {
                if (bestBlockToPlaceOpt.isPresent()) {
                    final StackedTotem bestBlockToPlace = bestBlockToPlaceOpt.get();

                    // WE FINALLY ADD IT OMG FJUFHJWKHGWLIGHWEIULG
                    fullSquares.add(bestBlockToPlace);

                    // updating variables for remnants...
                    final List<Totem> typesPlaced = bestBlockToPlace.totemList().stream()
                            .map(TotemAnswer::shape)
                            .collect(Collectors.toList());
                    for (int i = 0; i < typesPlaced.size(); i++) {
                        Totem type = typesPlaced.get(i);
                        switch (type) {
                            case L -> amountOfL--;
                            case J -> amountOfJ--;
                            case I -> amountOfI--;
                            case T -> amountOfT--;
                            case O -> amountOfO--;
                            case S -> amountOfS--;
                            case Z -> amountOfZ--;
                        }
                    }
                }
                final List<Totem> totemShapesThatRemain = new ArrayList<>();
                for (int i = 0; i < amountOfL % 4; i++) {
                    totemShapesThatRemain.add(Totem.L);
                }
                for (int i = 0; i < amountOfJ % 4; i++) {
                    totemShapesThatRemain.add(Totem.J);
                }
                for (int i = 0; i < amountOfI % 4; i++) {
                    totemShapesThatRemain.add(Totem.I);
                }
                for (int i = 0; i < amountOfT % 4; i++) {
                    totemShapesThatRemain.add(Totem.T);
                }
                for (int i = 0; i < amountOfO % 4; i++) {
                    totemShapesThatRemain.add(Totem.O);
                }
                for (int i = 0; i < amountOfS % 4; i++) {
                    totemShapesThatRemain.add(Totem.S);
                }
                for (int i = 0; i < amountOfZ % 4; i++) {
                    totemShapesThatRemain.add(Totem.Z);
                }
                bestBlockToPlaceOpt = findBestSuitedBlockToFillInRemnants(totemShapesThatRemain);
            } while (bestBlockToPlaceOpt.isPresent());
        }


        // NOW, WE CAN PLACE EVERYTHING THAT'S CONTAINED IN THE "fullSquares" LIST, AND WE'LL BE DONE

        fullSquares.addAll(new Special4By4SquareBuilder(Totem.L).build(amountOfL /4));
        fullSquares.addAll(new Special4By4SquareBuilder(Totem.J).build(amountOfJ /4));
        fullSquares.addAll(new Special4By4SquareBuilder(Totem.I).build(amountOfI /4));
        fullSquares.addAll(new Special4By4SquareBuilder(Totem.O).build(amountOfO /4));
        fullSquares.addAll(new Special4By4SquareBuilder(Totem.T).build(amountOfT /4));
        // remnants, but considered as full squares
        fullSquares.add(SpecialBlockStacker.stackBlocks(amountOfL%4, new LStacker()));
        fullSquares.add(SpecialBlockStacker.stackBlocks(amountOfJ%4, new JStacker()));
        fullSquares.add(SpecialBlockStacker.stackBlocks(amountOfI%4, new IStacker()));
        fullSquares.add(SpecialBlockStacker.stackBlocks(amountOfT%4, new TStackerV2()));
        fullSquares.add(SpecialBlockStacker.stackBlocks(amountOfO%4, new OStacker()));
        // not remnants though
        fullSquares.addAll(new Special4By4SquareBuilder(Totem.S).build(amountOfS /4));
        fullSquares.addAll(new Special4By4SquareBuilder(Totem.Z).build(amountOfZ /4));
        // these yes
        fullSquares.add(SpecialBlockStacker.stackBlocks(amountOfS%4, new SStackerV2()));
        fullSquares.add(SpecialBlockStacker.stackBlocks(amountOfZ%4, new ZStacker()));
        fullSquares = fullSquares.stream().filter(stackedTotem -> stackedTotem.totemList().size() != 0).collect(Collectors.toList());

        // place the crazy cool lines, and fill the holes with squares, 2 times
        int biggestIndexOfAlreadyPlacedFullSquare = 0;
        int amountOfLayersPlaced = 0;
        for(int i = 0; i < blockLinesOfZ.size(); i++) {
            stackedTotems.add(blockLinesOfZ.get(i)
                    .moveBy(new CoordinatePair(0, 4*i)));
            lastPositionOnField = new CoordinatePair(blockLinesOfZ.get(i).size().x()-4, 4*i);
            int offset = blockLinesOfZ.get(i).size().x();
            for(int j = offset/4; j < playFieldWidth/4 && j - offset/4 < fullSquares.size(); j++) {
                stackedTotems.add(fullSquares.get(biggestIndexOfAlreadyPlacedFullSquare)
                        .moveBy(new CoordinatePair(4*j, 4*i)));
                biggestIndexOfAlreadyPlacedFullSquare++;
                lastPositionOnField = new CoordinatePair(4*j, 4*i);
            }
        }
        amountOfLayersPlaced += blockLinesOfZ.size();
        for(int i = 0; i < blockLinesOfS.size(); i++) {
            stackedTotems.add(blockLinesOfS.get(i)
                    .moveBy(new CoordinatePair(0, 4*(i + amountOfLayersPlaced))));
            lastPositionOnField = new CoordinatePair(blockLinesOfS.get(i).size().x()-4, 4*(i + amountOfLayersPlaced));
            int offset = blockLinesOfS.get(i).size().x();
            for(int j = offset/4; j < playFieldWidth/4
                    && j + biggestIndexOfAlreadyPlacedFullSquare - offset/4 < fullSquares.size(); j++) {
                CoordinatePair position = new CoordinatePair(4 * j, 4 * (i + amountOfLayersPlaced));
                stackedTotems.add(fullSquares.get(biggestIndexOfAlreadyPlacedFullSquare)
                        .moveBy(position));
                biggestIndexOfAlreadyPlacedFullSquare++;
                lastPositionOnField = position;
            }
        }
        amountOfLayersPlaced += blockLinesOfS.size();

        if(totemsToPlace.size() <= 2) {
            playFieldWidth = 4;
        }
        else if(playFieldWidth < 8) {
            playFieldWidth = 8;
        }

        // fill the rest of the play-field with full squares
        CoordinatePair copyOfLatestPositionOnField = new CoordinatePair(lastPositionOnField.x(), lastPositionOnField.y());
        if(blockLinesOfS.size() == 0 && blockLinesOfZ.size() == 0) {
            for (int i = biggestIndexOfAlreadyPlacedFullSquare; i < fullSquares.size(); i++) {
                final int xInBlockUnits = copyOfLatestPositionOnField.x() / 4;
                final int newXInBlockUnits = (xInBlockUnits + (i - biggestIndexOfAlreadyPlacedFullSquare)) % (playFieldWidth / 4);
                final int yInBlockUnits = copyOfLatestPositionOnField.y() / 4;
                final int newYInBlockUnits = yInBlockUnits + ((xInBlockUnits + i - biggestIndexOfAlreadyPlacedFullSquare) / (playFieldWidth / 4));
                stackedTotems.add(fullSquares.get(i).moveBy(new CoordinatePair(newXInBlockUnits * 4, newYInBlockUnits * 4)));
                lastPositionOnField = new CoordinatePair(newXInBlockUnits * 4, newYInBlockUnits * 4);
            }
        }
        else {
            for (int i = biggestIndexOfAlreadyPlacedFullSquare; i < fullSquares.size(); i++) {
                final int xInBlockUnits = copyOfLatestPositionOnField.x() / 4;
                final int newXInBlockUnits = (xInBlockUnits + (i - biggestIndexOfAlreadyPlacedFullSquare + 1)) % (playFieldWidth / 4);
                final int yInBlockUnits = copyOfLatestPositionOnField.y() / 4;
                final int newYInBlockUnits = yInBlockUnits + ((xInBlockUnits + i - biggestIndexOfAlreadyPlacedFullSquare + 1) / (playFieldWidth / 4));
                stackedTotems.add(fullSquares.get(i).moveBy(new CoordinatePair(newXInBlockUnits * 4, newYInBlockUnits * 4)));
                lastPositionOnField = new CoordinatePair(newXInBlockUnits * 4, newYInBlockUnits * 4);
            }
        }

        // remnants!
        List<StackedTotem> remnants = new ArrayList<>();
        remnants = remnants.stream().filter(snowFlake -> snowFlake.totemList().size() > 0).collect(Collectors.toList());

        copyOfLatestPositionOnField = new CoordinatePair(lastPositionOnField.x(), lastPositionOnField.y());
        for(int i = 0; i < remnants.size(); i++) {
            final int xInBlockUnits = copyOfLatestPositionOnField.x()/4;
            final int newXInBlockUnits = (xInBlockUnits + (i + 1)) % (playFieldWidth/4);
            final int yInBlockUnits = copyOfLatestPositionOnField.y()/4;
            final int newYInBlockUnits = yInBlockUnits + ((xInBlockUnits + i + 1) / (playFieldWidth/4));

            stackedTotems.add(remnants.get(i).moveBy(new CoordinatePair(newXInBlockUnits*4, newYInBlockUnits*4)));
        }


        List<TotemAnswer> totemAnswers = new ArrayList<>();
        stackedTotems.forEach(stackedTotem -> totemAnswers.addAll(stackedTotem.totemList()));
        System.out.println(totemsToPlace.size());
        System.out.println(totemAnswers.size());
        return totemAnswers;
    }

    private Optional<StackedTotem> findBestSuitedBlockToFillInRemnants(List<Totem> totemShapesThatRemain) {

        final Set<Totem> distinctShapes = new HashSet<>(totemShapesThatRemain);

        // this method is hand-crafted and executes in O(1), there's no way we are solving that on the fly here lol
        final List<StackedTotem> allWeirdBlocks = SpecialBlockStacker.generateEveryAvailableWeirdBlocks();

        // only keep the blocks that contain at least one occurrence of the shape we need
        final List<StackedTotem> concernedBlocksWithEmptyOnes = new ArrayList<>();
        allWeirdBlocks.forEach(stackedTotem -> {
            concernedBlocksWithEmptyOnes.add(
                    new StackedTotem(stackedTotem.totemList().stream()
                            .filter(totemAnswer -> totemShapesThatRemain.contains(totemAnswer.shape()))
                            .collect(Collectors.toList()), new CoordinatePair(4, 4)));
        });
        List<StackedTotem> concernedBlocks = concernedBlocksWithEmptyOnes.stream()
                .filter(stackedTotem -> stackedTotem.totemList().size() > 0)
                .collect(Collectors.toList());

        for(int i = 0; i < concernedBlocks.size(); i++) {
            for(Totem totem: distinctShapes) {
                int amountOfSpecificShapeInBlock = (int) concernedBlocks.get(i).totemList().stream().filter(totemAnswer -> totemAnswer.shape().equals(totem)).count();
                int amountOfSpecificShapeToAdd = (int) totemShapesThatRemain.stream().filter(shape -> shape.equals(totem)).count();
                while(amountOfSpecificShapeInBlock > amountOfSpecificShapeToAdd) {
                    amountOfSpecificShapeInBlock = (int) concernedBlocks.get(i).totemList().stream().filter(totemAnswer -> totemAnswer.shape().equals(totem)).count();
                    amountOfSpecificShapeToAdd = (int) totemShapesThatRemain.stream().filter(shape -> shape.equals(totem)).count();
                    for(int j = 0; j < concernedBlocks.get(i).totemList().size(); j++) {
                        if(concernedBlocks.get(i).totemList().get(j).shape().equals(totem)) {
                            concernedBlocks.get(i).totemList().remove(j);
                            j = concernedBlocks.get(i).totemList().size(); // break;
                        }
                    }
                }
            }
        }
        concernedBlocks = concernedBlocks.stream()
                .filter(stackedTotem -> stackedTotem.totemList().size() > 0)
                .collect(Collectors.toList());

        final List<StackedTotem> sortedConcernedBlocks = concernedBlocks.stream()
                .sorted(Comparator.comparingInt(stackedTotem -> stackedTotem.totemList().size()))
                .collect(Collectors.toList());

        if(sortedConcernedBlocks.size() > 0) {
            return Optional.of(sortedConcernedBlocks.get(sortedConcernedBlocks.size() - 1));
        }

        return Optional.empty();
    }
}
