/**
 * メインクラス
 * アプリケーションの開始と終了、ユーザーとの対話という3つの責任を持ちます。
 */
package com.example.todolist;

import java.time.LocalDate;
import java.util.Scanner;

public class TodoApp {

	//フィールド
	/**
	 * ユーザー入力を受け付けるオブジェクト
	 * @param コマンドラインからの入力
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	 * TodoListManagerをインスタンス化
	 */
	private TodoListManager todoListManager = new TodoListManager();

	/**
	 * FileHandlerをインスタンス化
	 */
	private FileHandler fileHandler = new FileHandler();

	// ----------------------------------------------------
    // アプリケーションの開始・終了処理
    // ----------------------------------------------------

	/**
     * アプリケーションのメイン処理を開始します。
     */
	public void start() {
		System.out.println("--- ToDoリストアプリを起動中 ---");

		/**
		 * 初期化メソッド
		 * 保存されているデータを読み込み、TodoListManagerにセットします。
		 */
		todoListManager.setTodoItems(fileHandler.loadList());
        System.out.println("初期データを読み込みました。");

        //メインループを実行
        runLoop();

        //終了処理
        scanner.close();
	}




	// ----------------------------------------------------
    // メインループ処理
    // ----------------------------------------------------

    /**
	 * ユーザーと対話するための無限ループを開始
	 * メニュー表示→入力受付→コマンド振り分け→終了処理
	 */
	public void runLoop() {
		while(true) {
			/**
			 * メニュー表示
			 * ユーザーに「1:追加, 2:編集, 3:一覧, 4:完了, 0:終了」のコマンドメニューを表示します。
			 */
			System.out.println("行いたい操作の番号を入力してください。");
			System.out.println("1:追加　2:編集　3:一覧　4:完了　0:終了");
			System.out.print("> ");

			/**
			 * 入力受付
			 */
			String operationNumber = scanner.nextLine().trim();

			/**
			 * コマンド振り分け
			 * 入力されたコマンドに応じて適切なメソッドを呼び出します。
			 */
			try {

				switch(operationNumber) {

					//追加の場合addTaskメソッドを実行
					case "1":
						addTask();
						break;

					//編集の場合editTaskメソッドを実行
					case "2":
						editTask();
						break;


					//一覧の場合
					case "3":

					//完了の場合completeTaskメソッドを実行
					case "4":
						completeTask();
						break;

					case "0":
						// 終了処理: データを保存し、ループを抜ける
                        fileHandler.saveList(todoListManager.getTodoItems());
                        System.out.println("データを保存しました。Todoリストアプリを終了します。");
						break;
				}

			}catch(InputFormatException e){
				//入力エラーの捕捉と表示
				System.out.println("エラー (入力形式): " + e.getMessage());
			}

			scanner.close();

		}
	}



    // ----------------------------------------------------
    // 各コマンドの処理メソッド
    // ----------------------------------------------------

	/**
	 * タスクの新規追加
	 * ユーザーに"[入力例]　部屋の掃除 2025-11-07"の形で入力させ、タスクを新規追加します。
	 */
    private void addTask() throws Exception{
		System.out.println("タスクと期限を入力してください");
		System.out.println("[入力例]　部屋の掃除 2025-11-07");
		String inputTask = scanner.nextLine();

		//タスクが空の場合
		if (inputTask.isEmpty()) {
            System.out.println("タスクが入力されていません。");
            return;
        }

		//1,コマンドラインの引数を2つに分割（スペース区切り）
		String[] parts = inputTask.split(" ",2);
        if (parts.length < 2) {
            // 例外をスローして、runLoopのcatchで処理させる
            throw new Exception("日付の入力が不足しています。 [内容 日付(YYYY-MM-DD)] の形式で入力してください。");
        }

		String content			= parts[0];
		String deadlineString	= parts[1];

        // 2. 型変換 (LocalDate.parseが失敗するとExceptionをスローする)
        LocalDate deadline = LocalDate.parse(deadlineString);

        // 3. TodoItemの生成と追加
        TodoItem newItem = new TodoItem(content, deadline);
        todoListManager.addItem(newItem);
        System.out.println("タスクを追加しました: " + newItem.toString());
    }

	/**
	 * タスクの編集
	 *
	 */
    private void editTask() throws Exception{
		System.out.println("編集したい項目の番号を入力してください。");
		System.out.println("1:内容　2:期限");
		System.out.print("> ");
		String operationNumber = scanner.nextLine().trim();

		//編集したい項目番号を格納する変数を宣言
		String indexString;
		int index;

		switch(operationNumber) {
			case "1":
				System.out.println("編集したい項目の番号と内容を入力してください");
				System.out.println("[入力例]　7 javaの勉強");
				String inputEditContent = scanner.nextLine();

				//1-1,コマンドラインの引数を2つに分割（スペース区切り）
				String[] editContentParts = inputEditContent.split(" ",2);
		        if (editContentParts.length < 2) {
		            // 例外をスローして、runLoopのcatchで処理させる
		            throw new Exception("入力が不正です。 [項目番号 内容] の形式で入力してください。");
		        }

				indexString	= editContentParts[0];
				String content		= editContentParts[1];

				//1-2. 型変換 (Integer.parseIntが失敗するとExceptionをスローする)
				index = Integer.parseInt(indexString);

				//1-3.項目番号と内容をTodoListManagerに渡す
				todoListManager.editContent(index, content);

				return;

			case "2":
				System.out.println("編集したい項目の番号と期限を入力してください");
				System.out.println("[入力例]　7 javaの勉強");
				String inputEditDeadline = scanner.nextLine();

				//2-1,コマンドラインの引数を2つに分割（スペース区切り）
				String[] editDeadlineParts = inputEditDeadline.split(" ",2);
		        if (editDeadlineParts.length < 2) {
		            // 例外をスローして、runLoopのcatchで処理させる
		            throw new Exception("入力が不正です。 [項目番号 内容] の形式で入力してください。");
		        }

				indexString	  = editDeadlineParts[0];
				String deadlineString = editDeadlineParts[1];

				//2-2. 型変換 (Integer.parseInt、LocalDate.parseが失敗するとExceptionをスローする)
				index = Integer.parseInt(indexString);
		        LocalDate deadline = LocalDate.parse(deadlineString);

				//2-3.項目番号と内容をTodoListManagerに渡す
				todoListManager.editDeadline(index, deadline);

				return;
		}
    }

    private void completeTask() throws Exception{
    	System.out.println("完了にしたい項目の番号を入力してください")
    	System.out.print("> ");
    	String inputIndex = scanner.nextLine().trim();

		//型変換 (Integer.parseIntが失敗するとExceptionをスローする)
		int index = Integer.parseInt(inputIndex);

		//項目番号をTodoListManagerに渡す
		todoListManager.completeItem(index);

		return;
    }


    // ----------------------------------------------------
    // メインメソッド (アプリケーションのエントリーポイント)
    // ----------------------------------------------------

	public static void main(String[] args) {
        TodoApp app = new TodoApp();
        app.start(); // startメソッドを呼び出す
	}

}
