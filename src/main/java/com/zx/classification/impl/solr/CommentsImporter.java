package com.zx.classification.impl.solr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.zx.classification.Lable;
import com.zx.classification.impl.Comment;

public class CommentsImporter {

  public static final String DEFAULT_DELIMETER = ",";

  private static final boolean COMMENTS_FILE_HAS_HEADER = true;

  // String commentsFilePath;
  String commentsFilePath;

  IndexBuilder indexBuilder;

  public CommentsImporter(String commentsFilePath, String indexDirPath) {
    this.commentsFilePath = commentsFilePath;
  }

  public void importComment() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(commentsFilePath));

    String line;

    if (COMMENTS_FILE_HAS_HEADER) {
      line = reader.readLine();
      if (line != null)
        System.out.println("HEADER:" + line);
    }

    String[] tokens;
    String id;
    String commentText;
    Comment.Rating rating;
    String[] lables;

    Comment comment;
    while ((line = reader.readLine()) != null) {
      tokens = line.trim().split(DEFAULT_DELIMETER);
      // System.out.println(Arrays.asList(tokens).toString());
      id = tokens[0];

      commentText = tokens[4];

      rating = Comment.convertRating(tokens[6]);

      lables = new Lable(tokens[7]).getLables();// TODO;

      comment = new Comment(id, commentText, rating, lables);

      System.out.println(comment);

    }
    reader.close();

    // Scanner scanner = new Scanner(commentsCSV);
    //
    // scanner.useDelimiter(DEFAULT_DELIMETER);
    //
    // while (scanner.hasNext()) {
    // System.out.print(scanner.next() + "|");
    // }
    // scanner.close();
  }

  public static void main(String[] args) throws IOException {
    String filePath = "E:\\wrkspace\\text.classfication\\lib\\comments.log";
    // CommentsImporter importer = new CommentsImporter(filePath);
    // importer.importComment();

  }

}
