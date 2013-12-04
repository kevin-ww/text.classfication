public class RuleEngineUT {

  public static void main(String[] args) {
    Rule rule = new Rule();
    Fact fact = new Fact();
    RuleEngine engine = new RuleEngine();
    engine.applyRule(rule, fact);

    System.out.println("now apply rule");
  }

}

class RuleEngine {

  public void applyRule(Rule rule, Fact fact) {

  }

}

class Rule {

}

class Fact {

  String originalString;

  // String factStr = "一直购买，品质不错，发货也很快";

}