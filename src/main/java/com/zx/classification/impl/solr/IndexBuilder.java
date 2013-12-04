package com.zx.classification.impl.solr;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.zx.classification.impl.Comment;

public class IndexBuilder {

  IndexWriter indexWriter = null;

  Directory indexDir = null;

  public IndexBuilder(Analyzer analyzer, String indexDirPath)
      throws IOException {
    this.indexDir = FSDirectory.open(new File(indexDirPath));
    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,
        analyzer);
    indexWriter = new IndexWriter(indexDir, config);
  }

  public void buildIndex(Comment comment) throws IOException {

    Document doc = new Document();

    // Field
    Field fc = new TextField("comment", comment.getComment(), Field.Store.YES);

    Field fid = new StringField("id", comment.getId(), Field.Store.YES);

    Field fbrand = new StringField("brand", comment.getBrand(), Field.Store.YES);

    Field frating = new StringField("rating", comment.getRating().toString(),
        Field.Store.YES);

    Field flables = null;

    doc.add(fid);
    doc.add(fc);
    doc.add(fbrand);
    doc.add(frating);
    doc.add(flables);

    indexWriter.addDocument(doc);

  }

  public void buildIndex(String text) throws IOException {

    Document doc = new Document();

    Field content = new TextField("content", text, Field.Store.YES);

    doc.add(content);

    if (indexWriter.getConfig().getOpenMode() == OpenMode.CREATE) {
      // System.out.println("ready for create new index");
    }

    indexWriter.addDocument(doc);
  }

  public void done() throws IOException {
    this.indexWriter.close();
  }

  public static void main(String[] args) throws IOException {

    String dirPath = "e://tmp//";

    Analyzer ikAnalyzer = new IKAnalyzer();

    String text = "IK Analyzer是一个结合词典分词和文法分词的中文分词开源工具包。它使用了全新的正向迭代最细粒度切分算法。";

    IndexBuilder builder = new IndexBuilder(ikAnalyzer, dirPath);

    builder.buildIndex(text);

    builder.done();

  }

}
