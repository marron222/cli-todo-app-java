package com.example.todolist;

import java.time.LocalDate;

public class TodoItem {

	//フィールド
	private String content;			//内容
    private LocalDate deadline;		//期限
    private boolean isCompleted;	//完了状態

    //コンストラクタ
    public TodoItem(String ct, LocalDate dl) {
    	this.content = ct;
    	this.deadline = dl;
    	this.isCompleted = false; //最初は未完了
    }

    //getter/setter
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
}
