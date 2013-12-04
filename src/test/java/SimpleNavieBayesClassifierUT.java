import com.zx.classification.impl.fs.FSNavieBayesClassifier;
import com.zx.classification.impl.fs.TrainningDataSet;

import junit.framework.TestCase;

public class SimpleNavieBayesClassifierUT extends TestCase {

  protected void setUp() throws Exception {
    super.setUp();

    // System.out.println(trainingDataSet);

  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public static void testTrain() throws Exception {

    // String trainingDocPath = "e:/tmp/sample";
    //
    // TrainningDataSet trainingDataSet = new TrainningDataSet(trainingDocPath);
    //
    // trainingDataSet.doTrain();
    //
    // System.out.println(trainingDataSet);

  }

  public static void testClassifiy() throws Exception {

    String trainingDocPath = "e:/tmp/sample";

    TrainningDataSet trainingDataSet = new TrainningDataSet(trainingDocPath);

    trainingDataSet.doTrain();

    String text = "戴尔公司一些产品打折力度很大。戴尔公司首席执行官凯文";

    FSNavieBayesClassifier classifier = new FSNavieBayesClassifier();

    classifier.applyTrainSet(trainingDataSet);

    classifier.classify(text);

  }

}
