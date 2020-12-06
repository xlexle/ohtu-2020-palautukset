package statistics;

import java.util.ArrayList;
import java.util.List;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {
    private List<Matcher> matchers = new ArrayList<Matcher>();

    public QueryBuilder() {
        matchers.add(new All());
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder any(Matcher... alternatives) {
        matchers.add(new Or(alternatives));
        return this;
    }

    public Matcher build() {
        Matcher every = new And(matchers.toArray(new Matcher[0]));
        matchers.clear();
        return every;
    }
}
