/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    Matcher m;
    List<Matcher> matchers;
    
    public QueryBuilder() {
        m = new And();
        matchers = new ArrayList<Matcher>();
    }
    
    public QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int num, String category) {
        this.matchers.add(new HasAtLeast(num, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int num, String category) {
        this.matchers.add(new HasFewerThan(num, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }
    
    public Matcher build() {
        matchers.add(new All());
        Matcher[] matcherArr = new Matcher[matchers.size()];
        matcherArr = matchers.toArray(matcherArr);
        return new And(matcherArr);
    }
}
