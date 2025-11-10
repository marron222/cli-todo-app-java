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
						todoListManager.displayList();
						break;

					//完了の場合completeTaskメソッドを実行
					case "4":
						completeTask();
						break;

					case "0":
						// 終了処理: データを保存し、ループを抜ける
                        fileHandler.saveList(todoListManager.getTodoItems());
                        System.out.println("データを保存しました。Todoリストアプリを終了します。");
						return;

					default:
						// 1, 2, 3, 4, 0 以外のコマンドが入力された場合
						System.out.println("エラー: 無効な操作番号です。1, 2, 3, 4, 0 のいずれかを入力してください。");
						break;
				}

			}catch(InputFormatException e){
				//アプリケーション固有の入力形式エラーの捕捉と表示
				System.out.println("エラー (入力形式): " + e.getMessage());
			}catch(Exception e) {
				// 数値や日付のパースエラー、配列外参照など、予期せぬエラー
				System.out.println("致命的なエラーが発生しました: " + e.getMessage());
			}
		}
	}



    // ----------------------------------------------------
    // 各コマンドの処理メソッド
    // ----------------------------------------------------

	/**
	 * タスクの新規追加
	 * ユーザーに"[入力例]　部屋の掃除 2025-11-07"の形で入力させ、タスクを新規追加します。
	 */
    private void addTask() throws InputFormatException{
		System.out.println("タスクと期限を入力してください");
		System.out.println("[入力例]　部屋の掃除 2025-11-07");
		System.out.print("> ");
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
            throw new InputFormatException("日付の入力が不足しています。 [内容 日付(YYYY-MM-DD)] の形式で入力してください。");
        }

		String content			= parts[0];
		String deadlineString	= parts[1];


        // 2. 型変換 (LocalDate.parseが失敗するとExceptionをスローする)
		LocalDate deadline;
	    try {
	        // 2. 型変換（パースエラーが発生する可能性のある箇所）
	        deadline = LocalDate.parse(deadlineString);

	    } catch (java.time.format.DateTimeParseException e) {
	        // パースエラーが発生した場合、それを InputFormatException でラップする
	        throw new InputFormatException(
	            "日付の形式が不正です。YYYY-MM-DD (例: 2025-11-07) 形式で入力してください。",
	            e
	        );
	    }

        // 3. TodoItemの生成と追加
        TodoItem newItem = new TodoItem(content, deadline);
        todoListManager.addItem(newItem);
        System.out.println("タスクを追加しました: " + newItem.toString());
    }

	/**
	 * タスクの編集
	 * ユーザーに編集したい項目をコマンドで選択させ、内容or期限を編集させる
	 */
    private void editTask() throws InputFormatException{
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
				System.out.print("> ");
				String inputEditContent = scanner.nextLine();

				//1-1,コマンドラインの引数を2つに分割（スペース区切り）
				String[] editContentParts = inputEditContent.split(" ",2);
		        if (editContentParts.length < 2) {
		            // 例外をスローして、runLoopのcatchで処理させる
		            throw new InputFormatException("入力が不正です。 [項目番号 内容] の形式で入力してください。");
		        }

				indexString	= editContentParts[0];
				String content		= editContentParts[1];

				//1-2. 型変換 (Integer.parseIntが失敗するとExceptionをスローする)
				index = Integer.parseInt(indexString);

				//1-3.項目番号と内容をTodoListManagerに渡す
				todoListManager.editContent(index, content);

				break;

			case "2":
				System.out.println("編集したい項目の番号と期限を入力してください");
				System.out.println("[入力例]　7 2026-01-01");
				System.out.print("> ");
				String inputEditDeadline = scanner.nextLine();

				//2-1,コマンドラインの引数を2つに分割（スペース区切り）
				String[] editDeadlineParts = inputEditDeadline.split(" ",2);
		        if (editDeadlineParts.length < 2) {
		            // 例外をスローして、runLoopのcatchで処理させる
		            throw new InputFormatException("入力が不正です。 [項目番号 内容] の形式で入力してください。");
		        }

				indexString	  = editDeadlineParts[0];
				String deadlineString = editDeadlineParts[1];

				//2-2. 型変換 (Integer.parseInt、LocalDate.parseが失敗するとExceptionをスローする)
				index = Integer.parseInt(indexString);
		        LocalDate deadline = LocalDate.parse(deadlineString);

				//2-3.項目番号と内容をTodoListManagerに渡す
				todoListManager.editDeadline(index, deadline);

				break;
		}
    }

	/**
	 * 項目の完了
	 * TodoListManagerのcompleteItemメソッドを呼び出し完了状態にする
	 */
    private void completeTask() throws InputFormatException{
    	System.out.println("完了にしたい項目の番号を入力してください");
    	System.out.print("> ");
    	String inputIndex = scanner.nextLine().trim();

		//型変換 (Integer.parseIntが失敗するとExceptionをスローする)
		int index = Integer.parseInt(inputIndex);

		//項目番号をTodoListManagerに渡す
		todoListManager.completeItem(index);
    }


    // ----------------------------------------------------
    // メインメソッド (アプリケーションのエントリーポイント)
    // ----------------------------------------------------

	public static void main(String[] args) {
        TodoApp app = new TodoApp();
        app.start(); // startメソッドを呼び出す
	}

}
