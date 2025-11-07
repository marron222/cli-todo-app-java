package com.example.todolist;

public class InputFormatException extends Exception{

	// エラーメッセージを受け取り、親クラスに渡すコンストラクタを一つ用意
	public InputFormatException(String message) {

		// super(message) で、Exceptionクラスにメッセージを保存
		super(message);

	}

	//オプション：原因となった別の例外も一緒に渡す
    public InputFormatException(String message, Throwable cause) {
        super(message, cause);
    }

}
