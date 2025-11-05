package com.example.todolist;

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
}
