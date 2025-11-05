package com.example.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoListManager {

	//フィールド
	private List<TodoItem> todoItems = new ArrayList <>();

	// TodoItemを受け取り、リストに追加する
	public void addItem(TodoItem item) {
		todoItems.add(item);
	}

	// リスト全体を番号付きで表示する
	public void displayList() {


		if(todoItems.isEmpty()) {

			//項目がない場合は"ToDoリストに項目はありません。"を表示
			System.out.println("-------------------------");
	        System.out.println("ToDoリストに項目はありません。");
	        System.out.println("-------------------------");
	        return;

		}else{

			//項目がある場合は一覧表示
			System.out.println("--- 現在のToDoリスト ---");
			for(int i = 0; i < todoItems.size(); i++) {

				// (i + 1) を表示番号として出力
				System.out.print((i + 1) + ". ");

				//TodoItemオブジェクトを取得し、出力
				TodoItem item = todoItems.get(i);
				System.out.println(item);

			}
			System.out.println("-------------------------");
		}
	}

	//ユーザーが指定した番号（インデックス）の ToDo項目を「完了」にする
	public void completeItem(int index) {

		//受け取った index がリストの有効な範囲内にあるかチェック
		if(
			index < 1 ||
			index > todoItems.size()
		){
			//無効な場合メソッドを終了
			System.out.println("エラー: 指定された番号は無効です。");
	        return;
		}else {

			//index を使ってリストから TodoItem オブジェクトを取得
			int zeroBasedIndex = index - 1;
			TodoItem itemToComplete = todoItems.get(zeroBasedIndex);

			//取得したオブジェクトの setCompleted(true) メソッドを呼び出し
			itemToComplete.setCompleted(true);
		}
	}

	//リストの内容を編集する
	public void editContent(int index, String newContent) {
		//受け取った index がリストの有効な範囲内にあるかチェック
		if(
			index < 1 ||
			index > todoItems.size()
		){
			//無効な場合メソッドを終了
			System.out.println("エラー: 指定された番号は無効です。");
	        return;
		}else {

			//index を使ってリストから TodoItem オブジェクトを取得
			int zeroBasedIndex = index - 1;
			TodoItem itemToComplete = todoItems.get(zeroBasedIndex);

			//取得したオブジェクトのsetContent(newContent)メソッドを呼び出し
			itemToComplete.setContent(newContent);
		}
	}

	//リストの期限を編集する
	public void editDeadline(int index, LocalDate newDeadline) {
		//受け取った index がリストの有効な範囲内にあるかチェック
		if(
			index < 1 ||
			index > todoItems.size()
		){
			//無効な場合メソッドを終了
			System.out.println("エラー: 指定された番号は無効です。");
	        return;
		}else {

			//index を使ってリストから TodoItem オブジェクトを取得
			int zeroBasedIndex = index - 1;
			TodoItem itemToComplete = todoItems.get(zeroBasedIndex);

			//取得したオブジェクトのsetDeadline(newDeadline)メソッドを呼び出し
			itemToComplete.setDeadline(newDeadline);
		}
	}

	//現在管理しているToDoリスト全体を取得し、外部に提供する。
	public List<TodoItem> getTodoItems() {
		return todoItems;
	}
}
