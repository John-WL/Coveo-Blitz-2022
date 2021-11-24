package codes.blitz.game.solvers;

import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.message.TotemQuestion;
import codes.blitz.game.totem_utils.stacked.StackedTotem;
import codes.blitz.game.totem_utils.stacked.StackedTotemBuilder;
import codes.blitz.game.totem_utils.stacked.stack_mergers.BasicArrayMerger;

import java.util.ArrayList;
import java.util.List;

public class BasicStackerSolver implements CoveoSolver {

    public List<TotemAnswer> solve(List<TotemQuestion> totemsToPlace) {
        final int amountOfL = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.L)).count();
        final int amountOfJ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.J)).count();
        final int amountOfI = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.I)).count();
        final int amountOfT = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.T)).count();
        final int amountOfS = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.S)).count();
        final int amountOfZ = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.Z)).count();
        final int amountOfO = (int) totemsToPlace.stream().filter(totemQuestion -> totemQuestion.shape().equals(Totem.O)).count();

        final List<StackedTotem> stackedTotems = new ArrayList<>();

        stackedTotems.add(new StackedTotemBuilder(Totem.L).build(amountOfL));
        stackedTotems.add(new StackedTotemBuilder(Totem.J).build(amountOfJ));
        stackedTotems.add(new StackedTotemBuilder(Totem.I).build(amountOfI));
        stackedTotems.add(new StackedTotemBuilder(Totem.T).build(amountOfT));
        stackedTotems.add(new StackedTotemBuilder(Totem.S).build(amountOfS));
        stackedTotems.add(new StackedTotemBuilder(Totem.Z).build(amountOfZ));
        stackedTotems.add(new StackedTotemBuilder(Totem.O).build(amountOfO));

        return new BasicArrayMerger().merge(stackedTotems);
    }
}
