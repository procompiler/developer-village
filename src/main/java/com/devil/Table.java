package com.devil;

public class Table {
  public static void main(String[] args) {
    String[] tables = {"arc_pht", "arc_tag", "article", "badge", "bdg_eva", "bdg_stan", "block", "category", "comment", "follow", "noti", "region", "relo", "repo_arc", "repo_comt", "repo_type", "report", "tag", "user", "usr_bdg", "usr_bmk_arc", "usr_tag"};
    for (int i = 0; i < tables.length; i++) {
      System.out.printf("alter table %s convert to character set utf8;\n", tables[i]);
    }
  }
}
