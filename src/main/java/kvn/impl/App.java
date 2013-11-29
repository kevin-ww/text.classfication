package kvn.impl;

public class App {

  public static void main(String[] args) {

    String trainingDataDir = "E:\\tmp\\sample";

    LogUtil.debug("starting training text data under " + trainingDataDir);

    TrainingDataSet trainingDataSet = new TrainingDataSet(trainingDataDir);

    trainingDataSet.doTraining();

    String text = "微软公司提出美元网";
    // "A股并非对全球投资者完全开放，因此目前被排除在全球性股指之外。业内人士表示，A股总市值很可观，接近4万亿美元。如果中国股市完全对外开放";
    // "Oracle Exadata数据库云服务器、Oracle Exalytics商务智能云服务器以及Oracle Endeca Information Discovery，依托于ERP\\CRM等企业管理系统的商务软件组成了甲骨文高度集成化产品组合，为企业提供端到端解决方案";
    // /"不经意，i黑马网全新改版，可是我们怎么会让他不经意间呢？作为slogan为：挖黑马、评热点、找灵感、抄本质，不仅是我们，受众也不会满足，只是简单提供资讯";
    // "北京市海淀区人民法院26日上午对“微博名人”禹晋永合同诈骗一案作出一审判决，认定禹晋永犯合同诈骗罪，判处有期徒刑十一年、罚金人民币十万元、剥夺政治权利二年，并责令其退赔被害单位经济损失人民币52.2万元";
    // "想看看现在三十多岁的人一路走过来的生活，可以参考网络活化石--校园BBS。BBS是 网络社区的元老了。由于界面不够傻瓜，后来的学生已经很少用，其主流用户群一直 是十多年前读大学那帮人。十多年前这帮人还在象牙塔里的时候，像水木清华BBS比 较热门的版块是L";
    // "微软公司提出以446亿美元的价格收购雅虎中国网2月1日报道 美联社消息，微软公司提出以446亿美元现金加股票的价格收购搜索网站雅虎公司。微软提出以每股31美元的价格收购雅虎。微软的收购报价较雅虎1月31日的收盘价19.18美元溢价62%。微软公司称雅虎公司的股东可以选择以现金或股票进行交易。微软和雅虎公司在2006年底和2007年初已在寻求双方合作。而近两年，雅虎一直处于困境：市场份额下滑、运营业绩不佳、股价大幅下跌。对于力图在互联网市场有所作为的微软来说，收购雅虎无疑是一条捷径，因为双方具有非常强的互补性。(小桥)";

    LogUtil.debug("starting classifier " + text
        + " using navie bayes classifier");

    String classifierResult = new BayesClassfiler(trainingDataSet)
        .classifier(text);

    System.out.println(classifierResult);

  }

}
