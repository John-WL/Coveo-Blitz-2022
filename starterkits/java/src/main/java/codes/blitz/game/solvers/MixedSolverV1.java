package codes.blitz.game.solvers;

import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.message.TotemQuestion;

import java.util.List;

public class MixedSolverV1 implements CoveoSolver {

    @Override
    public List<TotemAnswer> solve(List<TotemQuestion> totemsToPlace) {
        if(totemsToPlace.size() == 1) {
            return new SingleTotemSolver().solve(totemsToPlace);
        }
        if(totemsToPlace.size() == 2) {
            return new BiTotemSolver().solve(totemsToPlace);
        }
        if(totemsToPlace.size() == 4) {
            return new QuadriTotemSolver().solve(totemsToPlace);
        }
        return new BigSquareFirstStackerSolver().solve(totemsToPlace);
    }
}
