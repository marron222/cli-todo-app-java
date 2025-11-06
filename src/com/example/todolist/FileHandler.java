package com.example.todolist;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {

	//フィールド名
	private static final String FILENAME = "C:/ForDevelop/pleiades2019/WorkSpace/cli-todo-app-java/todo_list.txt";

	//保存メソッド
	public void saveList(List<TodoItem> listToSave) {
		try(PrintWriter pw = new PrintWriter(new FileWriter(FILENAME))){

			//リストの項目を1つずつループで処理する
			for(TodoItem item : listToSave) {

				String line = item.toString();

				pw.println(line);

			}
		}catch(IOException e) {
				System.out.println("エラー：ファイルの保存中に問題が発生しました。");
				e.printStackTrace();
		}
	}
}