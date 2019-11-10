import java.util.Comparator;

public class SortHighscore implements Comparator<ScorePoint> {

    @Override
    public int compare(ScorePoint o1, ScorePoint o2) {
        return o1.getPoints() - o2.getPoints();
    }
}
