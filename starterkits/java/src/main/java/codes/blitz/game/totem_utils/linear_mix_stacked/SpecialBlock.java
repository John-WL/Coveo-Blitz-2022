package codes.blitz.game.totem_utils.linear_mix_stacked;

import codes.blitz.game.totem_utils.stacked.StackedTotem;

public interface SpecialBlock {
    StackedTotem pattern();
    int LCoeff();
    int JCoeff();
    int ICoeff();
    int TCoeff();
    int SCoeff();
    int ZCoeff();
    int OCoeff();
}
