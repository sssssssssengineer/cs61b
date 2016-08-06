/**
 * Created by Administrator on 2016/7/24.
 */
public class OffByOne implements CharacterComparator{
    // implement CharacterComparator
    // such that equalChars returns true for letters that are different by one letter.
    @Override
    public boolean equalChars(char x, char y){
        if ((x-y==1) ||(x-y)==-1){
            return true;
        }
        else {return false;}
    }
}
