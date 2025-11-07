/**
 * ToDoリストの項目（TodoItem）全体を管理するクラスです。
 * 項目の追加、表示、完了、編集、リストの取得といった中核的なロジックを提供します。
 */
package com.example.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoListManager {

	//フィールド
	/** ToDoItemオブジェクトを保持するリスト */
	private List<TodoItem> todoItems = new ArrayList <>();

	/**
	 * ToDo項目をリストに追加します。
	 * @param item 追加するTodoItemオブジェクト
	 */
	public void addItem(TodoItem item) {
		todoItems.add(item);
	}

	/**
	 * 現在のToDoリスト全体をコンソールに番号付きで表示します。
	 */
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

	/**
	 * ユーザーが指定した番号（インデックス）のToDo項目を「完了」状態にします。
	 * @param index 完了にしたい項目の番号（1始まり）
	 */
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

	/**
	 * ユーザーが指定した番号のToDo項目の内容を編集します。
	 * @param index 編集したい項目の番号（1始まり）
	 * @param newContent 新しい内容
	 */
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

	/**
	 * ユーザーが指定した番号のToDo項目の期限を編集します。
	 * @param index 編集したい項目の番号（1始まり）
	 * @param newDeadline 新しい期限 (LocalDate形式)
	 */
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

	/**
	 * 現在管理しているToDoリスト全体を取得し、外部に提供します。
	 * @return TodoItemオブジェクトのリスト
	 */
	public List<TodoItem> getTodoItems() {
		return todoItems;
	}


	/**
	 * TodoItemのsetter
	 * @param todoItems 読み込んだリスト
	 */
	public void setTodoItems(List<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}


}
