package codes.blitz.game.solvers;

import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.message.TotemQuestion;

import java.util.List;

public interface CoveoSolver {
    List<TotemAnswer> solve(List<TotemQuestion> totemsToPlace);
}
