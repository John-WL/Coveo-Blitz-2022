package codes.blitz.game.solvers;

import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.message.TotemQuestion;
import codes.blitz.game.totem_utils.stacked.StackedTotem;
import codes.blitz.game.totem_utils.stacked.StackedTotemBuilder;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SingleTotemSolver implements CoveoSolver {
    @Override
    public List<TotemAnswer> solve(List<TotemQuestion> totemsToPlace) {
        if(totemsToPlace.size() != 1) {
            throw new RuntimeException("SingleTotemSolver object needs a problem with exactly one totem");
        }

        final int amountOfL = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.L)).count();
        final int amountOfJ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.J)).count();
        final int amountOfI = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.I)).count();
        final int amountOfT = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.T)).count();
        final int amountOfS = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.S)).count();
        final int amountOfZ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.Z)).count();
        final int amountOfO = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.O)).count();

        // getting remaining blocks
        List<StackedTotem> snowFlakes = new ArrayList<>();
        snowFlakes.add(SpecialBlockStacker.stackBlocks(amountOfL, new LStacker()));
        snowFlakes.add(SpecialBlockStacker.stackBlocks(amountOfJ, new JStacker()));
        snowFlakes.add(SpecialBlockStacker.stackBlocks(amountOfI, new IStacker()));
        snowFlakes.add(SpecialBlockStacker.stackBlocks(amountOfT, new TStacker()));
        snowFlakes.add(SpecialBlockStacker.stackBlocks(amountOfS, new SStacker()));
        snowFlakes.add(SpecialBlockStacker.stackBlocks(amountOfZ, new ZStacker()));
        snowFlakes.add(SpecialBlockStacker.stackBlocks(amountOfO, new OStacker()));

        // filtering useless elements
        snowFlakes = snowFlakes.stream().filter(snowFlake -> snowFlake.totemList().size() > 0).collect(Collectors.toList());

        return snowFlakes.get(0).totemList();
    }
}
