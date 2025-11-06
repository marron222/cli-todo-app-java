/**
 * ToDoリストの個々の項目（タスク）のデータを保持するクラスです。
 * 内容、期限、完了状態の3つの情報を管理します。
 */
package com.example.todolist;

import java.time.LocalDate;

public class TodoItem {

	// フィールド
	/** タスクの内容（例：報告書作成） */
	private String content;

	/** タスクの期限（LocalDate形式） */
    private LocalDate deadline;

    /** タスクの完了状態（true:完了, false:未完了） */
    private boolean isCompleted;

    /**
     * TodoItemを初期化するコンストラクタです。
     * @param ct タスクの内容
     * @param dl タスクの期限 (LocalDate形式)
     */
    public TodoItem(String ct, LocalDate dl) {
    	this.content = ct;
    	this.deadline = dl;
    	this.isCompleted = false; //最初は未完了
    }

    /**
     * タスクの内容を取得します。
     * @return タスクの内容 (String)
     */
	public String getContent() {
		return content;
	}

	/**
	 * タスクの内容を更新します。
	 * @param content 新しい内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

    /**
     * タスクの期限を取得します。
     * @return タスクの期限 (LocalDate)
     */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * タスクの期限を更新します。
	 * @param deadline 新しい期限
	 */
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	/**
	 * 期限が完了しているかどうかを返します。
	 * @return 完了状態 (true:完了, false:未完了)
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * 完了状態を更新します。
	 * @param isCompleted 新しい完了状態
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/**
	 * ToDo項目をコンソール表示用の読みやすい形式で返します。
	 * (例: [DONE]｜期限：2025-11-06｜内容：報告書作成)
	 * * @return 整形されたステータス、期限、内容を含む文字列
	 */
	@Override
	public String toString() {
		String status = isCompleted ? "[DONE]":"[TODO]";
		return status + "｜期限：" + deadline + "｜内容：" + content;
	}

	/**
	 * ToDo項目をファイル保存用のカンマ区切り文字列として返します。
	 * 形式: 内容,期限(YYYY-MM-DD),完了状態(true/false)
	 * * @return ファイルに書き込むためのCSV形式のデータ文字列
	 */
	public String toFileString() {
		return this.content + "," + this.deadline + "," + this.isCompleted;
	}
}
