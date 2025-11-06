/**
 * ファイルのI/O処理を管理するクラスです。
 */
package com.example.todolist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

	//フィールド名
	/** ToDoリストを保存するファイルパス */
	private static final String FILENAME = "C:/ForDevelop/pleiades2019/WorkSpace/cli-todo-app-java/todo_list.txt";

	/**
	 * ToDoリスト全体をファイルに保存します。
	 * @param listToSave 保存対象のTodoItemリスト
	 */
	public void saveList(List<TodoItem> listToSave) {
		try(PrintWriter pw = new PrintWriter(new FileWriter(FILENAME))){

			//リストの項目を1つずつループで処理する
			for(TodoItem item : listToSave) {

				String line = item.toFileString();

				pw.println(line);

			}

		}catch(IOException e) {
				System.out.println("エラー：ファイルの保存中に問題が発生しました。");
				e.printStackTrace();
		}
	}

	/**
	 * プリケーション起動時にデータをファイルから読み込みます。
	 * @param listToSave 保存対象のTodoItemリスト
	 */
	public List<TodoItem> loadList(){

		// BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込む
		List<TodoItem> loadedList = new ArrayList<>();

		try(BufferedReader br = new BufferedReader(new FileReader(FILENAME))){

			String line;


			while((line = br.readLine()) != null) {

				// 空行や不正な行はスキップ
                if (line.trim().isEmpty()) {
                    continue;
                }

				//読み込んだ line を、区切り文字である カンマ (,) で分割します。
				String[] parts = line.split(",");

				// データが3つ（内容,期限,完了状態）揃っているか確認
                if (parts.length < 3) {
                    System.err.println("警告: 不正なデータ形式の行をスキップしました: " + line);
                    continue;
                }

				//3つに分割したものを変数に格納します。
                String contentString = parts[0];
                String deadlineString = parts[1];
                String completedString = parts[2];

                //String型からそれぞれ型変換
                LocalDate deadlineLocalDate = LocalDate.parse(deadlineString);
                boolean completedBoolean = Boolean.parseBoolean(completedString);

                //変換したデータでTodoItemを生成
                TodoItem item = new TodoItem(contentString,deadlineLocalDate);

                //完了状態をリセット
                item.setCompleted(completedBoolean);

                //リストに追加
                loadedList.add(item);

			}
		}catch(IOException e){
			// ファイルがない場合やI/Oエラー時の処理（空のリストを返すのが目的）
            System.out.println("警告: ToDoリストファイルが見つからないか、読み込みに失敗しました。新規リストを開始します。");
		}catch(Exception e) {
			// パースエラー（LocalDate.parseの失敗など）をキャッチするため、広めのExceptionも検討
            System.err.println("致命的なエラー: データの解析に失敗しました。");
		}

		return loadedList;

	}


}