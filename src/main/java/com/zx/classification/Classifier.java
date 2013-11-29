package com.zx.classification;

//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.index.AtomicReader;

import java.io.IOException;
import java.util.Collection;

/**
 * A classifier, see
 * <code>http://en.wikipedia.org/wiki/Classifier_(mathematics)</code>, which
 * assign classes of type <code>T</code>
 */
public interface Classifier<T> {

  /**
   * Assign a class (with score) to the given text String
   * 
   * @param text
   *          a String containing text to be classified
   * @return a {@link ClassificationResult} holding assigned class of type
   *         <code>T</code> and score
   * @throws IOException
   *           If there is a low-level I/O error.
   * @throws Exception
   */
  public Collection<ClassificationResult<T>> classify(String text)
      throws Exception;

  /**
   * Train the classifier using the underlying Lucene index
   * 
   * @param atomicReader
   *          the reader to use to access the Lucene index
   * @param textFieldName
   *          the name of the field used to compare documents
   * @param classFieldName
   *          the name of the field containing the class assigned to documents
   * @param analyzer
   *          the analyzer used to tokenize / filter the unseen text
   * @throws IOException
   *           If there is a low-level I/O error.
   */

  public void applyTrainSet(DataSet dataSet);
  // public void train(AtomicReader atomicReader, String textFieldName,
  // String classFieldName, Analyzer analyzer) throws IOException;

}
