package patience;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ludvig on 24/06/15.
 */
public class PileGroup extends ArrayList<Pile> {
    int n =0;
    public PileGroup(int n) {
        for ( int i = 0; i < n; ++i ) add(new Pile());

    }

    public Pile at(int n) {
      return get(n);
    }


    public void deal(Card deal) {
        get(n).add(deal);
        n = (n + 1) % size();
    }
    
    public String toString() {
      return "PileGroup(" + size() + ")";
    }
}
