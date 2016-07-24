/**
 * Created by Administrator on 2016/7/24.
 */
public class OffByN implements CharacterComparator {
    private int Num;

    public OffByN(int N) {
        Num = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if ((x - y == Num) || (x - y) == -Num) {
            return true;
        } else {
            return false;
        }
    }
}
