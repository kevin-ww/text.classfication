package kvn.vista;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
//
//import com.vista.ClassConditionalProbability;
//import com.vista.PriorProbability;
//import com.vista.StopWordsHandler;
//import com.vista.TrainingDataManager;

/**
 * 朴素贝叶斯分类器
 */
public class BayesClassifier {
  private TrainingDataManager tdm;// 训练集管理器
  private String trainnigDataPath;// 训练集路径
  private static double zoomFactor = 10.0f;

  /**
   * 默认的构造器，初始化训练集
   */
  public BayesClassifier() {
    tdm = new TrainingDataManager();
  }

  /**
   * 计算给定的文本属性向量X在给定的分类Cj中的类条件概率 <code>ClassConditionalProbability</code>连乘值
   * 
   * @param X
   *          给定的文本属性向量
   * @param Cj
   *          给定的类别
   * @return 分类条件概率连乘值，即<br>
   */
  float calcProd(String[] X, String Cj) {
    float ret = 1.0F;
    // 类条件概率连乘
    for (int i = 0; i < X.length; i++) {
      String Xi = X[i];
      // 因为结果过小，因此在连乘之前放大10倍，这对最终结果并无影响，因为我们只是比较概率大小而已
      float vv = ClassConditionalProbability.calculatePxc(Xi, Cj);
      // System.out.println("getting " + vv + " for " + Xi);
      ret *= vv * zoomFactor;
    }
    // 再乘以先验概率
    ret *= PriorProbability.calculatePc(Cj);
    return ret;
  }

  /**
   * 去掉停用词
   * 
   * @param text
   *          给定的文本
   * @return 去停用词后结果
   */
  public String[] DropStopWords(String[] oldWords) {
    Vector<String> v1 = new Vector<String>();
    for (int i = 0; i < oldWords.length; ++i) {
      if (StopWordsHandler.IsStopWord(oldWords[i]) == false) {// 不是停用词
        v1.add(oldWords[i]);
      }
    }
    String[] newWords = new String[v1.size()];
    v1.toArray(newWords);
    return newWords;
  }

  /**
   * 对给定的文本进行分类
   * 
   * @param text
   *          给定的文本
   * @return 分类结果
   */
  @SuppressWarnings("unchecked")
  public String classify(String text) {
    String[] terms = null;
    terms = ChineseSpliter.split(text, " ").split(" ");// 中文分词处理(分词后结果可能还包含有停用词）
    terms = DropStopWords(terms);// 去掉停用词，以免影响分类

    String[] Classes = tdm.getTraningClassifications();// 分类
    float probility = 0.0F;
    List<ClassifyResult> crs = new ArrayList<ClassifyResult>();// 分类结果
    for (int i = 0; i < Classes.length; i++) {
      String Ci = Classes[i];// 第i个分类
      probility = calcProd(terms, Ci);// 计算给定的文本属性向量terms在给定的分类Ci中的分类条件概率
      // 保存分类结果
      ClassifyResult cr = new ClassifyResult();
      cr.classification = Ci;// 分类
      cr.probility = probility;// 关键字在分类的条件概率
      System.out.println("In process....");
      System.out.println(Ci + "：" + probility);
      crs.add(cr);
    }
    // 对最后概率结果进行排序
    java.util.Collections.sort(crs, new Comparator() {
      public int compare(final Object o1, final Object o2) {
        final ClassifyResult m1 = (ClassifyResult) o1;
        final ClassifyResult m2 = (ClassifyResult) o2;
        final double ret = m1.probility - m2.probility;
        if (ret < 0) {
          return 1;
        } else {
          return -1;
        }
      }
    });
    // 返回概率最大的分类
    return crs.get(0).classification;
  }

  public static void main(String[] args) {
    String text = "微软公司提出美元网";
    // "A股并非对全球投资者完全开放，因此目前被排除在全球性股指之外。业内人士表示，A股总市值很可观，接近4万亿美元。如果中国股市完全对外开放";
    // "Oracle Exadata数据库云服务器、Oracle Exalytics商务智能云服务器以及Oracle Endeca Information Discovery，依托于ERP\\CRM等企业管理系统的商务软件组成了甲骨文高度集成化产品组合，为企业提供端到端解决方案";
    // /"不经意，i黑马网全新改版，可是我们怎么会让他不经意间呢？作为slogan为：挖黑马、评热点、找灵感、抄本质，不仅是我们，受众也不会满足，只是简单提供资讯";
    // "北京市海淀区人民法院26日上午对“微博名人”禹晋永合同诈骗一案作出一审判决，认定禹晋永犯合同诈骗罪，判处有期徒刑十一年、罚金人民币十万元、剥夺政治权利二年，并责令其退赔被害单位经济损失人民币52.2万元";
    // "想看看现在三十多岁的人一路走过来的生活，可以参考网络活化石--校园BBS。BBS是 网络社区的元老了。由于界面不够傻瓜，后来的学生已经很少用，其主流用户群一直 是十多年前读大学那帮人。十多年前这帮人还在象牙塔里的时候，像水木清华BBS比 较热门的版块是L";
    // "微软公司提出以446亿美元的价格收购雅虎中国网2月1日报道 美联社消息，微软公司提出以446亿美元现金加股票的价格收购搜索网站雅虎公司。微软提出以每股31美元的价格收购雅虎。微软的收购报价较雅虎1月31日的收盘价19.18美元溢价62%。微软公司称雅虎公司的股东可以选择以现金或股票进行交易。微软和雅虎公司在2006年底和2007年初已在寻求双方合作。而近两年，雅虎一直处于困境：市场份额下滑、运营业绩不佳、股价大幅下跌。对于力图在互联网市场有所作为的微软来说，收购雅虎无疑是一条捷径，因为双方具有非常强的互补性。(小桥)";
    BayesClassifier classifier = new BayesClassifier();// 构造Bayes分类器
    String result = classifier.classify(text);// 进行分类
    System.out.println("此项属于[" + result + "]");
  }
  // C000007 汽车
  // C000008 财经
  // C000010 IT
  // C000013 健康
  // C000014 体育
  // C000016 旅游
  // C000020 教育
  // C000022 招聘
  // C000023 文化
  // C000024 军事
}