package codes.blitz.game.totem_utils;

public class SpecialSquareBruteForcer {

    public static void main(String[] args) {
        int[] possibilities = new int[]{8, 24, 24, 12, 12, 24, 9};
        int x = 0;

        for(int i = 0; i < possibilities.length; i++) {
            for(int j = i; j < possibilities.length; j++) {
                for(int k = j; k < possibilities.length; k++) {
                    for(int l = k; l < possibilities.length; l++) {
                        x += possibilities[i] * possibilities[j] * possibilities[k] * possibilities[l];
                    }
                }
            }
        }

        System.out.println(x);
    }
}
