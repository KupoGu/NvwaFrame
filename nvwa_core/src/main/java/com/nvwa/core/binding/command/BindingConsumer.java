package com.nvwa.core.binding.command;

/**
 * @author G.god
 * Create by AS 2020/5/15 10:11
 */
public interface BindingConsumer<T> {
    void call(T t);
}
